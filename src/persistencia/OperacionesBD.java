/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.SQLException;
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
    
    public void guardar(Connection conexion, Persona registro) throws SQLException{
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
    
}
