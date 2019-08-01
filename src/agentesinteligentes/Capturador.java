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

/**
 *
 * @author CIEDUCAR
 */
public class Capturador extends Agent{

    /**
     * @param args the command line arguments
     */
    public void setup(){
        // TODO code application logic here
        System.out.println("Iniciando "+getAID().getName()+" : "+getAID());
         // Añade un comportamiento
        addBehaviour(new Disparar());
    }
    
   /* public void takeDown(){
        System.out.println("Termine...fui asesinado "+getAID().getName());
    }*/
    
    // Definición de un comportamiento
    private class Disparar extends Behaviour{
        private int estado = 0;
 
        // Función que realiza MiComportamiento
        public void action(){            
            switch(estado){
                case 0: System.out.println("Preparando arma"); break;
                case 1: System.out.println("Disparo 1");break;
                case 2: System.out.println("Disparo 2"); break;
                case 3: System.out.println("Disparo 3"); break;
                case 4: System.out.println("Disparo 4"); break;
                case 5: System.out.println("Disparo 5"); break;
                case 6: addBehaviour(new Comunicar());System.out.println("Comunicando");break;
                case 7:{
                        System.out.println("Verica cadaver");
                        //myAgent.doDelete();
                        break;
                }
                default:
                    addBehaviour(new Comunicar());System.out.println("Comunicando");break;
                   
            }
            estado++;            
        }
 
        // Comprueba si el comportamiento ha finalizado.
        public boolean done(){
            return (estado > 700);
        }
    }
    class Comunicar extends SimpleBehaviour
    {
        boolean fin = false;
        public void action()
        {
            System.out.println(getLocalName() +": marcando al celular del jefe");
            AID id = new AID();
            id.setLocalName("jefe");
 
        // Creación del objeto ACLMessage
            ACLMessage mensaje = new ACLMessage(ACLMessage.REQUEST);
 
        //Rellenar los campos necesarios del mensaje
            mensaje.setSender(getAID());
            mensaje.setLanguage("Español");
            mensaje.addReceiver(id);
            mensaje.setContent("Hola, Tarea realizada....chulo listo");
 
        //Envia el mensaje a los destinatarios
            send(mensaje);
 
            System.out.println(getLocalName() +": Enviando a jefe");
            System.out.println(mensaje.toString());
            fin = true;
        }
 
        public boolean done()
        {
            return fin;
        }
    }
}

