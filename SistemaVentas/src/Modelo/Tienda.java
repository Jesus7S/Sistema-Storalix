
package Modelo;


public class Tienda {
    
    private String tienda_id;
    private String tienda_nombre;
    private String tienda_telefono;
    private String tienda_direccion;
    private String tienda_email;
    private String tienda_ciudad;
    private String tienda_dpto;
    
    public Tienda(){
        
    }

    public Tienda(String tienda_id, String tienda_nombre, String tienda_telefono, String tienda_direccion, String tienda_email, String tienda_ciudad, String tienda_dpto) {
        this.tienda_id = tienda_id;
        this.tienda_nombre = tienda_nombre;
        this.tienda_telefono = tienda_telefono;
        this.tienda_direccion = tienda_direccion;
        this.tienda_email = tienda_email;
        this.tienda_ciudad = tienda_ciudad;
        this.tienda_dpto = tienda_dpto;
    }

    public String getTienda_id() {
        return tienda_id;
    }

    public void setTienda_id(String tienda_id) {
        this.tienda_id = tienda_id;
    }

    public String getTienda_nombre() {
        return tienda_nombre;
    }

    public void setTienda_nombre(String tienda_nombre) {
        this.tienda_nombre = tienda_nombre;
    }

    public String getTienda_telefono() {
        return tienda_telefono;
    }

    public void setTienda_telefono(String tienda_telefono) {
        this.tienda_telefono = tienda_telefono;
    }

    public String getTienda_direccion() {
        return tienda_direccion;
    }

    public void setTienda_direccion(String tienda_direccion) {
        this.tienda_direccion = tienda_direccion;
    }

    public String getTienda_email() {
        return tienda_email;
    }

    public void setTienda_email(String tienda_email) {
        this.tienda_email = tienda_email;
    }

    public String getTienda_ciudad() {
        return tienda_ciudad;
    }

    public void setTienda_ciudad(String tienda_ciudad) {
        this.tienda_ciudad = tienda_ciudad;
    }

    public String getTienda_dpto() {
        return tienda_dpto;
    }

    public void setTienda_dpto(String tienda_dpto) {
        this.tienda_dpto = tienda_dpto;
    }

   
    
}
