/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alimentation;

/**
 *
 * @author miguel
 */
public class Afficheur {
    
    void start (){
    
        System.out.println("<--  Lancement de la séquence de test  -->");
    }
    
    
    void end(){
    
        System.out.println("<--  Fin de la séquence de test  -->");
        
    }
    
    void resultatFinal(int resultatTest){
    
    switch (resultatTest){
        
            case 0:  // Test OK
                
                 break;
                 
            case 1: // Test NOK
                 
        }
    
    }
    
}
