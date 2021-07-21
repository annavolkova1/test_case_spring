package com.testTaskSpring.dao.implementation;

import com.testTaskSpring.connection.ConnectionProvider;
import com.testTaskSpring.dao.WellDao;
import com.testTaskSpring.domain.Well;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class WellDaoImpl implements WellDao {

  private final static String SELECT_ALL_WELL = "SELECT id, name FROM well";
  private final static String SELECT_BY_NAME = "SELECT id, name FROM well WHERE name = ?";
  private final static String INSERT_WELL = "INSERT INTO well (name) VALUES (?)";

  @Override
  public List<Well> getAllWells() {

    List<Well> wells = new ArrayList<>();
    try (Connection connection = ConnectionProvider.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_WELL)) {

      while (resultSet.next()) {
        long id = resultSet.getLong(1);
        String name = resultSet.getString(2);
        Well well = new Well(id, name);

        wells.add(well);
      }
    }
    catch (SQLException throwable) {
      throwable.printStackTrace();
    }

    return wells;
  }

  @Override
  public Well createWell(@NotNull Well well) {

    try (Connection connection = ConnectionProvider.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WELL,
            Statement.RETURN_GENERATED_KEYS)) {
      preparedStatement.setString(1, well.getName());

      int rowsAdded = preparedStatement.executeUpdate();

      if (rowsAdded == 0) {
        throw new SQLException("Creating well failed, no rows affected.");
      }

      try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {

        if (generatedKeys.next()) {
          well.setId(generatedKeys.getLong(1));
        }
        else {
          throw new SQLException("Creating well failed, no ID obtained.");
        }
      }

      return well;
    }
    catch (SQLException throwable) {
      throwable.printStackTrace();
    }

    return null;
  }

  @Override
  public Well getWellByName(String name) {

    try (Connection connection = ConnectionProvider.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_NAME)) {
      preparedStatement.setString(1, name);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        return extractWellFromResultSet(resultSet);
      }
    }
    catch (SQLException throwable) {
      throwable.printStackTrace();
    }

    return null;
  }

  /**
   * Extracts well from ResultSet
   *
   * @param resultSet that contains information about well
   * @return well
   */
  private Well extractWellFromResultSet(@NotNull ResultSet resultSet) {

    Well well = new Well();
    try {
      long id = resultSet.getLong(1);
      String name = resultSet.getString(2);
      well = new Well(id, name);
    }
    catch (SQLException throwable) {
      throwable.printStackTrace();
    }

    return well;
  }
}
