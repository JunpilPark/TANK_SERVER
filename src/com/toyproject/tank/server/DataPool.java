package com.toyproject.tank.server;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DataPool {
	private BlockingQueue<byte[]>  queue;
	
	public DataPool() {
		queue = new ArrayBlockingQueue<byte[]>(50);
	}
	
	public void add(byte[] data)
	{
		this.queue.add(data);
	}
	
	public boolean hasPacket()
	{
		if(queue.isEmpty()) 
			return false;
		else
			return true;
	}
	
	public byte[] take()
	{
		try {
			return this.queue.take();
		} catch (InterruptedException e) {
			return null;		
		}
	}
	
}
