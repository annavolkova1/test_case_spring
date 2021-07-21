package com.testTaskSpring.service.implementation;

import com.testTaskSpring.dao.implementation.EquipmentDaoImpl;
import com.testTaskSpring.domain.Equipment;
import java.util.List;
import java.util.Map;
import com.testTaskSpring.service.EquipmentService;

public class EquipmentServiceImpl implements EquipmentService {

  private final EquipmentDaoImpl equipmentDao = new EquipmentDaoImpl();

  @Override
  public void createEquipment(long wellId) {

    Equipment equipment = new Equipment();
    equipment.setWellId(wellId);

    equipmentDao.createEquipment(equipment);
  }

  @Override
  public Map<String, Integer> numberOfEquipment(String names) {

    return equipmentDao.numberOfEquipment(names);
  }

  @Override
  public List<Equipment> getAllEquipments() {

    return equipmentDao.getAllEquipments();
  }
}
