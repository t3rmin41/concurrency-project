package com.concurrency.project.test.sample;

import static org.junit.Assert.assertEquals;
import java.util.concurrent.locks.ReentrantLock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.concurrency.project.sample.UnsafeSequence;
import com.concurrency.project.tester.MultithreadedStressTester;

public class UnsafeSequenceLockTest {

  private final ReentrantLock lock = new ReentrantLock();
  
  private static final int THREAD_COUNT = 100;
  private static final int INVOCATION_COUNT = 1;
  
  private UnsafeSequence seq = null;
  private MultithreadedStressTester stressTester = null;
  
  @Before
  public void before() {
    seq =  new UnsafeSequence();
    stressTester = new MultithreadedStressTester(THREAD_COUNT, INVOCATION_COUNT);
  }
  
  @Test
  public void testSequence() throws InterruptedException {
    //race condition occurs when multiple threads might read (hence, generate) the same value when in fact 
    //have to read and generate different value each read operation
    this.lock.lock();
    try {
      stressTester.stress(new Runnable() {
        public void run() {
          seq.increase();
        }
      });
    } finally {
      this.lock.unlock();
    }
    stressTester.shutdown();
  }
  
  @After
  public void testCount() {
    assertEquals("100 Threads running increase() in parallel should lead to 100" , 100 , seq.getCount());
  }
  
  
}
