/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentesinteligentes;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

/**
 *
 * @author carlo
 */
public class Listador {
    //TickerBehaviours
    protected void setup(){
        
    }
    
    class ActualizarLista extends TickerBehaviour{

        public ActualizarLista(Agent a, long period) {
            super(a, period);
        }

        @Override
        protected void onTick() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}
