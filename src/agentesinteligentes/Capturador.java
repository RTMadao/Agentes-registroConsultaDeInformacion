/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentesinteligentes;

import jade.core.*;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CIEDUCAR
 */
public class Capturador extends Agent{
    protected void setup(){
        
    }
    
    class DatosParaGuardar extends SimpleBehaviour{

        @Override
        public void action() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean done() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    class DatosParaConsultar extends SimpleBehaviour{

        @Override
        public void action() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean done() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    class Comunicar extends SimpleBehaviour{
        
        private String destinatario;
        private Serializable mensaje;
        private boolean finaliza = false;
        
        public Comunicar(String d, Serializable m){
            this.destinatario=d;
            this.mensaje=m;
        }

        @Override
        public void action() {
            try {
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.addReceiver(new AID(this.destinatario, AID.ISLOCALNAME));
                msg.setLanguage("English");
                msg.setContentObject(this.mensaje);
                send(msg);
                
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
}

