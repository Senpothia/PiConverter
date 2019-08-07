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
import java.io.IOException;

/**
 *
 * @author Michel
 */
public class Etape {
    
   
   // private ContactMetre contactMetre = new ContactMetre();
    private boolean activeRelais = false;
    private double voltage = 0;
    private double tolerance = 0.15;
    private Voltmetre voltmetre;
    private TesterRelais testerRelais;
    
    public Etape(boolean stateRelais, double voltage, double tolerance) throws I2CFactory.UnsupportedBusNumberException, IOException, InterruptedException{
       
        this.voltmetre = new Voltmetre();
        this.testerRelais = new TesterRelais();
        this.activeRelais = stateRelais;
        this.voltage = voltage;
        this.tolerance = tolerance;
    }
    
        public boolean  exec() throws Exception{
        
        boolean resultat = false;
        
        // Mesure de tension
        
       
        return resultat;
        
    }
    
      
      
      
      
      
}
