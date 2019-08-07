/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import agentesinteligentes.Capturador;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlo
 */
public class Comunicar extends SimpleBehaviour{
        
        private String destinatario;
        private Serializable mensaje;
        private boolean finaliza = false;
        private Agent emisor;
        
        public Comunicar(String d, Serializable m, Agent a){
            this.destinatario=d;
            this.mensaje=m;
            this.emisor=a;
        }

        @Override
        public void action() {
            try {
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.addReceiver(new AID(this.destinatario, AID.ISLOCALNAME));
                msg.setLanguage("English");
                msg.setContentObject(this.mensaje);
                this.emisor.send(msg);
                
                this.finaliza = true;
            } catch (IOException ex) {
                Logger.getLogger(Capturador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public boolean done() {
            return this.finaliza;
        }
    }
