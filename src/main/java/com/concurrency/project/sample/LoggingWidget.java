package com.concurrency.project.sample;

public class LoggingWidget extends Widget {

  public synchronized void doSomething() {
    System.out.println("Logging widget does something");
    super.doSomething();
  }
  
}
