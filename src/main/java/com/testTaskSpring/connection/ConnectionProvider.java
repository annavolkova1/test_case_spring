package com.testTaskSpring.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectionProvider {

  /**
   * Gets com.testTaskSpring.connection with database.
   *
   * @return the com.testTaskSpring.connection
   */
  public static Connection getConnection() {

    Connection connection;

    try {
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:test.db");

      return connection;
    }
    catch (Exception exception) {
      log.error(exception.getClass().getName() + ": " + exception.getMessage());
      System.exit(0);
    }
    System.out.println("Opened database successfully");

    return null;
  }
}
