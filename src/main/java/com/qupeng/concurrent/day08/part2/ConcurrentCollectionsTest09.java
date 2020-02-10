package com.powernode.concurrent.day08.part2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * HashMap的数据结构：
 * JDK1.7：是哈希表结构（数组+链表）
 * 加载因子：0.75
 * 扩容：原来容量的2倍
 * JDK1.8：是哈希表结构（数组+红黑树）
 * 
 * ConcurrentHashMap实现线程安全的方式：
 * JDK1.7：是分段锁机制    默认将锁分成16段，
 * 这个在初始化的时候可以指定，一旦指定就不可更改。
 * JDK1.8：是无锁（CAS）
 * 
 * ConcurrentSkipListMap的数据结构：
 * 跳表结构，所以是有序的。
 * 
 * 性能对比
 * 
 * @author qupeng
 */
public class ConcurrentCollectionsTest09 {

	public static void main(String[] args) {
		Map<String, Object>  testConHashMap=new ConcurrentHashMap<>();
		Map<String,Object>   testConSkipListMap =new ConcurrentSkipListMap<>();
		Map<String, Object>  testHashMap=new HashMap<>();
	}

}
