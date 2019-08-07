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
import javax.swing.JOptionPane;
import modelo.Comunicar;
import modelo.Persona;
import vista.Consultar;
import vista.Guardar;

/**
 *
 * @author CIEDUCAR
 */
public class Capturador extends Agent{
    
    public static Guardar ventana;
    public static Consultar ventanaConsulta;
    public static boolean b = false;
    static Agent esteAgente;
    
    protected void setup(){
        Capturador.esteAgente = this;
        Capturador.ventana = new Guardar((Capturador) Capturador.esteAgente);
        Capturador.ventanaConsulta = new Consultar((Capturador)Capturador.esteAgente);
        System.out.println("vamos a capturar");
    }
    
    public void dispararDatosGuardar(){
        addBehaviour(new DatosParaGuardar());
    }
    
    public void dispararDatosConsultar(){
        addBehaviour(new DatosParaConsultar());
    }
 
    class DatosParaGuardar extends SimpleBehaviour{
        
        private Guardar ventana;
        private boolean fin = false;
        
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
                        JOptionPane.showMessageDialog(ventana, "El Usuario ya existe");
                    }else{
                        System.out.println("enviando para guardar");
                        addBehaviour( new Comunicar("Guardar",datos,Capturador.esteAgente));
                        this.ventana.setDatos(null);
                        this.fin = true;
                    }
                } catch (UnreadableException ex) {
                    Logger.getLogger(Capturador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        @Override
        public boolean done() {
            return this.fin == true;
        }
        
    }
    
    class DatosParaConsultar extends SimpleBehaviour{
        
        private boolean fin = false;

        @Override
        public void action() {
            
            if(Capturador.b){
                String datos = ventanaConsulta.datoConsulta;
                System.out.println("capturando");
                System.out.println("enviando para verificar");
                addBehaviour(new Comunicar("Verificar", datos,Capturador.esteAgente));
                Capturador.b = false;
            }
            ACLMessage msg = receive();
            if (msg != null) {
                String datos = ventanaConsulta.datoConsulta;
                try {
                    boolean existe = (boolean) msg.getContentObject();
                    System.out.println(existe);
                    if(existe){
                        addBehaviour(new Comunicar("Consultar",datos,Capturador.esteAgente));
                        this.fin = true;
                    }else{
                        System.out.println("no encontre nada, abre el ojo");
                        JOptionPane.showMessageDialog(ventana, "El Usuario no existe");
                        
                    }
                } catch (UnreadableException ex) {
                    Logger.getLogger(Capturador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }

        @Override
        public boolean done() {
            return this.fin == true;
        }
        
    }
}

