
package Modelo;


public class Venta {
   
    private int id_venta;
    private String cli_id;
    private String nombre_cliente;
    private String vendedor;
    private double total;
    private String fecha;
    

    public Venta() {
    }

    public Venta(int id_venta, String cli_id, String nombre_cliente, String vendedor, double total, String fecha) {
        this.id_venta = id_venta;
        this.cli_id = cli_id;
        this.nombre_cliente = nombre_cliente;
        this.vendedor = vendedor;
        this.total = total;
        this.fecha = fecha;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public String getCli_id() {
        return cli_id;
    }

    public void setCli_id(String cli_id) {
        this.cli_id = cli_id;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

  

 

} 