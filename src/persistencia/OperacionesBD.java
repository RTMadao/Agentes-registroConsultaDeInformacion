/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Persona;

/**
 *
 * @author carlo
 */
public class OperacionesBD {
    
    private final String TablaPERSONA = "persona";
    private final String TablaTELEFONO = "telefono";
    private final String TablaCORREO = "correo";
    private final String TablaDIRECCION = "direccion";
    private static OperacionesBD instancia = null;
    private static Connection conexion = null;
    
    private OperacionesBD(){}
    
    public void guardar(Persona registro) throws SQLException{
        try{
            PreparedStatement insert;
            
            insert = (PreparedStatement) conexion.prepareStatement("INSERT INTO " + this.TablaPERSONA + " (Documento,Nombre,Apellido,Genero,FechaNacimiento) VALUES (?,?,?,?,?)");
            insert.setString(1, registro.getDocumento());
            insert.setString(2, registro.getNombre());
            insert.setString(3, registro.getApellido());
            insert.setString(4, registro.getGenero());
            insert.setString(5, registro.getFechaNacimiento());
            
            insert.executeUpdate();
            
        }catch(SQLException ex){
            throw new SQLException(ex);
        }
    }
    
    public boolean existeRegistro(String documento) throws SQLException{
        try{
         PreparedStatement consulta = (PreparedStatement) conexion.prepareStatement("SELECT Documento FROM " + this.TablaPERSONA + " WHERE  Documento = ?" );
         consulta.setString(1, documento);
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            //if(resultado.getString(0)== )
             System.out.println("resultado de busqueda "+resultado.getString(1));
             return true;
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
        System.out.println("no se encontro nada nadita nada");
        return false;
    }
    
    public ArrayList<Persona> recuperarTodas() throws SQLException{
      ArrayList<Persona> personas = new ArrayList<>();
      try{
         PreparedStatement consulta = (PreparedStatement) conexion.prepareStatement("SELECT * FROM " + this.TablaPERSONA + " ORDER BY apellido");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            personas.add(new Persona(resultado.getString("Documento"), resultado.getString("Nombre"), resultado.getString("Apellido"), resultado.getString("genero"), resultado.getString("FechaNacimiento")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return personas;
   }
    
    public static OperacionesBD getInstance() {
        if(OperacionesBD.instancia == null){
            OperacionesBD.instancia = new OperacionesBD();
            try {
                OperacionesBD.conexion = (Connection) Conexion.obtener();
            } catch (SQLException ex) {
                Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return OperacionesBD.instancia;
    }
    
}
