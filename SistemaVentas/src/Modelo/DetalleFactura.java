
package Modelo;


public class DetalleFactura {
    
    private int detalle_fact_id;
    private int id_venta;
    private String barras_id;
    private Double precio;
    private int detalle_fact_prod_cant;
    
    public DetalleFactura(){
        
    }

    public DetalleFactura(int detalle_fact_id, int id_venta, String barras_id, Double precio, int detalle_fact_prod_cant) {
        this.detalle_fact_id = detalle_fact_id;
        this.id_venta = id_venta;
        this.barras_id = barras_id;
        this.precio = precio;
        this.detalle_fact_prod_cant = detalle_fact_prod_cant;
    }

    public int getDetalle_fact_id() {
        return detalle_fact_id;
    }

    public void setDetalle_fact_id(int detalle_fact_id) {
        this.detalle_fact_id = detalle_fact_id;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public String getBarras_id() {
        return barras_id;
    }

    public void setBarras_id(String barras_id) {
        this.barras_id = barras_id;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getDetalle_fact_prod_cant() {
        return detalle_fact_prod_cant;
    }

    public void setDetalle_fact_prod_cant(int detalle_fact_prod_cant) {
        this.detalle_fact_prod_cant = detalle_fact_prod_cant;
    }

 

  
   
   
}
