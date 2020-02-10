package com.powernode.concurrent.day08.collectionbase;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 演示队列的API
 * @author qupeng
 * 队列（Queue）接口继承了Collection接口
 * 队列元素通常是：FIFO（First In First Out）先进先出原则
 * 其元素是可重复的
 * add方法 	向队列中追加元素成功则返回一个true
 * 如果当前没有可用空间则抛出一个IllegalStateException
 * offer方法	也是追加元素，不同于add方法的是其不会抛出异常
 * element方法	获取该队列的头元素且不做出列操作
 * 如果队列中不含有元素则抛出一个NoSuchElementException
 * peek方法	获取该队列的头元素且不做出列操作
 * 如果队列中不含有元素则返回null
 * remove方法		获取该队列的头元素且做出列操作删除该元素
 * 如果队列中不含有元素则抛出一个NoSuchElementException
 * poll方法	获取该队列的头元素且做出列操作删除该元素
 * 如果队列中不含有元素则返回null
 */
public class QueueTest {

	public static void main(String[] args) {
		Queue<String> qe=new LinkedList<String>();
		/*add方法向队列中追加元素成功则返回一个true
		如果当前没有可用空间则抛出一个IllegalStateException*/
		qe.add("语文");
		//offer方法也是追加元素，不同于add方法的是其不会抛出异常
		qe.offer("数学");
		qe.add("英语");
		//qe.add("英语");
		System.out.println(qe.toString());
//		boolean b=qe.contains("生物");
//		System.out.println(b);
		//element方法获取该队列的头元素且不做出列操作
		//如果队列中不含有元素则抛出一个NoSuchElementException
		qe.removeAll(qe);
		System.out.println(qe.poll());
		//System.out.println(qe.remove());
		//System.out.println(qe.peek());
		//System.out.println(qe.element());
		System.out.println(qe.toString());
	}

}
