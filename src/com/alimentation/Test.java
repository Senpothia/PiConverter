package com.alimentation;


import com.alimentation.Setup;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import java.io.IOException;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Michel
 */
public class Test {

    private static Object device;
    
    private Setup setup = new Setup();
   
    private Etape etape1 =  new Etape(false, 12, 0.15);
    private Etape etape2 =  new Etape(true, 12, 0.15);
    private Etape etape3 =  new Etape(false, 12, 0.15);
    
    
    boolean exec() throws InterruptedException, Exception{
    
        boolean result = false;
        
       System.out.println("Lancement séquence de test");
        // Activation led PROG
        
        setup.activeOutput(true, setup.pinProg);
        
        // Commutation secteur
        
        setup.activeOutput(true, setup.pinPhase);
        setup.activeOutput(true, setup.pinNeutre);
        
        
        // Executer etape 1
        
        
        // Test résultat Etape 1
        
        
        // Affichage résultat final
        Thread.sleep(5000);
        System.out.println("Fin séquence de test");
        setup.pinProg.setShutdownOptions(true, PinState.LOW);
        setup.pinPhase.setShutdownOptions(true, PinState.LOW);
        setup.pinNeutre.setShutdownOptions(true, PinState.LOW);
       
        etape1.voltmetre();
        setup.gpio.shutdown();
        return result;
    
    }
    
    
      
        
                
        
    
}
