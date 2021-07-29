package com.testTaskSpring.controller;

import com.testTaskSpring.domain.Equipment;
import com.testTaskSpring.domain.MetaData;
import com.testTaskSpring.domain.Well;
import com.testTaskSpring.service.EquipmentService;
import com.testTaskSpring.service.WellService;
import com.testTaskSpring.service.implementation.EquipmentServiceImpl;
import com.testTaskSpring.service.implementation.WellServiceImpl;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@RestController
@Slf4j
public class MainSpringController {

  WellService wellService = new WellServiceImpl();
  EquipmentService equipmentService = new EquipmentServiceImpl();
  List<Well> wells = new ArrayList<>();
  Map<String, Integer> map;

  @RequestMapping(value = "/createEquipmentOnWell", method = RequestMethod.POST)
  public List<Well> createEquipmentOnWell(@RequestParam(value = "name") String name,
      @RequestParam(value = "amount") int amount) {

    log.info("Into method createEquipmentOnWell");
    Well well = wellService.getWellByName(name);

    if (well == null) {
      well = wellService.createWellByName(name);
    }

    for (int i = 0; i < amount; i++) {
      equipmentService.createEquipment(well.getId());
    }

    return  wells.stream()
        .filter(x -> x.getName()
            .equalsIgnoreCase(name))
        .collect(Collectors.toList());
  }

  @RequestMapping(value = "/displayGeneralInfoAboutEquipment", method = RequestMethod.GET)
  public Map<String, Integer> displayGeneralInfoAboutEquipment(@RequestParam(value = "name") String namesWell) {

    log.info("Into method displayGeneralInfoAboutEquipment");
    String[] names = namesWell.split("[\\s,]");
    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < names.length; i++) {

      if (i == names.length - 1) {
        stringBuilder.append("'")
            .append(names[i])
            .append("'");
      }
      else {
        stringBuilder.append("'")
            .append(names[i])
            .append("', ");
      }
    }

    return map = equipmentService.numberOfEquipment(stringBuilder.toString());
  }

  @RequestMapping(value = "/exportAllDataToXmlFile", method = RequestMethod.GET)
  public List<String> exportAllDataToXmlFile(@RequestParam(value = "fileName") String fileName) {

    log.info("Into method exportAllDataToXmlFile");
    String xmlFilePath = fileName + ".xml";
    wells = wellService.getAllWells();
    List<Equipment> equipments = equipmentService.getAllEquipments();

    try {
      DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
      Document document = documentBuilder.newDocument();
      Element root = document.createElement("dbinfo");

      document.appendChild(root);

      for (Well well : wells) {
        Element wellEl = createDomElement(well, document, root, "well");

        for (Equipment equipment : equipments) {

          if (well.getId().equals(equipment.getWellId())) {
            createDomElement(equipment, document, wellEl, "equipment");
          }
        }
      }
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource domSource = new DOMSource(document);
      File tmp = new File("./", xmlFilePath);
      StreamResult streamResult = new StreamResult(tmp);

      transformer.transform(domSource, streamResult);

      return Files.readAllLines(Paths.get(Paths.get(xmlFilePath).toString()));
    }
    catch (ParserConfigurationException | TransformerException | IOException exception) {
      log.error("This is error : ", exception);
    }
    return null;
  }

  /**
   * Creates DOM element
   *
   * @param metaData well or equipment instance
   * @param document document instance
   * @param element parent DOM element
   * @param str well or equipment
   * @return created DOM element
   */
  @NotNull
  private Element createDomElement(@NotNull MetaData metaData, @NotNull Document document, @NotNull Element element,
      String str) {

    Element documentElement = document.createElement(str);
    element.appendChild(documentElement);

    Attr id = document.createAttribute("id");
    id.setValue(String.valueOf(metaData.getId()));
    documentElement.setAttributeNode(id);

    Attr name = document.createAttribute("name");
    name.setValue(metaData.getName());
    documentElement.setAttributeNode(name);

    return documentElement;
  }
}
