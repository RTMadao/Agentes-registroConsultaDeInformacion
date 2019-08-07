/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentesinteligentes;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.OperacionesBD;
import vista.Guardar;
import modelo.Comunicar;

/**
 *
 * @author CIEDUCAR
 */
public class Verificador extends Agent{
    
    static Agent esteAgente;
    
    protected void setup(){
        System.out.println("vamos a verificar");
        Verificador.esteAgente = this;
        addBehaviour(new validarID());
    }
 
    class validarID extends CyclicBehaviour{

        @Override
        public void action() {
            String datoAVerificar="nada";
            boolean existe = false;
            ACLMessage msg = receive();
            if (msg != null) {
                OperacionesBD db = OperacionesBD.getInstance();
                try {
                    datoAVerificar = (String) msg.getContentObject();
                    System.out.println("verificando..."+datoAVerificar);
                    existe = db.existeRegistro(datoAVerificar);
                    addBehaviour(new Comunicar("Capturar", existe,Verificador.esteAgente));
                } catch (UnreadableException ex) {
                    Logger.getLogger(Verificador.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Verificador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                block();
            } 
        }
    }
}
