package Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class LoginDAO {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    public Login log(String correo, String pass){
        Login l = new Login();
        String sql = "SELECT * FROM usuario WHERE correo = ? AND pass = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, pass);
            rs= ps.executeQuery();
            if (rs.next()) {
                l.setId(rs.getInt("id"));
                l.setNombre(rs.getString("nombre"));
                l.setCorreo(rs.getString("correo"));
                l.setPass(rs.getString("pass"));
                l.setRol(rs.getString("rol"));
                l.setEstado(rs.getString("estado"));
                
            }
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null,e.toString());
        }
        return l;
    }
    
    
    //Metodo para registrar
    
    public boolean insertar(Login lo){
        String sql = "INSERT INTO usuario (nombre, correo, pass, rol) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, lo.getNombre());
            ps.setString(2, lo.getCorreo());
            ps.setString(3, lo.getPass());
            ps.setString(4, lo.getRol());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.toString());
            return false;
        }
    }
    
    
    
    //Metodo para registrar con funciones almacenadas
    
      
    
    
    
    public List ListarUsuarios(String valor){
       List<Login> Lista = new ArrayList();
       String sql = "SELECT * FROM usuario ORDER BY estado ASC";
       String buscar = "SELECT * FROM usuario WHERE nombre LIKE '%"+valor+"%' OR  id LIKE '%"+valor+"%'";
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
               Login lg = new Login();
               lg.setId(rs.getInt("id"));
               lg.setNombre(rs.getString("nombre"));
               lg.setCorreo(rs.getString("correo"));
               lg.setRol(rs.getString("rol"));
               lg.setEstado(rs.getString("estado"));
               Lista.add(lg);
           }
       } catch (SQLException e) {
          JOptionPane.showMessageDialog(null,e.toString());
       }
       return Lista;
   }
    
     public boolean Modificar(Login reg){
        String sql = "UPDATE usuario SET nombre = ? , correo = ?, rol = ?  WHERE id = ? ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, reg.getNombre());
            ps.setString(2, reg.getCorreo());
            ps.setString(3, reg.getRol());
           ps.setInt(4, reg.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.toString());
            return false;
        }
    }
     
     public boolean accion(String estado, int id){
         
         String sql = "UPDATE usuario SET estado = ?  WHERE  id = ?";
         
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
       String sql = "DELETE FROM usuario WHERE id = ?";
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
