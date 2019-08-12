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
    
        static final double UMAX = 3.3;
        static final double ATT = 4;
       
       
    
        private I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
        
		// Get I2C device, ADS1115 I2C addresse 0x48
        private I2CDevice device = bus.getDevice(0x48);
       // private byte[] config;
		
    
        public Voltmetre(int voie) throws I2CFactory.UnsupportedBusNumberException, IOException, InterruptedException{
            
            // Create I2C bus
		  
                 byte[] config = null;
                 
            switch(voie){
                
                case 1:
                       // config = {(byte)0x44, (byte)0x83};
                       config[0] = (byte)0x44;
                       config[1] = (byte)0x83;
                       break;
                  
                case 2:
                       
                       config[0] = (byte)0x44;
                       config[1] = (byte)0x83;
                    
             }
                    device.write(0x01, config, 0, 2);
                    Thread.sleep(500);}
    	
         public boolean mesure(double voltage, double tolerance) throws IOException{
                
                boolean resultat = false;
                double minVoltage = voltage - tolerance;
                double maxVoltage = voltage + tolerance;
                int can =0;
                double [] volt = new double [10];
                double moyenne =0;
                
		// Lecture 2 bytes  data
		// can msb, can lsb
                
		byte[] data = new byte[2];
                
                for (int i=0; i<10; i++){
		this.device.read(0x00, data, 0, 2);
		
		// Conversion
		can  = ((data[0] & 0xFF) * 256) + (data[1] & 0xFF);
                volt[i] = (ATT *(UMAX * can / 32767));
                
                }
                
                // Estimation tension moyenne
                
                for (int i=0; i<10; i++){
                
                    moyenne = moyenne + volt[i] / 10;
                
                }
                
                if (moyenne<maxVoltage && moyenne>minVoltage){
                
                resultat = true;} 

		// Affichage tension moyenne
                
		System.out.println("Tension moyenne mesurée: " + moyenne);
                
               
               return resultat;
               
                }
         
         
         public void etallonnage () throws IOException{
         
             double mesure = 0;
             byte[] data = new byte[2];
             this.device.read(0x00, data, 0, 2);
             int can =0;
             
            // Conversion
             can  = ((data[0] & 0xFF) * 256) + (data[1] & 0xFF);
             mesure = (ATT *(UMAX * can / 32767));
             
         
             System.out.println("Tension mesurée: " + mesure);
         
         }
    
         
}
