package com.toyproject.tank.server;

import java.net.ServerSocket;

import com.toyproject.tank.Log;

public class Server {
	public static int port = 9999;
	private ServerSocket serverSocket = null;
	private Reciver reciever;
	
	public Server(boolean isInit)
	{
		try
		{
			if (isInit)
			{
				serverSocket = new ServerSocket(port);
				Log.getLog().logString("serverSocket created");
				
			}
			else
			{
				Log.getLog().logString("Sever Class created : execute initSocket() ");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void initSocket()
	{
		try
		{
			serverSocket = new ServerSocket(port);
			Log.getLog().logString("serverSocket created");
			
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void startReciver()
	{
		reciever = new Reciver(serverSocket);
		reciever.start();
		
	}
	
	public void startReciver(DataPool pool)
	{
		reciever = new Reciver(serverSocket, pool);
		reciever.start();
		
	}
	
}
