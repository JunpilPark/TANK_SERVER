package com.toyproject.tank;

public class Log {
	
	private boolean DebugMode;
	private static Log log = null;
	
	private Log() {
		DebugMode = false;
	}
	
	public static Log getLog() 
	{	
		if(log == null)
			log = new Log();
		return log; 
	}
	
	public void debugModeOn()
	{
		DebugMode = true;
	}
	
	public void debugModeOff()
	{
		DebugMode = false;
	}
	
	public void logString(String log)
	{
		if(DebugMode)
			System.out.println(log);
	}
}
