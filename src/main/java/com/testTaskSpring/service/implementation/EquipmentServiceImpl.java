package com.testTaskSpring.service.implementation;

import com.testTaskSpring.dao.implementation.EquipmentDaoImpl;
import com.testTaskSpring.domain.Equipment;
import java.util.List;
import java.util.Map;
import com.testTaskSpring.service.EquipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EquipmentServiceImpl implements EquipmentService {

  private final EquipmentDaoImpl equipmentDao = new EquipmentDaoImpl();

  @Override
  public void createEquipment(long wellId) {

    log.info("Create of equipments is started");

    Equipment equipment = new Equipment();
    equipment.setWellId(wellId);

    equipmentDao.createEquipment(equipment);
    log.info("Create of equipments is finished");
  }

  @Override
  public Map<String, Integer> numberOfEquipment(String names) {

    log.info("Method numberOfEquipment is started");

    return equipmentDao.numberOfEquipment(names);
  }

  @Override
  public List<Equipment> getAllEquipments() {

    log.info("Method getAllEquipments is started");

    return equipmentDao.getAllEquipments();
  }
}
