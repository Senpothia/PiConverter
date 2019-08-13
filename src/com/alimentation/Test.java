package com.alimentation;

import com.alimentation.Setup;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinState;
import static com.pi4j.io.gpio.PinState.HIGH;
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger;
import com.pi4j.io.gpio.trigger.GpioSetStateTrigger;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import java.io.IOException;
import java.util.concurrent.Callable;


/**
 * Classe de gestion de la séquence de test
 * 
 * @author Michel
 */
public class Test {

    private static Object device;

    public Setup setup = new Setup();
    private Afficheur afficheur = new Afficheur();
    private Etape[] listeEtape;
  

    private boolean marcheDrt = false;
    private boolean marcheGch = false;
    private boolean modePAP = false;
    private boolean testOn = false;
    boolean[] resultatEtape = new boolean[3];

    public Test() throws I2CFactory.UnsupportedBusNumberException, IOException, InterruptedException {

        listeEtape = new Etape[3];
        
        //  public Etape(boolean stateRelais, boolean secteur, boolean batterie, boolean comBatterie, boolean charge, double voltage, double tolerance)

        listeEtape[0] = new Etape(false, true, false, false, false, 12, 0.15, 0, 0.15);   // Mesures à vide
        listeEtape[1] = new Etape(true, true, true, false, true, 12, 0.15, 12, 0.15);    // Mesures en charge avec batterie
        listeEtape[2] = new Etape(false, false, false, true, true,10, 0.15, 12, 0.15);   // Mesures en charge avec batterie hors secteur

    }

    boolean exec() throws InterruptedException, Exception {

        boolean result = false;
        marcheDrt = false;
        marcheGch = false;
        testOn = false;

        // Attente démarrage - Marche DRT et marche GCH acrifs
        setup.marcheDrt.addTrigger(new GpioCallbackTrigger(new Callable<Void>() {
            public Void call() throws Exception {
                System.out.println(" --> GPIO MarcheDrt TRIGGER CALLBACK RECEIVED ");
                marcheDrt = !marcheDrt;
                return null;
            }
        }));

        setup.marcheGch.addTrigger(new GpioCallbackTrigger(new Callable<Void>() {
            public Void call() throws Exception {
                System.out.println(" --> GPIO MarcheGch TRIGGER CALLBACK RECEIVED ");
                marcheGch = !marcheGch;
                return null;
            }
        }));
        afficheur.attente();
        while (!marcheDrt & !marcheGch | modePAP){
        
        // attente lancement du test par activation des boutons marche ou mode pap actif
        }
        
        testOn = true;
        int i = 0;
        
        while (marcheDrt & marcheGch & i < listeEtape.length & testOn & !modePAP | modePAP & ADC612S.activePap & testOn & i < listeEtape.length) {  // Bouche d'execution du test par étapes

            if (i == 0) {   // Affichage message de démarrage
                setup.activeOutput(true, setup.pinProg);

            } 

            // Executer etape i
            if (listeEtape[i].isSecteur()) {  // Test de commutation secteur
                // Commutation secteur

                setup.activeOutput(true, setup.pinPhase);
                setup.activeOutput(true, setup.pinNeutre);

            } else {

                setup.activeOutput(false, setup.pinPhase);
                setup.activeOutput(false, setup.pinNeutre);

            }
            
             if (listeEtape[i].isBatterie()) {  // Test de commutation batterie
                // Commutation secteur

                setup.activeOutput(true, setup.pinChargeBat);

            } else {

                setup.activeOutput(false, setup.pinChargeBat);
            }
             
             if (listeEtape[i].isComBatterie()) {  // Test de commutation batterie
                // Commutation secteur

                setup.activeOutput(true, setup.pinCmdeBat);

            } else {

                setup.activeOutput(false, setup.pinCmdeBat);
            }
            
             if (listeEtape[i].isCharge()) {  // Test de commutation batterie
                // Commutation secteur

                setup.activeOutput(true, setup.pinCharge);

            } else {

                setup.activeOutput(false, setup.pinCharge);
            }

           boolean  resultatVoltage = listeEtape[i].testVoltageOUT();                          // Test de la tension Vout
           boolean  resultatVoltageBat = listeEtape[i].testVoltageBat();                                                                          // Test de la tension batterie
           boolean  resultatRelais = testRelais(listeEtape[i].isActiveRelais());            // Test du relais
            
            if (resultatVoltage && resultatVoltageBat && resultatRelais) {
                
                resultatEtape[i] = true; }
            
            if (!resultatEtape[i]) {   // si résultat NOK

                testOn = false;        // Avortement du test
                
            } else {                   // Si résultat OK
 
                i++;                   // Incrémentation pour étape suivante
            }

        }           // Fin boucle while
        
        setup.activeOutput(false, setup.pinPhase);      // Déactivation phase
        setup.activeOutput(false, setup.pinNeutre);     // Déactivation neutre
        setup.activeOutput(false, setup.pinProg);       // Déactivation led Progression

        if (testOn & i < listeEtape.length & !modePAP) {  // Test interrompu par relachement des boutons marche

            System.out.println("TEST INTERROMPU");
           
        } 
        
        
        if (!testOn) {   // Test avorté par non conformité à une étape i
            
             System.out.println("TEST NON CONFORME : Etape: " + (i + 1));
                   while (marcheDrt && marcheGch)   {
                        setup.activeOutput(true, setup.pinKo);          // Activation led rouge
                   }
        } 
        
        if (testOn && i == listeEtape.length){      // Test executé jusqu'à la dernière étape
            
          for (boolean res : resultatEtape) {  
            result = result & res;
        }
           if (result) {
               
            System.out.println("TEST CONFORME");
            
            while (marcheDrt && marcheGch)   {
                setup.pinOk.setShutdownOptions(true, PinState.LOW);  // Activation led verte tant que les boutons marche sont actifs
           
            }
        } else {        // Test non conforme
           
                 System.out.println("TEST NON CONFORME");
                 
                   while (marcheDrt && marcheGch)   {
                        setup.activeOutput(true, setup.pinKo);          // Activation led rouge
                   }
           }
        }

        // Affichage résultat final
        System.out.println("Fin séquence de test");

        // Gestion des IO en fin de test
        setup.pinProg.setShutdownOptions(true, PinState.LOW);
        setup.pinPhase.setShutdownOptions(true, PinState.LOW);
        setup.pinNeutre.setShutdownOptions(true, PinState.LOW);
        setup.pinKo.setShutdownOptions(true, PinState.LOW);
        setup.pinOk.setShutdownOptions(true, PinState.LOW);

        setup.gpio.shutdown();

        // reset flag des boutons marche
        marcheDrt = false;
        marcheGch = false;
        testOn = false;
       
        return result;

    }
    
    public boolean testRelais(boolean state) {
             
            GpioPinDigitalInput input1;
            input1 = setup.NO;
            GpioPinDigitalInput input2;
            input2 = setup.NF;
            boolean stateRead1 = false;
            boolean stateRead2 = false;
            boolean resultat = false;
            
            PinState pinState1 = input1.getState();
            PinState pinState2 = input1.getState();
            
             if (pinState1 == HIGH){   // Test contact NO
                 
                 stateRead1= true;
             
             } else {
                 
                 stateRead1 = false;
             }
             
             PinState pinState = input2.getState();
            
             if (pinState2 == HIGH){  // Test contact NF
                 
                 stateRead2= true;
             
             } else {
                 
                 stateRead2 = false;
             }
             
             if (stateRead1 & state & !stateRead2 | !stateRead1 & !state & stateRead2 ){
             
                 resultat = true;
             } else {
                 
                 resultat = false;
             }
             
             return resultat;
}

    public void setModePAP(boolean modePAP) {
        this.modePAP = modePAP;
    }

   
     public boolean readInput(GpioPinDigitalInput input) {
             
           
            boolean stateRead = false;
            
            PinState pinState = input.getState();
            
             if (pinState == HIGH){
                 
                 stateRead = true;
             
             } else {
                 
                 stateRead = false;
             }
             return stateRead;
}
    
    
}
