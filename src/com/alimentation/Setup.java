package com.alimentation;

import java.util.concurrent.Callable;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import static com.pi4j.io.gpio.PinState.HIGH;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger;
import com.pi4j.io.gpio.trigger.GpioPulseStateTrigger;
import com.pi4j.io.gpio.trigger.GpioSetStateTrigger;
import com.pi4j.io.gpio.trigger.GpioSyncStateTrigger;

/**
 * Classe de configuration des GPIO
 * @author Michel
 */
        public class Setup {
        
      // gpio controller
        final GpioController gpio = GpioFactory.getInstance();
        
      // Définnition des sorties
        
        final GpioPinDigitalOutput pinProg = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Prog", PinState.LOW);
        final GpioPinDigitalOutput pinOk = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "OK", PinState.LOW);
        final GpioPinDigitalOutput pinKo = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "KO", PinState.LOW);
        final GpioPinDigitalOutput pinNeutre = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Neutre", PinState.LOW);
        final GpioPinDigitalOutput pinPhase = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "Phase", PinState.LOW);
        final GpioPinDigitalOutput pinCmdeBat = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "CmdeBat", PinState.LOW);
        final GpioPinDigitalOutput pinChargeBat = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "ChargeBat", PinState.LOW);
        final GpioPinDigitalOutput pinCharge = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "Charge", PinState.LOW);
       
        
        // Définition des entrées
        
         final GpioPinDigitalInput marcheDrt = gpio.provisionDigitalInputPin(RaspiPin.GPIO_10, PinPullResistance.PULL_DOWN);
         final GpioPinDigitalInput marcheGch = gpio.provisionDigitalInputPin(RaspiPin.GPIO_11, PinPullResistance.PULL_DOWN);
         final GpioPinDigitalInput pap = gpio.provisionDigitalInputPin(RaspiPin.GPIO_12, PinPullResistance.PULL_DOWN);
         final GpioPinDigitalInput direct = gpio.provisionDigitalInputPin(RaspiPin.GPIO_13, PinPullResistance.PULL_DOWN);
         final GpioPinDigitalInput test = gpio.provisionDigitalInputPin(RaspiPin.GPIO_14, PinPullResistance.PULL_DOWN);
         final GpioPinDigitalInput NO = gpio.provisionDigitalInputPin(RaspiPin.GPIO_15, PinPullResistance.PULL_DOWN);
         final GpioPinDigitalInput NF = gpio.provisionDigitalInputPin(RaspiPin.GPIO_16, PinPullResistance.PULL_DOWN);
        
        
         void activeOutput(boolean state, GpioPinDigitalOutput output) {
             
             if (state){
                 
                 output.high();
                 
             
             } else {output.low();}
        
         }
         
}
