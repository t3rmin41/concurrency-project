package com.concurrency.project.test.sample;

import org.junit.Test;

public class DeadLockTest {

    class A {
        private int i = 10;
        public int getI() {
            return this.i;
        }
        public void setI(int p) {
            this.i = p;
        }
    }

    class B {
        private int i = 20;
        public int getI() {
            return this.i;
        }
        public void setI(int p) {
            this.i = p;
        }
    }

    @Test
    public void testDeadLock() {
        final A a = new A();
        final B b = new B();

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                synchronized (a) {
                    try {
                        Thread.sleep(1000);
                        synchronized (b) {
                            System.out.println("in block1");
                        }
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                synchronized (b) {
                    synchronized (a) {
                        System.out.println("in block2");
                    }
                }
            }
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();
        thread2.start();
    }
}
