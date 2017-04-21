package com.toyproject.tank.server;

import java.io.OutputStream;

import com.toyproject.tank.Log;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class Serial {
	
	OutputStream out;
	
	public void connect(String portName)  {
	
			
		Log.getLog().logString("Port : " + portName);
			
			try 
			{
				CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		
				if (portIdentifier.isCurrentlyOwned()) {
					Log.getLog().logString("Error: Port is currently in use");
				} else {
					CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
		
					if (commPort instanceof SerialPort) {
						SerialPort serialPort = (SerialPort) commPort;
						serialPort.setSerialPortParams(57600,	
								SerialPort.DATABITS_8, 			
								SerialPort.STOPBITS_1,			
								SerialPort.PARITY_NONE);		
		
						 out = serialPort.getOutputStream();
		
		
					} else {
						Log.getLog().logString("Error: Only serial ports are handled by this example.");
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	
	public void startWrite(DataPool datapool)
	{
		new Thread(new WriteSr(out,datapool)).start();
	}
	
}
