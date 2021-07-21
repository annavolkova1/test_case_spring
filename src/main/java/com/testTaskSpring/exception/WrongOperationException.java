package com.testTaskSpring.exception;

public class WrongOperationException extends Exception {

  static {
    System.out.println("Вы ввели неверное значение!");
  }
}
