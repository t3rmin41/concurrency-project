package com.concurrency.project.test.sample;

import org.junit.Test;

public class DeadLockResolvedTest {

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
    public void testDeadLockResolved() {
        final A a = new A();
        final B b = new B();

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                synchronized (b) {
                    System.out.println("runnable1 locked b");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                    synchronized (a) {
                        System.out.println("runnable1 locked a and b");
                    }
                    System.out.println("runnable1 released a");
                }
                System.out.println("runnable1 released b");
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                synchronized (a) {
                    System.out.println("runnable2 locked a");
                    synchronized (b) {
                        System.out.println("runnable2 locked a and b");
                    }
                    System.out.println("runnable2 released b");
                }
                System.out.println("runnable2 released a");
            }
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        thread2.start();
    }

}
