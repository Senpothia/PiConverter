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
   // private TesterRelais testerRelais;
    private boolean secteur = false;
    private boolean batterie = false;
    private boolean comBatterie = false;
    private boolean charge = false;
    
    public Etape(boolean stateRelais, boolean secteur, boolean batterie, boolean comBatterie, boolean charge, double voltage, double tolerance) throws I2CFactory.UnsupportedBusNumberException, IOException, InterruptedException{
       
        this.voltmetre = new Voltmetre();
        //this.testerRelais = new TesterRelais();
        this.activeRelais = stateRelais;
        this.voltage = voltage;
        this.tolerance = tolerance;
        this.secteur = secteur;
    }
    
        public boolean  exec(boolean state) throws Exception{
        
        boolean resultat = false;
        boolean stateRead = false;
        
        voltmetre.mesure(voltage, tolerance);
        //stateRead = testerRelais.test(state);
        //Mesure de tension
        
       
        return resultat;
        
    }

    public boolean isSecteur() {
        return secteur;
    }

    public boolean isBatterie() {
        return batterie;
    }

    public boolean isComBatterie() {
        return comBatterie;
    }

    public boolean isCharge() {
        return charge;
    }

    public boolean isActiveRelais() {
        return activeRelais;
    }
       
}
