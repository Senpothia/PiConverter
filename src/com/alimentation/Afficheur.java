
package com.alimentation;

/**
 * Gestion des affichages 
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
    
    
    void attente(){
    
        System.out.println("<--  En attente d'un nouveau test  -->");
        
    }
    
    void resultatFinal(int resultatTest){
    
    switch (resultatTest){
        
            case 0:  // Test OK
                
                 break;
                 
            case 1: // Test NOK
                 
        }
    
    }
    
}
