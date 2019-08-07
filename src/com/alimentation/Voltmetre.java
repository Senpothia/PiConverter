/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alimentation;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import java.io.IOException;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

/**
 *
 * @author Michel
 */
public class Voltmetre {
    
        private I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
		// Get I2C device, ADS1115 I2C address is 0x48(72)
        private I2CDevice device = bus.getDevice(0x48);
		
    
        public Voltmetre() throws I2CFactory.UnsupportedBusNumberException, IOException, InterruptedException{
            
            // Create I2C bus
		
		byte[] config = {(byte)0x84, (byte)0x83};
		// Select configuration register
		// AINP = AIN0 and AINN = AIN1, +/- 2.048V, Continuous conversion mode, 128 SPS
		device.write(0x01, config, 0, 2);
		Thread.sleep(500);
        }
        
    
    	
         public void mesure() throws IOException{

		// Read 2 bytes of data
		// raw_adc msb, raw_adc lsb
		byte[] data = new byte[2];
               // for (int i=0; i<10; i++){
		this.device.read(0x00, data, 0, 2);
		
		// Convert the data
		int raw_adc = ((data[0] & 0xFF) * 256) + (data[1] & 0xFF);
                    if (raw_adc > 32767){
			raw_adc -= 65535;
                    }

		// Output data to screen
		System.out.printf("Digital Value of Analog Input : %d %n", raw_adc);
               // }
    
                }
    
    
}
