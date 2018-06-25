package com.concurrency.project.test.sample;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.concurrency.project.sample.SafeSequence;
import com.concurrency.project.tester.MultithreadedStressTester;

public class SafeSequenceTest {

  private static final int THREAD_COUNT = 100;
  private static final int INVOCATION_COUNT = 1;
  
  private SafeSequence seq = null;
  private MultithreadedStressTester stressTester = null;
  
  @Before
  public void before() {
    seq =  new SafeSequence();
    stressTester = new MultithreadedStressTester(THREAD_COUNT, INVOCATION_COUNT);
  }
  
  @Test
  public void testSequence() throws InterruptedException {
    //race condition occurs when multiple threads might read (hence, generate) the same value when in fact 
    //have to read and generate different value each read operation
    stressTester.stress(new Runnable() {
      public void run() {
        seq.increase();
      }
    });
    stressTester.shutdown();
  }
  
  @After
  public void testCount() {
    assertEquals("100 Threads running increase() in parallel should lead to 100" , 100 , seq.getCount());
  }
  
}
