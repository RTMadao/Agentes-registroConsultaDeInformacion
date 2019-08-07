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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Persona;
import persistencia.OperacionesBD;
import vista.Listar;

/**
 *
 * @author Estudiante
 */
public class Guardador extends Agent{
    
    protected void setup(){
        System.out.println("vamos a guardar");
        addBehaviour(new Guardar());
    }  
    
    class Guardar extends CyclicBehaviour{

        @Override
        public void action() {
            ACLMessage msg = receive();
            if (msg != null) {
                OperacionesBD db = OperacionesBD.getInstance();
                try {
                    Persona datos = (Persona) msg.getContentObject();
                    System.out.println("guardando registro de..."+datos.getNombre());
                    db.guardar(datos);
                } catch (UnreadableException ex) {
                    Logger.getLogger(Verificador.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Guardador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                block();
            } 
        }
    }
}
