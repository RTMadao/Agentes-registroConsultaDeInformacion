/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentesinteligentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.introspection.AddedBehaviour;
import javax.swing.JFrame;
import modelo.Comunicar;
import vista.Listar;

/**
 *
 * @author carlo
 */
public class Listador extends Agent{
    
    public static JFrame ventana;
    static Agent esteAgente;
    
    //TickerBehaviours
    protected void setup(){
        System.out.println("vamos a listar");
        Listador.ventana = new Listar();
        Listador.ventana.setVisible(true);
        Listador.esteAgente = this;
        addBehaviour(new ActualizarLista());
    }
    
    class ActualizarLista extends CyclicBehaviour{

        @Override
        public void action() {
            if(Listador.ventana.isVisible()){
                addBehaviour(new Comunicar("Consultar", "listar", Listador.esteAgente));
            }
        }
    }
}
