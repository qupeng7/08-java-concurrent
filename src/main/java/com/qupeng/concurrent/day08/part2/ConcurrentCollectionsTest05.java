package com.powernode.concurrent.day08.part2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * LinkedBlockingQueue
 * 有界或无界可选的阻塞队列
 * @author qupeng
 */
public class ConcurrentCollectionsTest05 {

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> blockingQueue=new LinkedBlockingQueue<>();
//		blockingQueue.add(null);
		for(int i=1;i<=5;i++){
			blockingQueue.add("qupeng_"+i+"号");
		}
		System.out.println(blockingQueue);
		//将所有元素转移到某个集合中
		Collection<String> clc=new ArrayList<>();
//		blockingQueue.drainTo(clc);
		//将前几个元素转移到某个集合中
		blockingQueue.drainTo(clc,3);
		System.out.println("现在的阻塞队列："+blockingQueue);
		System.out.println("转移的集合："+clc);
		
	}

}
