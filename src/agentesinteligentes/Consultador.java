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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Comunicar;
import modelo.Persona;
import persistencia.OperacionesBD;

/**
 *
 * @author carlo
 */
public class Consultador extends Agent{
    
    static Agent esteAgente;
    
    protected void setup(){
        System.out.println("a consultar");
        Consultador.esteAgente = this;
        addBehaviour(new ConsultarID());
    }
    
    class ConsultarID extends CyclicBehaviour{

        @Override
        public void action() {
            String IDConsultar="nada";
            ACLMessage msg = receive();
            if (msg != null) {
                OperacionesBD db = OperacionesBD.getInstance();
                try {
                    IDConsultar = (String) msg.getContentObject();
                    if(IDConsultar == null){
                        ArrayList<Persona>lista = db.recuperarTodas();
                        addBehaviour(new Comunicar("Listar", lista, Consultador.esteAgente));
                    }else{
                        Persona registro = db.buscar(IDConsultar);
                        Capturador.ventanaConsulta.datos = registro;
                        Capturador.ventanaConsulta.llenar();
                    }
                } catch (UnreadableException ex) {
                    Logger.getLogger(Consultador.class.getName()).log(Level.SEVERE, null, ex);
                }catch (SQLException ex) {
                        Logger.getLogger(Consultador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                block();
            } 
        }
        
    }
    
}
