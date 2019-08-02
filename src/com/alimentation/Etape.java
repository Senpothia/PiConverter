/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alimentation;

/**
 *
 * @author Michel
 */
public class Etape {
    
    private Voltmetre voltmetre = new Voltmetre();
    private ContactMetre contactMetre = new ContactMetre();
    private boolean activeRelais = false;
    private double voltage = 0;
    private double tolerance = 0.15;
    
    public Etape(boolean stateRelais, double voltage, double tolerance){
    
        this.activeRelais = activeRelais;
        this.voltage = voltage;
        this.tolerance = tolerance;
    }
    
    private int exec(){
        
        int resultat = 0;
        
        boolean resultVoltage = voltmetre.mesure(this.voltage, this.tolerance);   
        boolean resultContact = contactMetre.test(this.activeRelais);
        return resultat; }
    
    
}
