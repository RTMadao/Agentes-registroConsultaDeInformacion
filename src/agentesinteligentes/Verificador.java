/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentesinteligentes;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author CIEDUCAR
 */
public class Verificador extends Agent
{
    class ReceptorComportaminento extends SimpleBehaviour
    {
            private boolean fin = false;
            public void action()
            {
                //System.out.println(" Preparandose para recibir");
 
                //Obtiene el primer mensaje de la cola de mensajes
                ACLMessage mensaje = receive();
 
                if (mensaje!= null)
                {
                    System.out.println(getLocalName() + ": acaba de recibir el siguiente mensaje: ");
                    System.out.println(mensaje.toString());
                    fin = true;
                }
            }
            public boolean done()
            {
                return fin;
            }
    }
    protected void setup()
    {
        addBehaviour(new ReceptorComportaminento());
    }
    public void takeDown(){
        System.out.println("Enterado "+getAID().getName());
    }
}
