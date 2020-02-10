package com.powernode.concurrent.day08.part2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * PriorityBlockingQueue
 * 
 * 有序的无界队列
 * 
 * 可以扩容
 * @author qupeng
 */
public class ConcurrentCollectionsTest07 {

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> blockingQueue=new PriorityBlockingQueue<>();
//		BlockingQueue<String> blockingQueue=new PriorityBlockingQueue<>(5);
		for(int i=1;i<=10;i+=2){
			blockingQueue.add("qupeng_"+i);
		}
		System.out.println(blockingQueue);
		blockingQueue.add("qupeng_4");
		System.out.println(blockingQueue);
		
	}
	

}
