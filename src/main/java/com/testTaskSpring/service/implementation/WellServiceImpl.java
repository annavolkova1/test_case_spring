package com.testTaskSpring.service.implementation;

import com.testTaskSpring.dao.implementation.WellDaoImpl;
import com.testTaskSpring.domain.Well;
import java.util.ArrayList;
import java.util.List;
import com.testTaskSpring.service.WellService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class WellServiceImpl implements WellService {

  public WellServiceImpl() {

  }

  WellDaoImpl wellDaoImpl = new WellDaoImpl();

  @Override
  public Well getWellByName(String name) {

    Well well;
    try {
      well = wellDaoImpl.getWellByName(name);

      return well;
    }
    catch (NullPointerException npe) {
      npe.printStackTrace();
    }

    return null;
  }

  @Override
  public Well createWellByName(String name) {

    Well well = new Well();
    well.setName(name);

    return wellDaoImpl.createWell(well);
  }

  @Override
  public List<String> getWellsName() {

    List<String> wellNames = new ArrayList<>();
    wellDaoImpl.getAllWells().forEach(well -> wellNames.add(well.getName()));

    return wellNames;
  }

  @Override
  public List<Well> getAllWells() {

    return wellDaoImpl.getAllWells();
  }
}
