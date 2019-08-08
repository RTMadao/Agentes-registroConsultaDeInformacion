/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author carlo
 */
public class Persona implements Serializable{
    
    private String documento;
    private String nombre;
    private String apellido;
    private String genero;
    private String fechaNacimiento;
    private ArrayList<Telefono> listaTelefono;
    private ArrayList<Direccion> listaDireccion;
    private ArrayList<String> listaCorreo;

    public Persona(String documento, String nombre, String apellido, String genero, String fechaNacimiento) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.listaTelefono = new ArrayList<Telefono>();
        this.listaDireccion = new ArrayList<Direccion>();
        this.listaCorreo = new ArrayList<String>();
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public ArrayList<Telefono> getListaTelefono() {
        return listaTelefono;
    }

    public void setListaTelefono(ArrayList<Telefono> listaTelefono) {
        this.listaTelefono = listaTelefono;
    }

    public ArrayList<Direccion> getListaDireccion() {
        return listaDireccion;
    }

    public void setListaDireccion(ArrayList<Direccion> listaDireccion) {
        this.listaDireccion = listaDireccion;
    }

    public ArrayList<String> getListaCorreo() {
        return listaCorreo;
    }

    public void setListaCorreo(ArrayList<String> listaCorreo) {
        this.listaCorreo = listaCorreo;
    }
}
