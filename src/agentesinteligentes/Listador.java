/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentesinteligentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.introspection.AddedBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import modelo.Comunicar;
import modelo.Persona;
import vista.Listar;

/**
 *
 * @author carlo
 */
public class Listador extends Agent{
    
    public static Listar ventana;
    static Agent esteAgente;
    
    //TickerBehaviours
    protected void setup(){
        System.out.println("vamos a listar");
        addBehaviour(new ActualizarLista());
        Listador.esteAgente = this;
        Listador.ventana = new Listar((Listador)Listador.esteAgente);
        Listador.ventana.setVisible(true);
    }
    
    public void disparar(){
        addBehaviour(new SolicitarLista());
    }
    
    class SolicitarLista extends SimpleBehaviour{
        
        boolean echo = false;
        
        @Override
        public void action() {
            System.out.println("solicitando");
            addBehaviour(new Comunicar("Consultar", null, Listador.esteAgente));
            echo = true;
        }

        @Override
        public boolean done() {
            return echo == true;
        }
        
    } 
    
    class ActualizarLista extends CyclicBehaviour{

        @Override
        public void action() {
            ACLMessage msg = receive();
            if (msg != null) {
                try {
                    System.out.println("actualizando");
                    ArrayList<Persona> lista = (ArrayList<Persona>) msg.getContentObject();
                    Listador.ventana.listaRegistros = lista;
                    Listador.ventana.llenarTabla();
                } catch (UnreadableException ex) {
                    Logger.getLogger(Listador.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }else{
                block();
            }
        }
    }
}
