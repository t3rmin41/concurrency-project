package com.concurrency.project.sample;

public class UnsafeSequence {

  private int value;
  
  public void increase() {
    value++;
  }
  
  public int getCount() {
    return value;
  }
}
