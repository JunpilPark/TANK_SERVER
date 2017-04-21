package com.toyproject.tank;


import com.toyproject.tank.server.DataPool;
import com.toyproject.tank.server.Serial;
import com.toyproject.tank.server.Server;

public class Main {
	
	public static void main(String... args)
	{

		Log.getLog().debugModeOn();
		DataPool dataPool = new DataPool();
		Serial serial = new Serial();
		Server server = new Server(true);
		
		server.startReciver(dataPool);
		
		serial.connect(args[0]);
		serial.startWrite(dataPool);
		
	}

}
