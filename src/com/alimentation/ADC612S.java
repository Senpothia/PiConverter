package com.alimentation;

import com.pi4j.io.gpio.PinState;
import static com.pi4j.io.gpio.PinState.HIGH;
import static com.pi4j.io.gpio.PinState.LOW;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/*
 * Programme de pilotage banc de test pour alimentations via carte Raspberry


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
        
        Voltmetre etalonnageOUT = new Voltmetre();
        Voltmetre etalonnageBAT = new Voltmetre();
        
        
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
        
            test.setModePAP(false);  // Desactivation mode pas à pas dans la classe Test
             System.out.println("MODE DIRECT ACTIF");
        }
        
        if (!tst&!drt){ // Activation mode Pas à Pas
            
             test.setModePAP(true);  // Activation mode pas à pas dans la classe Test
             System.out.println("MODE PAS A PAS ACTIF");
        }
        
        if (tst&drt){   // Configuration invalide
        
            System.out.println("Erreur de configuration!");
        
        }
        
        
        while (tst && !drt){  // Mode étalonnage
            
          
           System.out.println("MODE ETALONNAGE ACTIF");
           while(statePap == LOW){
           etalonnageOUT.etallonnage(1);
           Thread.sleep(1000);
           
           }
           
            while (statePap == HIGH){
             // Attente relachement BP PAP
           }
           
           while (statePap == LOW){
           etalonnageBAT.etallonnage(2);
           Thread.sleep(1000);
           }
           
           while (statePap == HIGH){
             // Attente relachement BP PAP
           }
        }
        
        boolean result = test.exec();
        
        }
        
    }

}
   
