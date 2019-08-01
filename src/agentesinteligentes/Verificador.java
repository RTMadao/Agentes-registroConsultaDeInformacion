/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentesinteligentes;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CIEDUCAR
 */
public class Verificador extends Agent{
    protected void setup(){
        
    }
    
    class validarID extends SimpleBehaviour{

        @Override
        public void action() {
            String datoAVerificar;
            ACLMessage msg = receive();
            if (msg != null) {
                try {
                    datoAVerificar = (String) msg.getContentObject();
                } catch (UnreadableException ex) {
                    Logger.getLogger(Verificador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                block();
            } 
        }

        @Override
        public boolean done() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}
