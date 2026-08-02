
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Vista.Sistema;
import java.sql.CallableStatement;


public class PersonaDAO {
     Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    private Sistema vista ;
    
  public boolean Registrar(Persona cli) {

    String sql = "INSERT INTO persona "
            + "(cli_tipo_id, cli_nombre, cli_apellido, "
            + "cli_piso, cli_cuenta, cli_celular, "
            + "cli_correo, cli_direccion) "
            + "VALUES (?,?,?,?,?,?,?,?)";

    try {

        con = cn.getConnection();
        ps = con.prepareStatement(sql);

        ps.setString(1, cli.getCli_tipo_id());
        ps.setString(2, cli.getCli_nombre());
        ps.setString(3, cli.getCli_apellido());
        ps.setString(4, cli.getCli_piso());
        ps.setString(5, cli.getCli_cuenta());
        ps.setString(6, cli.getCli_celular());
        ps.setString(7, cli.getCli_correo());
        ps.setString(8, cli.getCli_direccion());

        ps.execute();
        return true;

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}
    
        //Metodo para registrar con funciones almacenadas
    
    
     /*   public boolean Registrar(Persona cli){
        try{
            CallableStatement csta = con.prepareCall("{call insertarPersona(?,?,?,?,?,?,?,?,?)}");
              csta.setString(1, cli.getId());
            csta.setString(2, cli.getCli_tipo_id());
            csta.setString(3, cli.getCli_nombre());
            csta.setString(4, cli.getCli_apellido());
            csta.setString(5, cli.getCli_piso());
            csta.setString(6, cli.getCli_cuenta());
            csta.setString(7, cli.getCli_celular());
            csta.setString(8, cli.getCli_correo());
            csta.setString(9, cli.getCli_direccion());

               
               rs=csta.executeQuery(); 
                return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
             return false;
        }
         
    }*/
     
       public List ListarCliente(String valor){
       List<Persona> ListaBR = new ArrayList();
       String sql = "SELECT * FROM persona ";
       String buscar = "SELECT * FROM persona WHERE id LIKE '%"+valor+"%' OR cli_nombre LIKE '%"+valor+"%' OR cli_apellido LIKE '%"+valor+"%'";
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
               Persona br = new Persona();
               br.setId(rs.getString("id"));
               br.setCli_tipo_id(rs.getString("cli_tipo_id"));
               br.setCli_nombre(rs.getString("cli_nombre"));
               br.setCli_apellido(rs.getString("cli_apellido"));
               br.setCli_piso(rs.getString("cli_piso"));
               br.setCli_cuenta(rs.getString("cli_cuenta"));
               br.setCli_celular(rs.getString("cli_celular"));
               br.setCli_correo(rs.getString("cli_correo"));
               br.setCli_direccion(rs.getString("cli_direccion"));   
               ListaBR.add(br);
           }
       } catch (SQLException e) {
          JOptionPane.showMessageDialog(null,e.toString());
       }
       return ListaBR;
   }
       
       
        public boolean Modificar(Persona reg){
        String sql = "UPDATE persona SET  cli_tipo_id = ?,  cli_nombre = ?, cli_apellido = ?, cli_piso = ? ,cli_cuenta = ?, cli_celular = ?, cli_correo = ?, cli_direccion = ? WHERE id = ? ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, reg.getCli_tipo_id());
           ps.setString(2, reg.getCli_nombre());
           ps.setString(3, reg.getCli_apellido());
           ps.setString(4, reg.getCli_piso());
           ps.setString(5, reg.getCli_cuenta());
           ps.setString(6, reg.getCli_celular());
           ps.setString(7, reg.getCli_correo());
           ps.setString(8, reg.getCli_direccion());
              ps.setString(9, reg.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.toString());
            return false;
            
        }
    }
        
        
               //Creamos nuestro metodo para Eliminar al Persona 
   public boolean EliminarCliente(String id){
        //Creamos una variable String para ejecutar el codigo mysql para eliminar los campos en la tabla cliente
       String sql = "DELETE FROM persona WHERE id = ?";
        //Creamos un try catch para las excepciones
       try {
           ps = con.prepareStatement(sql);
           ps.setString(1, id);
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }finally{
           try {
               con.close();
           } catch (SQLException ex) {
               //Colocamos esto para que nos informe de cualquier error
               System.out.println(ex.toString());
           }
       }
   }

           public Persona Buscarcliente(String dni){
       Persona cl = new Persona();
       String sql = "SELECT * FROM persona WHERE id = ?";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, dni);
           rs = ps.executeQuery();
           if (rs.next()) {
              cl.setId(rs.getString("id"));
               cl.setCli_tipo_id(rs.getString("cli_tipo_id"));     
               cl.setCli_nombre(rs.getString("cli_nombre"));
               cl.setCli_apellido(rs.getString("cli_apellido"));
               cl.setCli_piso(rs.getString("cli_piso"));
               cl.setCli_cuenta(rs.getString("cli_cuenta"));
               cl.setCli_celular(rs.getString("cli_celular"));
               cl.setCli_correo(rs.getString("cli_correo"));
               cl.setCli_direccion(rs.getString("cli_direccion"));   
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return cl;
   }
           
           
              public Persona BuscarclienteNombre(String cliNom){
       Persona cl = new Persona();
       String sql = "SELECT * FROM persona WHERE cli_nombre = ?";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, cliNom);
           rs = ps.executeQuery();
           if (rs.next()) {
              cl.setId(rs.getString("id"));
               cl.setCli_tipo_id(rs.getString("cli_tipo_id"));      
               cl.setCli_nombre(rs.getString("cli_nombre"));
               cl.setCli_apellido(rs.getString("cli_apellido"));
               cl.setCli_piso(rs.getString("cli_piso"));
               cl.setCli_cuenta(rs.getString("cli_cuenta"));
               cl.setCli_celular(rs.getString("cli_celular"));
               cl.setCli_correo(rs.getString("cli_correo"));
               cl.setCli_direccion(rs.getString("cli_direccion"));   

           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return cl;
   }
            
                  public Persona BuscarPersonaApellido(String cliApellido){
       Persona cl = new Persona();
       String sql = "SELECT * FROM persona WHERE cli_apellido = ?";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, cliApellido);
           rs = ps.executeQuery();
           if (rs.next()) {
              cl.setId(rs.getString("id"));
               cl.setCli_tipo_id(rs.getString("cli_tipo_id"));      
               cl.setCli_nombre(rs.getString("cli_nombre"));
               cl.setCli_apellido(rs.getString("cli_apellido"));
               cl.setCli_piso(rs.getString("cli_piso"));
               cl.setCli_cuenta(rs.getString("cli_cuenta"));
               cl.setCli_celular(rs.getString("cli_celular"));
               cl.setCli_correo(rs.getString("cli_correo"));
               cl.setCli_direccion(rs.getString("cli_direccion"));   

           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return cl;
   }
              
              
}


