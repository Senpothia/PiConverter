/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alimentation;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalInput;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;


import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 *
 * @author Michel
 */
public class Etape {
    
   
   // private ContactMetre contactMetre = new ContactMetre();
    private boolean activeRelais = false;
    private double voltage = 0;
    private double tolerance = 0.15;
    
    public Etape(boolean stateRelais, double voltage, double tolerance){
    
        this.activeRelais = stateRelais;
        this.voltage = voltage;
        this.tolerance = tolerance;
    }
    
    private boolean  exec(){
        
        boolean resultat = false;
        return resultat;
        
    }
    
      public void voltmetre() throws Exception
	{
		// Create I2C bus
		I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
		// Get I2C device, ADS1115 I2C address is 0x48(72)
		I2CDevice device = bus.getDevice(0x48);
		
		byte[] config = {(byte)0x84, (byte)0x83};
		// Select configuration register
		// AINP = AIN0 and AINN = AIN1, +/- 2.048V, Continuous conversion mode, 128 SPS
		device.write(0x01, config, 0, 2);
		Thread.sleep(500);
        
		// Read 2 bytes of data
		// raw_adc msb, raw_adc lsb
		byte[] data = new byte[2];
                for (int i=0; i<10; i++){
		device.read(0x00, data, 0, 2);
		
		// Convert the data
		int raw_adc = ((data[0] & 0xFF) * 256) + (data[1] & 0xFF);
		if (raw_adc > 32767)
		{
			raw_adc -= 65535;
		}

		// Output data to screen
		System.out.printf("Digital Value of Analog Input : %d %n", raw_adc);
                }
        }
      
      public void contactTest() throws InterruptedException{
          
          System.out.println("<--Pi4J--> GPIO Listen Example ... started.");

        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        // provision gpio pin #02 as an input pin with its internal pull down resistor enabled
        final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);

        // set shutdown state for this input pin
        myButton.setShutdownOptions(true);

        // create and register gpio pin listener
        myButton.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // display pin state on console
                System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
            }

        });

        System.out.println(" ... complete the GPIO #02 circuit and see the listener feedback here in the console.");

        // keep program running until user aborts (CTRL-C)
        while(true) {
            Thread.sleep(500);
        }

        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        // gpio.shutdown();   <--- implement this method call if you wish to terminate the Pi4J GPIO controller
    }
      
      
}
