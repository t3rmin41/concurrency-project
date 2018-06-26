package com.concurrency.project.test.sample;

import static org.junit.Assert.assertEquals;
import java.util.Vector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.concurrency.project.tester.MultithreadedStressTester;

public class VectorSyncTest {

  private Vector<Integer> vector = new Vector<Integer>();
  
  private static final int THREAD_COUNT = 500;
  private static final int INVOCATION_COUNT = 1;
  private MultithreadedStressTester stressTester1 = null;
  
  @Before
  public void before() {
    stressTester1 = new MultithreadedStressTester(THREAD_COUNT, INVOCATION_COUNT);
  }
  
  @Test
  public void testVector() throws InterruptedException {
    //race condition occurs when multiple threads might read (hence, generate) the same value when in fact 
    //have to read and generate different value each read operation
    for (int i = 0; i < THREAD_COUNT; i++) {
      Integer integer = new Integer(i);
      stressTester1.stress(new Runnable() {
        public void run() {
          synchronized(vector) {
            if (!vector.contains(integer)) {
              vector.add(integer);
            }
          }
        }
      });
    }
    stressTester1.shutdown();
  }
  
  
  @After
  public void testCount() {
    assertEquals("500 Threads running increase() in parallel should lead to 499" , 499 , vector.get(499).intValue());
  }
  
  
}
