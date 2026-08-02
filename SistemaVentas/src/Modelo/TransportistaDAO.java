
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

public class TransportistaDAO {
    
      Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    private Sistema vista ;
     //--------------------------------------------------------------------------------------------------------------------------------------------------------
     
    //Metodo para llamar al procedimiento de la base de datos, el cual realizara la funcion de registrar los datos que le demos al sistema
    public boolean Registrar(Transportista Tra) {

    String sql = "INSERT INTO transportista "
            + "(id_transportista, tra_nombre, tra_nocaja, tra_transportadora) "
            + "VALUES (?,?,?,?)";

    try {

        con = cn.getConnection();
        ps = con.prepareStatement(sql);

        ps.setString(1, Tra.getId_Transportista());
        ps.setString(2, Tra.getTra_Nombre());
        ps.setString(3, Tra.getTra_NoCaja());
        ps.setString(4, Tra.getTra_Transportadora());

        ps.execute();
        return true;

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}
       //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
     
     //Con etsa funcion realizaremos que nos muestra la lista de datos que hemos introducido en el sistema.
     
      public List ListarTransportista(String valor){
       List<Transportista> ListaBR = new ArrayList();
       String sql = "SELECT * FROM transportista ";
       String buscar = "SELECT * FROM transportista WHERE id_Transportista  LIKE '%"+valor+"%'";
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
               Transportista tr = new Transportista();
               tr.setId_Transportista(rs.getString("id_Transportista"));
               tr.setTra_Nombre(rs.getString("Tra_Nombre"));
               tr.setTra_NoCaja(rs.getString("Tra_NoCaja"));
               tr.setTra_Transportadora(rs.getString("Tra_Transportadora"));
             
               
               ListaBR.add(tr);
           }
       } catch (SQLException e) {
          JOptionPane.showMessageDialog(null,e.toString());
       }
       return ListaBR;
   }
       //-------------------------------------------------------------------------------------------------------------------------------------------------------- 
      
      // La funcion que nos permitirar cualquier dato que el usuario considere erroneo en el sistema, o nesecite un cambio en el valor.
    
      
        public boolean ModificarTrans(Transportista tr){
        String sql = " UPDATE transportista SET  Tra_Nombre = ?,  Tra_NoCaja = ?, Tra_Transportadora = ?  WHERE id_Transportista = ? ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
           ps.setString(1, tr.getTra_Nombre());
           ps.setString(2, tr.getTra_NoCaja());
           ps.setString(3, tr.getTra_Transportadora());
            ps.setString(4, tr.getId_Transportista());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.toString());
            return false;
            
        }
    }
      
      //--------------------------------------------------------------------------------------------------------------------------------------------------------
      
      
                    //Creamos nuestro metodo para Eliminar al Persona 
   public boolean EliminarTransportista(String id){
        //Creamos una variable String para ejecutar el codigo mysql para eliminar los campos en la tabla cliente
       String sql = "DELETE FROM transportista WHERE id_Transportista = ?";
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
   
          public Transportista BuscarTransportador(String tra){
       Transportista tr = new Transportista();
       String sql = "SELECT * FROM transportista WHERE id_Transportista = ?";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, tra);
           rs = ps.executeQuery();
           if (rs.next()) {
              tr.setId_Transportista(rs.getString("id_Transportista"));
               tr.setTra_Nombre(rs.getString("Tra_Nombre"));     
               tr.setTra_NoCaja(rs.getString("Tra_NoCaja"));
               tr.setTra_Transportadora(rs.getString("Tra_Transportadora"));
                 
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return tr;
   }
           //--------------------------------------------------------------------------------------------------------------------------------------------------------
   
   //Metodo para buscar al empleado por medio del codigo id que se le fue asignado.
   
          public Transportista BuscarTransportadorNombre(String tra){
       Transportista tr = new Transportista();
       String sql = "SELECT * FROM transportista WHERE Tra_Nombre = ?";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, tra);
           rs = ps.executeQuery();
           if (rs.next()) {
              tr.setId_Transportista(rs.getString("id_Transportista"));
               tr.setTra_Nombre(rs.getString("Tra_Nombre"));     
               tr.setTra_NoCaja(rs.getString("Tra_NoCaja"));
               tr.setTra_Transportadora(rs.getString("Tra_Transportadora"));
                 
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return tr;
   }
          
}
