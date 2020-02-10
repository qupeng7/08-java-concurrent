package com.powernode.concurrent.day08.part2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * HashMap是线程不安全的
 * HashTable是线程安全的
 * ConcurrentHashMap 是线程安全的
 * ConcurrentSkipListMap 是线程安全的而且有序的
 * 
 * 性能对比
 * 
 * @author qupeng
 */
public class ConcurrentCollectionsTest08 {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		final Map<String, Object>  testConHashMap=new ConcurrentHashMap<>();
		final Map<String,Object>   testConSkipListMap =new ConcurrentSkipListMap<>();
		final Map<String, Object>  testHashMap=new HashMap<>();
		final Map<String, Object>  testHashTable=new Hashtable<>();
		List<Thread> threadList=new ArrayList<>();
		long beforeTime=System.currentTimeMillis();
		for(int i=1;i<=5;i++){
			Thread t = new Thread(){
				@Override
				public void run() {
					//放2百万个元素
					for(int i=1;i<=2000000;i++){
						testHashTable.put("test"+i, new Object());
//						testConSkipListMap.put("test"+i, new Object());
//						testConHashMap.put("test"+i, new Object());
					}
				}
			};
			threadList.add(t);
			t.start();
		}
		
		for(Thread t:threadList){
			t.join();
		}
		
		long endTime=System.currentTimeMillis();
		System.out.println("用时："+(endTime-beforeTime)+"毫秒");
		
		
	}

}
