package com.alimentation;


import com.alimentation.Setup;

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
    
    private Setup setup;
   
    private Etape etape1 =  new Etape(false, 12, 0.15);
    private Etape etape2 =  new Etape(true, 12, 0.15);
    private Etape etape3 =  new Etape(false, 12, 0.15);
    
    
    boolean exec(){
    
        boolean result = false;
        
        // Activation led PROG
        
        setup.activeOutput(true, setup.pinProg);
        
        // Commutation secteur
        
        setup.activeOutput(true, setup.pinPhase);
        setup.activeOutput(true, setup.pinNeutre);
        
        
        // Executer etape 1
        
        // Test résultat Etape 1
        
        
        // Affichage résultat final
        
        
        return result;
    
    }
    
}
