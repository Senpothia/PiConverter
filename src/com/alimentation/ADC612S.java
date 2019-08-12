package com.alimentation;

import com.pi4j.io.gpio.PinState;
import static com.pi4j.io.gpio.PinState.HIGH;
import static com.pi4j.io.gpio.PinState.LOW;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/*
 * Programme de pilotage banc de test alimentation 12V DC secourue


import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

/**
 * Classe principale
 * 
 * @author Michel Lopez
 */
public class ADC612S {
    
     //public boolean pap ;
    static PinState statePap;  // indicateur d'état du BP PAP dans bibliothèque pi4j
    static boolean activePap = false;  // Flag indicateur de l'état du BP PAP en boolean

    public static void main(String[] args) throws InterruptedException, Exception {
        
        Test test = new Test();
        
        Voltmetre etalonnageOUT = new Voltmetre(1);
        Voltmetre etalonnageBAT = new Voltmetre(2);
        
        
        test.setup.pap.addListener(new GpioPinListenerDigital() {
            @Override
             public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                
                ADC612S.statePap = event.getState();
                if (ADC612S.statePap == LOW){
                    
                    ADC612S.activePap = false;
                } else {
                    ADC612S.activePap = true;
                }
                //setStatePap(event.getState());
            }

        });
        
        while(true){  // Boucle infinie 
            
        boolean tst = test.readInput(test.setup.test); 
        boolean drt = test.readInput(test.setup.direct); 
       
        if (!tst&drt){  // Activation mode Direct
        
            test.setModePAP(false);
             System.out.println("MODE DIRECT ACTIF");
        }
        
        if (!tst&!drt){ // Activation mode Pas à Pas
            
             test.setModePAP(true);
             System.out.println("MODE PAS A PAS ACTIF");
        }
        
        if (tst&drt){   // Configuration invalide
        
            System.out.println("Erreur de configuration!");
        
        }
        
        
        while (tst && !drt){  // Mode étalonnage
            
           //pap = test.readInput(test.setup.pap); 
           System.out.println("MODE ETALONNAGE ACTIF");
           while(statePap == LOW){
           etalonnageOUT.etallonnage();
           Thread.sleep(1000);
           //pap = test.readInput(test.setup.pap);
           
           }
           
           //pap = false;
            while (statePap == HIGH){
             // Attente relachement BP PAP
           }
           
           while (statePap == LOW){
           etalonnageBAT.etallonnage();
           Thread.sleep(1000);
           //pap = test.readInput(test.setup.pap);
           }
        }
        
        boolean result = test.exec();
        
        }
        
    }

}
   
