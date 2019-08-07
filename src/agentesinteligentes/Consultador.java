/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentesinteligentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlo
 */
public class Consultador extends Agent{
    protected void setup(){
        System.out.println("a consultar");
        addBehaviour(new ConsultarID());
    }
    
    class ConsultarID extends CyclicBehaviour{

        @Override
        public void action() {
            String IDConsultar="nada";
            ACLMessage msg = receive();
            if (msg != null) {
                try {
                    IDConsultar = (String) msg.getContentObject();
                    
                    ACLMessage respuesta = msg.createReply();
                    String persona = "si";
                    if(persona != null){
                        respuesta.setPerformative(ACLMessage.PROPOSE);
                        respuesta.setContentObject("existe");
                    }else{
                        respuesta.setPerformative(ACLMessage.REFUSE);
                        respuesta.setContentObject("no existe");
                    }
                    send(respuesta);
                    
                } catch (UnreadableException ex) {
                    Logger.getLogger(Verificador.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Consultador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                block();
            } 
        }
        
    }
    
}
