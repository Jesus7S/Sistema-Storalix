
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class DarAltayBajaUsuarioDao {
   
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
  
    
     public List ListarUsuariosBajayAlta(String valor){
       List<DarAltayBajaUsuario> Lista = new ArrayList();
       String sql = "SELECT * FROM inabilitarusuario ORDER BY estado ASC";
       String buscar = "SELECT * FROM inabilitarusuario WHERE nombre LIKE '%"+valor+"%' OR  id LIKE '%"+valor+"%'";
       try {
           con = cn.getConnection();
           if(valor.equalsIgnoreCase("")){
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           }else{
           ps = con.prepareStatement(buscar);
           rs = ps.executeQuery();
           }
           while (rs.next()) {               
               DarAltayBajaUsuario lg = new DarAltayBajaUsuario();
               lg.setId(rs.getInt("id"));
               lg.setNombre(rs.getString("nombre"));
               lg.setCorreo(rs.getString("correo"));
               lg.setRol(rs.getString("rol"));
               lg.setEstado(rs.getString("estado"));
                lg.setFecha(rs.getString("fecha"));
               Lista.add(lg);
           }
       } catch (SQLException e) {
          JOptionPane.showMessageDialog(null,e.toString());
       }
       return Lista;
   }
     
          public boolean accion(String estado, int id){
         
         String sql = "UPDATE inabilitarusuario SET estado = ?  WHERE  id = ?";
         
         try {
             con = cn.getConnection();
             ps = con.prepareStatement(sql);
              ps.setString(1, estado);
             ps.setInt(2, id);
             ps.execute();
             return true;
             
         }catch (SQLException e){
             JOptionPane.showMessageDialog(null, e.toString());
             return false;
         }
     }
          
            
         public boolean EliminarUsuario(int id){
       String sql = "DELETE FROM inabilitarusuario WHERE id = ?";
       try {
           ps = con.prepareStatement(sql);
           ps.setInt(1, id);
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }finally{
           try {
               con.close();
           } catch (SQLException ex) {
               System.out.println(ex.toString());
           }
       }
   }
}
