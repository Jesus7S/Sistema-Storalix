/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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



/**
 *
 * @author jesus
 */
public class EmpleadoDAO {
     Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    private Sistema vista ;
     //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    //Metodo para llamar al procedimiento de la base de datos, el cual realizara la funcion de registrar los datos que le demos al sistema
    
     public boolean Registrar(Empleado Emp) {

    String sql = "INSERT INTO empleado "
            + "(id_empleado, nombre, cargo, departamento) "
            + "VALUES (?,?,?,?)";

    try {

        con = cn.getConnection();
        ps = con.prepareStatement(sql);

        ps.setString(1, Emp.getId_Empleado());
        ps.setString(2, Emp.getNombre());
        ps.setString(3, Emp.getCargo());
        ps.setString(4, Emp.getDepartamento());

        ps.execute();
        return true;

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}
     
     
     //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
     
     //Con etsa funcion realizaremos que nos muestra la lista de datos que hemos introducido en el sistema.
     
      public List ListarEmpleado(String valor){
       List<Empleado> ListaBR = new ArrayList();
       String sql = "SELECT * FROM empleado ";
       String buscar = "SELECT * FROM empleado WHERE id_Empleado  LIKE '%"+valor+"%'";
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
               Empleado br = new Empleado();
               br.setId_Empleado(rs.getString("id_Empleado"));
               br.setNombre(rs.getString("Nombre"));
               br.setCargo(rs.getString("Cargo"));
               br.setDepartamento(rs.getString("Departamento"));
             
               
               ListaBR.add(br);
           }
       } catch (SQLException e) {
          JOptionPane.showMessageDialog(null,e.toString());
       }
       return ListaBR;
   }
      
     //-------------------------------------------------------------------------------------------------------------------------------------------------------- 
      
      // La funcion que nos permitirar cualquier dato que el usuario considere erroneo en el sistema, o nesecite un cambio en el valor.
      public boolean Modificar(Empleado emp){
        String sql = "UPDATE empleado SET  Nombre = ?, Cargo = ?, Departamento = ?  WHERE id_Empleado = ? ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, emp.getNombre());
           ps.setString(2, emp.getCargo());
           ps.setString(3, emp.getDepartamento());
           ps.setString(4, emp.getId_Empleado());
         
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.toString());
            return false;
            
        }
    }
      
     //--------------------------------------------------------------------------------------------------------------------------------------------------------
      
      
                    //Creamos nuestro metodo para Eliminar al Persona 
   public boolean EliminarEmpleado(String id){
        //Creamos una variable String para ejecutar el codigo mysql para eliminar los campos en la tabla cliente
       String sql = "DELETE FROM empleado WHERE id_Empleado = ?";
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
   //--------------------------------------------------------------------------------------------------------------------------------------------------------
   
   //Metodo para buscar al empleado por medio del codigo id que se le fue asignado.
   
          public Empleado BuscarEmpleado(String emp){
       Empleado cl = new Empleado();
       String sql = "SELECT * FROM empleado WHERE id_Empleado = ?";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, emp);
           rs = ps.executeQuery();
           if (rs.next()) {
              cl.setId_Empleado(rs.getString("id_Empleado"));
               cl.setNombre(rs.getString("Nombre"));     
               cl.setCargo(rs.getString("Cargo"));
               cl.setDepartamento(rs.getString("Departamento"));
                 
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return cl;
   }
          
          //--------------------------------------------------------------------------------------------------------------------------------------------------------
          
            public Empleado BuscarEmpleadoNombre(String emp){
       Empleado cl = new Empleado();
       String sql = "SELECT * FROM empleado WHERE Nombre = ?";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, emp);
           rs = ps.executeQuery();
           if (rs.next()) {
              cl.setId_Empleado(rs.getString("id_Empleado"));
               cl.setNombre(rs.getString("Nombre"));     
               cl.setCargo(rs.getString("Cargo"));
               cl.setDepartamento(rs.getString("Departamento"));
                 
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return cl;
   }
    
            
            
               
            public Empleado BuscarEmpleadoNombre2(String emp){
       Empleado cl = new Empleado();
       String sql = "SELECT * FROM empleado WHERE Nombre = ?";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, emp);
           rs = ps.executeQuery();
           if (rs.next()) {
              cl.setId_Empleado(rs.getString("id_Empleado"));
               cl.setNombre(rs.getString("Nombre"));     
               cl.setCargo(rs.getString("Cargo"));
               
                 
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return cl;
   }
          

}
 