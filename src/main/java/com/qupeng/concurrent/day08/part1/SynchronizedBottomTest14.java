package com.powernode.concurrent.day08.part1;

import java.util.ArrayList;
import java.util.List;

import org.openjdk.jol.info.ClassLayout;

/**
 * synchronized关键字的底层原理
 * -XX:BiasedLockingStartupDelay=0
 * 
 * 
 *  批量锁撤销问题
 *  
 *  
 *         偏向锁大量撤销的门槛（阈值）
 * intx BiasedLockingBulkRevokeThreshold          = 40                                  {product}
 * -XX:+PrintFlagsFinal and -XX:+PrintFlagsInitial
 * 
 * @author qupeng
 */
public class SynchronizedBottomTest14 {

    private static volatile List<Test> myLockList = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        //创建一个线程创建80个Test的实例，并对其进行加锁
        Thread t1 = new Thread("线程1：") {

            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                for (int i = 1; i <= 80; i++) {
                    Test myLock = new Test();
                    synchronized (myLock) {
                        myLockList.add(myLock);
                    }
                    if (i == 7/*||i==25*/) {
                        System.out.println(
                            threadName + "---------------第" + i + "个----------------------");
                        System.out.println(ClassLayout.parseInstance(myLock).toPrintable());
                    }

                }
                /*
                 * 循环外休眠线程1，保证后续的线程2在执行的时候
                 * 线程1是存活的，这样线程2进入时就是轻量级锁
                 */
                try {Thread.sleep(60_000);} catch (InterruptedException e) {}
            }
        };
        t1.start();
//        t1.join();
        
        Thread.sleep(10_000);
        
        Thread t2 = new Thread("线程2：") {

            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                /*
                 * 这里换成40以下就可以导致线程3后续对
                 * 偏向了线程1的锁对象进行一个重偏向为线程3
                 * 但是>=40后就直接执行批量锁的撤销，不再批量重偏向
                 * 而是直接上轻量级锁
                 */
                for (int i = 1; i <= 40; i++) {
                    Test myLock = myLockList.get(i - 1);
                    synchronized (myLock) {
                        if (i == 5 || i == 20) {
                            System.out.println(
                                threadName + "----------------第" + i + "个---------------------");
                            System.out.println(ClassLayout.parseInstance(myLock).toPrintable());
                        }
                    }
                }
                try {Thread.sleep(60_000);} catch (InterruptedException e) {}
            }

        };
        t2.start();
        
//        t2.join();
//        /*
//         * 这里由于前19个锁对象为轻量级锁，所以在退出后会将其线程信息清空所以为001
//         * 且线程信息中没有任何东西存放（无锁且不可偏向）
//         */
//        System.out.println("-----------------myLockList第19个元素的对象头是：------------------");
//        System.out.println(ClassLayout.parseInstance(myLockList.get(18)).toPrintable());
//        /*
//         * 这里由于从第20个锁对象开始发生了批量锁的重偏向，所以在退出后锁状态101
//         * 且线程信息中存放的是线程2的信息（偏向线程2的偏向锁）
//         */
//        System.out.println("-----------------myLockList第20个元素的对象头是：------------------");
//        System.out.println(ClassLayout.parseInstance(myLockList.get(19)).toPrintable());
        
        
        
        Thread.sleep(10_000);
        /*
         * 线程3对已经偏向线程2的偏向锁对象再进行一个加锁，
         * 
         */
        Thread t3 = new Thread("线程3：") {

            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                for(int i=20;i<=80;i++){
//                	System.out.println(threadName +myLockList.size());
                    Test myLock =myLockList.get(i - 1);
                    synchronized (myLock) {
                        if (i == 25||i==42||i==55||i==67) {
                            System.out.println(
                                threadName + "----------------第" + i + "个---------------------");
                            System.out.println(ClassLayout.parseInstance(myLock).toPrintable());
                        }
                    }
                }
                
            }

        };
        t3.start();
        
    }

    static class Test {

    }

}
