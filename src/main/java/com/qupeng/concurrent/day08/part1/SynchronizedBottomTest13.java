package com.powernode.concurrent.day08.part1;

import java.util.ArrayList;
import java.util.List;

import org.openjdk.jol.info.ClassLayout;

/**
 * synchronized关键字的底层原理
 * -XX:BiasedLockingStartupDelay=0
 * 
 *  批量重偏向问题
 *  子线程创建了同一个类的多个对象并且对这个对象进行了加锁
 *  主线程也在这些对象加锁后，也对这些对象加锁（没有发生竞争的加锁）
 *  因为要执行CAS进行线程信息的替换（锁的升级），那么就会进行多次
 *  偏向锁的撤销，那么JVM就会认为后面的对象都需要做批量重偏向，
 *  那么后面的对象就会是加偏向锁，而不再是轻量级锁。
 *  
 *         偏向锁大量重偏向的门槛（阈值）
 * intx BiasedLockingBulkRebiasThreshold          = 20                                  {product}
 * 
 * 
 * 25个对象：
 * 1、不在同步代码区域
 * 2、25个中，只要有一个还在同步区域中
 * 
 * Test.class{
 *       //偏向计数器
 *       int count=20;
 *      1、执行批量重偏向为主线程
 *      2、epoch（时代）=01
 * }
 * 
 * 
 * 00000101 00100000 01111110 10110011
 *                     001000_00
 * @author qupeng
 */
public class SynchronizedBottomTest13 {

    private static volatile List<Test> myLockList = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        //创建一个线程创建25个Test的实例，并对其进行加锁
        Thread t1 = new Thread("子线程：") {

            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                for (int i = 1; i <= 25; i++) {
                    Test myLock = new Test();
                    synchronized (myLock) {
                        myLockList.add(myLock);
                    }
                    if (i == 7) {
                        System.out.println(threadName + "---------------------第"+i+"个-------------------------");
                        System.out.println(ClassLayout.parseInstance(myLock).toPrintable());
                    }

                }
            }

        };
        t1.start();
        t1.join();

        Thread mainThread = Thread.currentThread();
        mainThread.setName("主线程：");
        for (int i = 1; i <= myLockList.size(); i++) {
            Test myLock = myLockList.get(i - 1);
            synchronized (myLock) {
                /*
                 * 这里预测应该是轻量级锁，但是如果大于等于了20
                 * 就会发生批量重偏向
                 */
                if (i == 7 || i == 9 || i == 20) {
                    System.out
                        .println(mainThread.getName() + "-----------------第"+i+"个--------------------");
                    System.out.println(ClassLayout.parseInstance(myLock).toPrintable());
                }
            }
        }

    }

    static class Test {

    }

}
