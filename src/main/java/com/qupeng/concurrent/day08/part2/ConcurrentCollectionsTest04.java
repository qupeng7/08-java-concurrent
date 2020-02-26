package com.qupeng.concurrent.day08.part2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ArrayBlockingQueue
 * 有界阻塞队列
 * @author qupeng
 */
public class ConcurrentCollectionsTest04 {

	public static void main(String[] args) throws InterruptedException {
		final BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(5);
//		final BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(5,true);
		
		//put方法：往队列尾部添加元素，如果满了就等待
		blockingQueue.put("qupeng01");
		blockingQueue.put("qupeng02");
		blockingQueue.put("qupeng03");
		System.out.println("加了3个以后还剩几个？"+blockingQueue.remainingCapacity());
		blockingQueue.put("qupeng04");
		blockingQueue.put("qupeng05");
		//不能放null
		blockingQueue.put(null);
		System.out.println(blockingQueue);
		//如果满了（超过边界了）再调用add就抛异常
//		blockingQueue.add("Cat");
		//take方法：获取头元素，如果空了，就等待
//		System.out.println(blockingQueue.take());
		
//		new Thread("子线程："){
//			
//			public void run() {
//				String threadName = Thread.currentThread().getName();
//				try {
//					System.out.println(threadName+"阻塞前……");
////					blockingQueue.put("Cat");
//					//offer往队尾加元素，可以设置阻塞时间
////					blockingQueue.offer("Cat",3,TimeUnit.SECONDS);
//					System.out.println(threadName+"阻塞后……");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			
//		}.start();
		
		System.out.println("主线程结束……");
		
	}

}
