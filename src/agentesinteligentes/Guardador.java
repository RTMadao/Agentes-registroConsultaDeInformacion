/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentesinteligentes;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import vista.Listar;

/**
 *
 * @author Estudiante
 */
public class Guardador extends Agent{
    
    protected void setup(){
        Listar ventanaGuardar = new Listar();
        ventanaGuardar.setVisible(true);
    }  
    
    class Guardar extends SimpleBehaviour{

        @Override
        public void action() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean done() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}
