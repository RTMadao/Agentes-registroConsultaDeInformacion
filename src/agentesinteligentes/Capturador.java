/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentesinteligentes;

import jade.core.*;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import modelo.Comunicar;
import modelo.Persona;
import vista.Consultar;
import vista.Guardar;

/**
 *
 * @author CIEDUCAR
 */
public class Capturador extends Agent{
    
    public static JFrame ventana;
    public static boolean b = false;
    static Agent esteAgente;
    
    protected void setup(){
        Capturador.ventana = new Guardar();
        Capturador.esteAgente = this;
        System.out.println("vamos a capturar");
        addBehaviour(new DatosParaGuardar());
        
    }
    
    class DatosParaGuardar extends CyclicBehaviour{
        
        private Guardar ventana;
        
        
        public DatosParaGuardar() {
            this.ventana = (Guardar) Capturador.ventana;
        }

        @Override
        public void action() {
            
            if(Capturador.b){
                Persona datos = ventana.getDatos();
                System.out.println("capturando");
                System.out.println("enviando para verificar");
                addBehaviour(new Comunicar("Verificar", datos.getDocumento(),Capturador.esteAgente));
                Capturador.b = false;
            }
            ACLMessage msg = receive();
            if (msg != null) {
                Persona datos = ventana.getDatos();
                try {
                    boolean existe = (boolean) msg.getContentObject();
                    System.out.println(existe);
                    if(existe){
                        System.out.println("sorry pai, el vale ya esta registrado");
                    }else{
                        System.out.println("enviando para guardar");
                        addBehaviour( new Comunicar("Guardar",datos,Capturador.esteAgente));
                        this.ventana.setDatos(null);
                    }
                } catch (UnreadableException ex) {
                    Logger.getLogger(Capturador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
    class DatosParaConsultar extends SimpleBehaviour{

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

