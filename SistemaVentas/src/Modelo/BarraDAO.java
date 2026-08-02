
package Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.util.List;
import javax.swing.JComboBox;

public class BarraDAO {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
     
    /*
      public ArrayList<Barra> consultar(Barra barra) throws ClassNotFoundException{
        ArrayList<Barra> response = new ArrayList<>();
        String SQL = "select barras_id, prod_id, barras_desc, barras_prod_cant, barras_precio from barras WHERE 1 = 1 ";
        Statement st;
        
        
      
        
        
        String [] datos = new String [5];
        try{
           con = cn.getConnection();
           ps = con.prepareStatement(SQL);
           rs = ps.executeQuery();
            while(rs.next())
            {
                // BD -> Integer
                //Cod -> String = .toString();
                
                //BD -> String
                //Cod -> Double = Double.parseDouble(valor)
                //Cod -> Integer = Integer.parseInt(valor);
                //Date fecha = valor;
                Barra temporal = new Barra();
                temporal.setBarras_id(rs.getString(1));
                temporal.setEstado_prod_id(rs.getString(2));
                temporal.setBarra_descripcion(rs.getString(3));
                temporal.setBarras_prod_cant(rs.getInt(4));
                temporal.setBarras_precio(rs.getDouble(5));
                
                response.add(temporal);
            }
        }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Error" + e.toString());
        }
        return response;
    }
    
  /*     public boolean Registrar(Barra cli) {
        String sql = "INSERT INTO barras (barras_id, prod_id,barra_descripcion, color_id, talla_id, barras_precio, barras_genero, barras_prod_cant, estado_prod_id, categ_id, tienda_id ) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getBarras_id());
            ps.setString(2, cli.getProd_id());
            ps.setString(3, cli.getBarra_descripcion());
            ps.setString(4, cli.getColor_id());
            ps.setString(5, cli.getTalla_id());
            ps.setDouble(6, cli.getBarras_precio());
            ps.setString(7, cli.getBarras_genero());
            ps.setInt(8, cli.getBarras_prod_cant());
            ps.setString(9, cli.getEstado_prod_id());
            ps.setString(10, cli.getCateg_id());
            ps.setString(11, cli.getTienda_id());
            
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.toString());
            return false;
        }
    }*/
      
          //Metodo para registrar con funciones almacenadas
      
       public boolean Registrar(Barra br){
    String sql = "INSERT INTO listaarticulo (Id_Articulo, Marca_Pantalla, Tec_Pantalla, Tec_Pantalla2, Tec_Pantalla3, Tec_Torre, Marca_Torre, Marca_Raton, Marca_Teclado, Marca_Diademas, Marca_Camara, Adaptador, Fecha, Acta, empresa_id ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);

        ps.setString(1, br.getId_Articulo());
        ps.setString(2, br.getMarca_Pantalla());
        ps.setString(3, br.getTec_Pantalla());
        ps.setString(4, br.getTec_Pantalla2());
        ps.setString(5, br.getTec_Pantalla3());
        ps.setString(6, br.getTec_Torre());
        ps.setString(7, br.getMarca_Torre());
        ps.setString(8, br.getMarca_Raton());
        ps.setString(9, br.getMarca_Teclado());
        ps.setString(10, br.getMarca_Diademas());
        ps.setString(11, br.getMarca_Camara());
        ps.setString(12, br.getAdaptador());
        ps.setString(13, br.getFecha());
        ps.setString(14, br.getActa());
        ps.setString(15, br.getTienda_id());

        ps.executeUpdate();
        return true;

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}
      
       public List ListarBarra(String valor){
       List<Barra> ListaBR = new ArrayList();
       String sql = "SELECT * FROM  listaarticulo ORDER BY id_Articulo ASC ";
       String buscar = "SELECT * FROM  listaarticulo WHERE id_Articulo  LIKE '%"+valor+"%' OR Fecha LIKE '%"+valor+"%'OR Tec_Pantalla LIKE '%"+valor+"%' OR Tec_Pantalla2 LIKE '%"+valor+"%' OR Tec_Pantalla3 LIKE '%"+valor+"%' OR Tec_Torre LIKE '%"+valor+"%' OR Tec_Torre LIKE '%"+valor+"%'";
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
               Barra br = new Barra();
               br.setId_Articulo(rs.getString("id_Articulo"));
               br.setMarca_Pantalla(rs.getString("Marca_Pantalla"));
               br.setTec_Pantalla(rs.getString("Tec_Pantalla"));
               br.setTec_Pantalla2(rs.getString("Tec_Pantalla2"));
               br.setTec_Pantalla3(rs.getString("Tec_Pantalla3"));
               br.setTec_Torre(rs.getString("Tec_Torre"));
               br.setMarca_Torre(rs.getString("Marca_Torre"));
               br.setMarca_Raton(rs.getString("Marca_Raton"));
               br.setMarca_Teclado(rs.getString("Marca_teclado"));
               br.setMarca_Diademas(rs.getString("Marca_Diademas"));
               br.setMarca_Camara(rs.getString("Marca_Camara"));
               br.setAdaptador(rs.getString("Adaptador"));
               br.setFecha(rs.getString("Fecha"));
               br.setActa(rs.getString("Acta"));   
               br.setTienda_id(rs.getString("empresa_id"));    
               ListaBR.add(br);
           }
       } catch (SQLException e) {
          JOptionPane.showMessageDialog(null,e.toString());
       }
       return ListaBR;
   }
   
       
       
       
        public boolean Modificar(Barra reg){
        String sql = "UPDATE  listaarticulo SET Marca_Pantalla = ?, Tec_Pantalla = ?, Tec_Pantalla2 = ?, Tec_Pantalla3 = ?,  Tec_Torre  = ?, Marca_Torre = ?, Marca_Raton= ?, Marca_Teclado = ?, Marca_Diademas = ?, Marca_Camara = ?, Adaptador = ?, Fecha  = ?, Acta = ?, empresa_id = ?  WHERE  id_Articulo = ? ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
           ps.setString(1, reg.getMarca_Pantalla());
           ps.setString(2, reg.getTec_Pantalla());
           ps.setString(3, reg.getTec_Pantalla2());
           ps.setString(4, reg.getTec_Pantalla3());
           ps.setString(5, reg.getTec_Torre());
           ps.setString(6, reg.getMarca_Torre());
           ps.setString(7, reg.getMarca_Raton());
           ps.setString(8, reg.getMarca_Teclado());
           ps.setString(9, reg.getMarca_Diademas());
           ps.setString(10, reg.getMarca_Camara());
           ps.setString(11, reg.getAdaptador());
            ps.setString(12, reg.getFecha());
            ps.setString(13, reg.getActa());
            ps.setString(14, reg.getTienda_id());
            ps.setString(15, reg.getId_Articulo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.toString());
            return false;
            
        }
    }
       
          public Barra BuscarPro(String cod){
        Barra producto = new Barra();
        String sql = "SELECT id_Articulo, Marca_Pantalla, Tec_Pantalla, Tec_Pantalla2 , Tec_Pantalla3 ,  Tec_Torre, Marca_Torre, Marca_Raton, Marca_Teclado, Marca_Diademas, Marca_Camara, Adaptador, Fecha, Acta, empresa_id FROM  listaarticulo WHERE id_Articulo = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            if (rs.next()) {
                producto.setId_Articulo(rs.getString("id_Articulo"));
                producto.setMarca_Pantalla(rs.getString("Marca_Pantalla"));
                producto.setTec_Pantalla(rs.getString("Tec_Pantalla"));
                producto.setTec_Pantalla2(rs.getString("Tec_Pantalla2"));
                producto.setTec_Pantalla3(rs.getString("Tec_Pantalla3"));
                producto.setTec_Torre(rs.getString("Tec_Torre"));
                producto.setMarca_Torre(rs.getString("Marca_Torre"));
                producto.setMarca_Raton(rs.getString("Marca_Raton"));
                producto.setMarca_Teclado(rs.getString("Marca_Teclado"));
                producto.setMarca_Diademas(rs.getString("Marca_Diademas"));
                producto.setMarca_Camara(rs.getString("Marca_Camara"));
                producto.setAdaptador(rs.getString("Adaptador"));
                producto.setActa(rs.getString("Acta"));
                producto.setFecha(rs.getString("Fecha"));
                producto.setTienda_id(rs.getString("empresa_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return producto;
    }
          
          
            public Barra BuscarProNombre(String prod){
        Barra producto = new Barra();
        String sql = "SELECT id_Articulo, Marca_Pantalla, Tec_Pantalla,  Tec_Torre, Marca_Torre, Marca_Raton, Marca_Diademas, Marca_Camara, Adaptador, Fecha, Acta, empresa_id FROM  listaarticulo WHERE Tec_Pantalla = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, prod);
            rs = ps.executeQuery();
            if (rs.next()) {
                 producto.setTec_Pantalla(rs.getString("Tec_Pantalla"));
                producto.setTec_Torre(rs.getString("Tec_Torre"));
                producto.setFecha(rs.getString("Fecha"));
                producto.setTienda_id(rs.getString("empresa_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return producto;
    }
            
            
                 
            public Barra BuscarProNombre2(String prod){
        Barra producto = new Barra();
        String sql = "SELECT id_Articulo, Marca_Pantalla, Tec_Pantalla,  Tec_Torre, Marca_Torre, Marca_Raton, Marca_Diademas, Marca_Camara, Adaptador, Fecha, Acta, empresa_id FROM  listaarticulo WHERE Tec_Pantalla2 = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, prod);
            rs = ps.executeQuery();
            if (rs.next()) {
                 producto.setTec_Pantalla2(rs.getString("Tec_Pantalla2"));
                producto.setTec_Torre(rs.getString("Tec_Torre"));
                producto.setFecha(rs.getString("Fecha"));
                producto.setTienda_id(rs.getString("empresa_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return producto;
    }
            
            
            
                 
            public Barra BuscarProNombre3(String prod){
        Barra producto = new Barra();
        String sql = "SELECT id_Articulo, Marca_Pantalla, Tec_Pantalla,  Tec_Torre, Marca_Torre, Marca_Raton, Marca_Diademas, Marca_Camara, Adaptador, Fecha, Acta, empresa_id FROM listaarticulo WHERE Tec_Pantalla3 = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, prod);
            rs = ps.executeQuery();
            if (rs.next()) {
                 producto.setTec_Pantalla3(rs.getString("Tec_Pantalla3"));
                producto.setTec_Torre(rs.getString("Tec_Torre"));
                producto.setFecha(rs.getString("Fecha"));
                producto.setTienda_id(rs.getString("empresa_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return producto;
    }
            
          
             public boolean EliminarProducto(String id){
        //Creamos una variable String para ejecutar el codigo mysql para eliminar los campos en la tabla cliente
       String sql = "DELETE FROM  listaarticulo WHERE id_Articulo = ?";
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
             
  //--------------------------------------------------------------------------------------------------------------
                 
                 public Configuracion BuscarDatos(){
        Configuracion conf = new Configuracion();
        String sql = "SELECT * FROM empresa";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                conf.setEmpresa_id(rs.getString("empresa_id"));
                conf.setEmpresa_nombre(rs.getString("empresa_nombre"));
                conf.setEmpresa_telefono(rs.getString("empresa_telefono"));
                conf.setEmpresa_direccion(rs.getString("empresa_direccion"));
                conf.setEmpresa_email(rs.getString("empresa_email"));
                conf.setEmpresa_ciudad(rs.getString("empresa_ciudad"));
                conf.setEmpresa_dpto(rs.getString("empresa_dpto"));
                conf.setEmpresa_mensaje(rs.getString("empresa_mensaje"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return conf;
    }
                   public boolean ModificarDatos(Configuracion conf){
       String sql = "UPDATE empresa SET empresa_nombre=?, empresa_telefono=?, empresa_direccion=?, empresa_email=?, empresa_ciudad=?,  empresa_dpto=?, empresa_mensaje=? WHERE empresa_id=?";
       try {
           ps = con.prepareStatement(sql);
           ps.setString(1, conf.getEmpresa_nombre());
           ps.setString(2, conf.getEmpresa_telefono());
           ps.setString(3, conf.getEmpresa_direccion());
           ps.setString(4, conf.getEmpresa_email());
           ps.setString(5, conf.getEmpresa_ciudad());
           ps.setString(6, conf.getEmpresa_dpto());
           ps.setString(7, conf.getEmpresa_mensaje());
           ps.setString(8, conf.getEmpresa_id());
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }finally{
           try {
               con.close();
           } catch (SQLException e) {
               System.out.println(e.toString());
           }
       }
   }
                   
   
       
//-------------------------------------------------------------------------------------------------------------------------------------------
          
              public void ConsultarTienda(JComboBox estado){
        String sql = "SELECT * FROM empresa";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                estado.addItem(rs.getString("empresa_id"));
            }
        }catch (SQLException e){
            System.out.println(e.toString());
        }
    }
              
              
              
                public int Ticket(){
        int id = 0;
        String sql = "SELECT MAX(INCREMENTO) FROM listaarticulo";
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
              
}





