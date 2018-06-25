package com.concurrency.project.sample;

public class SafeSequence {

  private int value;
  
  public synchronized void increase() {
    value++;
  }
  
  public int getCount() {
    return value;
  }
  
}
