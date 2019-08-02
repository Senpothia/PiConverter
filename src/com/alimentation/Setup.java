package com.alimentation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

/**
 * Classe de configuration des GPIO
 * @author Michel
 */
public class Setup {
    
      // gpio controller
        final GpioController gpio = GpioFactory.getInstance();
        
      // Définnition des entrées
        
        final GpioPinDigitalOutput pinProg = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Prog", PinState.LOW);
        final GpioPinDigitalOutput pinOk = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "OK", PinState.LOW);
        final GpioPinDigitalOutput pinKo = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "KO", PinState.LOW);
        final GpioPinDigitalOutput pinNeutre = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Neutre", PinState.LOW);
        final GpioPinDigitalOutput pinPhase = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "Phase", PinState.LOW);
        final GpioPinDigitalOutput pinCmdeBat = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "CmdeBat", PinState.LOW);
        final GpioPinDigitalOutput pinChargeBat = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "ChargeBat", PinState.LOW);
        final GpioPinDigitalOutput pinNO = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "ChargeBat", PinState.LOW);
        final GpioPinDigitalOutput pinNF = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_09, "ChargeBat", PinState.LOW);
        
        // Définition des sorties
        
         final GpioPinDigitalInput marcheDrt = gpio.provisionDigitalInputPin(RaspiPin.GPIO_10, PinPullResistance.PULL_DOWN);
         final GpioPinDigitalInput marcheGch = gpio.provisionDigitalInputPin(RaspiPin.GPIO_11, PinPullResistance.PULL_DOWN);
         final GpioPinDigitalInput pap = gpio.provisionDigitalInputPin(RaspiPin.GPIO_12, PinPullResistance.PULL_DOWN);
         final GpioPinDigitalInput direct = gpio.provisionDigitalInputPin(RaspiPin.GPIO_13, PinPullResistance.PULL_DOWN);
         final GpioPinDigitalInput test = gpio.provisionDigitalInputPin(RaspiPin.GPIO_14, PinPullResistance.PULL_DOWN);
        
         private void activeOutput(boolean state, GpioPinDigitalOutput output) {}
         
         private void activeInput(boolean state, GpioPinDigitalInput input) {}
       
    
}
