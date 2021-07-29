package com.testTaskSpring.service.implementation;

import com.testTaskSpring.dao.implementation.WellDaoImpl;
import com.testTaskSpring.domain.Well;
import java.util.ArrayList;
import java.util.List;
import com.testTaskSpring.service.WellService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WellServiceImpl implements WellService {

  public WellServiceImpl() {

  }

  WellDaoImpl wellDaoImpl = new WellDaoImpl();

  @Override
  public Well getWellByName(String name) {

    log.info("Method getWellByName is started");
    Well well;
    try {
      well = wellDaoImpl.getWellByName(name);

      return well;
    }
    catch (NullPointerException npe) {
      log.error("This is error : " + npe.getMessage(), npe);
    }

    return null;
  }

  @Override
  public Well createWellByName(String name) {

    log.info("Method createWellByName is started");

    Well well = new Well();
    well.setName(name);

    return wellDaoImpl.createWell(well);
  }

  @Override
  public List<String> getWellsName() {

    log.info("Method getWellsName is started");
    List<String> wellNames = new ArrayList<>();
    wellDaoImpl.getAllWells().forEach(well -> wellNames.add(well.getName()));

    return wellNames;
  }

  @Override
  public List<Well> getAllWells() {

    log.info("Method getWells is started");

    return wellDaoImpl.getAllWells();
  }
}
