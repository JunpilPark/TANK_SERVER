package com.toyproject.tank.server;

import java.io.IOException;
import java.io.OutputStream;

import com.toyproject.tank.Log;

public class WriteSr implements Runnable{

	OutputStream out;
    DataPool dataPool;
    public WriteSr ( OutputStream out, DataPool dataPool)
    {
           this.out = out;
           this.dataPool = dataPool;
    }
       
	@Override
	public void run() {
		try
        {                
            byte[] tmp;
            
            while ( this.out !=null )
            {
            	if(this.dataPool.hasPacket())
                {
            		this.out.write(tmp = this.dataPool.take());
            		Log.getLog().logString("take dataPool : " + tmp[0] + ", " + tmp[1] + ", " + tmp[2] + ", " + tmp[3]);
                }
                
            }                
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }            
    }

}
