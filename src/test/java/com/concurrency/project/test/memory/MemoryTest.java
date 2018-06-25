package com.concurrency.project.test.memory;

import org.junit.Test;

public class MemoryTest {

  @Test
  public void testMemory() {
    Outer o1 = new Outer();
    Outer o2 = new Outer();
    
    Outer.Inner i1 = o1.new Inner();
    Outer.Inner i2 = o2.new Inner();
    System.out.println("i1 = " + i1);
    System.out.println("i2 = " + i2);
  }
  
}
