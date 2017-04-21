package com.toyproject.tank.server;

public class DataSet {
	public static final byte APP = (byte) 0x01;
 	// public static final byte SERVER = (byte) 0x02; 	// disable
	public static final byte SERIAL = (byte) 0x03;
	
	public static final byte MOTOR = (byte) 0x01;
	public static final byte LED = (byte) 0x02;
	
	public static final byte FRT_R = (byte) 0x11;
	public static final byte FRT_L = (byte) 0x12;
	public static final byte RR_R = (byte) 0x21;
	public static final byte RR_L = (byte) 0x22;
	
	public static final byte OFF = (byte) 0x00;
	public static final byte ON_FRONT = (byte) 0x11;
	public static final byte ON_BACK = (byte) 0x12;
	
	
}
