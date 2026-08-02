
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class VentaDAO {
    
     Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    
      public int IdVenta(){
        int id = 0;
        String sql = "SELECT MAX(id_venta) FROM venta";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return id;
    }
    
        public int RegistrarVenta(Venta v){
        String sql = "INSERT INTO venta ( cli_id, vendedor,  total, fecha) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, v.getCli_id());
            ps.setString(2, v.getVendedor());
            ps.setDouble(3, v.getTotal());
            ps.setString(4, v.getFecha());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return r;
    }
        
        
        
            public List Listarventas(){
       List<Venta> ListaVenta = new ArrayList();
       String sql = "SELECT * FROM venta ";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {               
               Venta vent = new Venta();
               vent.setId_venta(rs.getInt("id_venta"));
               vent.setVendedor(rs.getString("vendedor"));
               vent.setCli_id(rs.getString("cli_id"));
               vent.setTotal(rs.getDouble("total"));
               vent.setFecha(rs.getString("fecha"));
               ListaVenta.add(vent);
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return ListaVenta;
   }
        
        
          public int RegistrarDetalle(DetalleFactura Dv){
       String sql = "INSERT INTO detalle_factura (barras_id, precio, detalle_fact_prod_cant, id_venta) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, Dv.getBarras_id());
                ps.setDouble(2, Dv.getPrecio());
               ps.setInt(3, Dv.getDetalle_fact_prod_cant());
               ps.setInt(4, Dv.getId_venta());
              
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return r;
    }
          
              public boolean ActualizarStock(int cant, String id){
        String sql = "UPDATE barras SET barras_prod_cant = ? WHERE barras_id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,cant);
            ps.setString(2, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
                 public boolean EliminarVenta(int id){
        //Creamos una variable String para ejecutar el codigo mysql para eliminar los campos en la tabla cliente
       String sql = "DELETE FROM venta WHERE id_venta = ?";
        //Creamos un try catch para las excepciones
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
               //Colocamos esto para que nos informe de cualquier error
               System.out.println(ex.toString());
           }
       }
   }
}
