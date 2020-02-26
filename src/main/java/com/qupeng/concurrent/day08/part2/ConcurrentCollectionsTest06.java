package com.qupeng.concurrent.day08.part2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue
 * 同步队列
 * 
 * 生产者不能往队列中添加元素，只能等到有
 * 消费者在消费时才能够添加
 * 
 * 消费者必须先于生产者操作队列
 * 容量为0的队列
 * 
 * @author qupeng
 */
public class ConcurrentCollectionsTest06 {

	public static void main(String[] args) throws InterruptedException {
		final BlockingQueue<String> blockingQueue=new SynchronousQueue<>();
		System.out.println(blockingQueue.size());
//		blockingQueue.add("qupeng");
		
//		new Thread("消费者："){
//			
//			@Override
//			public void run() {
//				for(int i=1;i<=1000;i++){
//					blockingQueue.remove();
////					try {Thread.sleep(1000);} catch (InterruptedException e) {}
////					blockingQueue.poll();
//				}
//			}
//			
//		}.start();
//		
//		Thread.sleep(1000);
//		
//		new Thread("生产者："){
//			
//			@Override
//			public void run() {
//				for(int i=1;i<=10;i++){
//					try {Thread.sleep(2000);
//					blockingQueue.add("qupeng_"+i);
//					} catch (InterruptedException e) {}
//				}
//			}
//			
//		}.start();
		
		
		System.out.println("主线程执行完毕");
		
	}
	

}
