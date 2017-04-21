package com.toyproject.tank.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.toyproject.tank.Log;

public class Reciver extends Thread {

	private Socket socket;
	private DataInputStream in;
	private ServerSocket serverSocket;
	private DataPool datapool;
	
	public Reciver(Socket socket) {
		this.socket = socket;
		try {
			in = new DataInputStream(this.socket.getInputStream());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Reciver(ServerSocket serverSocket) {
		
		try {
			this.serverSocket = serverSocket;
			this.socket = serverSocket.accept();
			Log.getLog().logString("Socket created.");
			
			in = new DataInputStream(this.socket.getInputStream());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
public Reciver(ServerSocket serverSocket, DataPool pool) {
		
		try {
			this.serverSocket = serverSocket;
			this.socket = serverSocket.accept();
			Log.getLog().logString("Socket created.");
			
			in = new DataInputStream(this.socket.getInputStream());
			
			this.datapool = pool;
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run()  {
		Log.getLog().logString("reciver thread run.");
		byte[] tmpData = new byte[4];
		while(in != null)
		{
			try {
				in.readFully(tmpData, 0, tmpData.length);
				
				Log.getLog().logString("recive data: " + tmpData[0] + "," + tmpData[1] + "," + tmpData[2] + "," + tmpData[3]);
				
				if((byte)(tmpData[0] & 0x0f) == (byte)DataSet.SERIAL)
				{
					this.datapool.add(tmpData);
					Log.getLog().logString("data was added in datapool");
				}
				
			} catch (IOException e) {
				Log.getLog().logString("io Exception : reciver thread - " + e.getMessage());
				e.printStackTrace();
				reInit();
			}
		}
	}

	public void reInit()
	{
		try {
			if (this.socket != null || !this.socket.isClosed())
			{
				this.socket.close();
				Log.getLog().logString("reciver Socket closed");
			}
			
			in.close();
			Log.getLog().logString("reciver inputStream Closed");
					
			this.socket =  this.serverSocket.accept();		
			in = new DataInputStream(this.socket.getInputStream());
			Log.getLog().logString("reciver socket reconnect.");
			
		} catch (IOException e1) {
			Log.getLog().logString("io Exception : reciver thread - " + e1.getMessage());
			e1.printStackTrace();
		}
	}
}
