package com.alimentation;


import com.alimentation.Setup;
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
    
    
    boolean exec(){
    
        boolean result = false;
        
        // Activation led PROG
        
        setup.activeOutput(true, setup.pinProg);
        
        // Commutation secteur
        
      //  setup.activeOutput(true, setup.pinPhase);
       // setup.activeOutput(true, setup.pinNeutre);
        
        
        // Executer etape 1
        
        
        // Test résultat Etape 1
        
        
        // Affichage résultat final
        
        System.out.println("Essai");
        return result;
    
    }
    
    
      
        
                
        
    
}
