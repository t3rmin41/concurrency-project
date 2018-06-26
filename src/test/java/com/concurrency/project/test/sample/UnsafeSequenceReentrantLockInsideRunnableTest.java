package com.concurrency.project.test.sample;

import static org.junit.Assert.assertEquals;
import java.util.concurrent.locks.ReentrantLock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.concurrency.project.sample.UnsafeSequence;
import com.concurrency.project.tester.MultithreadedStressTester;

public class UnsafeSequenceReentrantLockInsideRunnableTest {

  private final ReentrantLock lock = new ReentrantLock();
  
  private static final int THREAD_COUNT = 500;
  private static final int INVOCATION_COUNT = 1;
  
  private UnsafeSequence seq = null;
  private MultithreadedStressTester stressTester1 = null;
  private MultithreadedStressTester stressTester2 = null;
  
  @Before
  public void before() {
    seq =  new UnsafeSequence();
    stressTester1 = new MultithreadedStressTester(THREAD_COUNT, INVOCATION_COUNT);
    stressTester2 = new MultithreadedStressTester(THREAD_COUNT, INVOCATION_COUNT);
  }
  

  @Test
  public void testSequenceInsideRunnableLock() throws InterruptedException {
    //race condition occurs when multiple threads might read (hence, generate) the same value when in fact 
    //have to read and generate different value each read operation
      stressTester2.stress(new Runnable() {
        public void run() {
          lock.lock();
          try {
            seq.increase();
          } finally {
            lock.unlock();
          }
        }
      });
    stressTester2.shutdown();
  }
  
  @After
  public void testCount() {
    assertEquals("500 Threads running increase() in parallel should lead to 500" , 500 , seq.getCount());
  }

}
