package com.alimentation;

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

    public static void main(String[] args) throws InterruptedException, Exception {
        
        Test test = new Test();
        
        while(true){
   
        boolean result = test.exec();
        
        }
        
    }
}
   
