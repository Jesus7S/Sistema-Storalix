
package Modelo;


public class Configuracion {
    
 private String empresa_id;
    private String empresa_nombre;
    private String empresa_telefono;
    private String empresa_direccion;
    private String empresa_email;
    private String empresa_ciudad;
    private String empresa_dpto;
    private String empresa_mensaje;
    
    public Configuracion (){
        
    }

    public Configuracion(String empresa_id, String empresa_nombre, String empresa_telefono, String empresa_direccion, String empresa_email, String empresa_ciudad, String empresa_dpto, String empresa_mensaje) {
        this.empresa_id = empresa_id;
        this.empresa_nombre = empresa_nombre;
        this.empresa_telefono = empresa_telefono;
        this.empresa_direccion = empresa_direccion;
        this.empresa_email = empresa_email;
        this.empresa_ciudad = empresa_ciudad;
        this.empresa_dpto = empresa_dpto;
        this.empresa_mensaje = empresa_mensaje;
    }

    public String getEmpresa_id() {
        return empresa_id;
    }

    public void setEmpresa_id(String empresa_id) {
        this.empresa_id = empresa_id;
    }

    public String getEmpresa_nombre() {
        return empresa_nombre;
    }

    public void setEmpresa_nombre(String empresa_nombre) {
        this.empresa_nombre = empresa_nombre;
    }

    public String getEmpresa_telefono() {
        return empresa_telefono;
    }

    public void setEmpresa_telefono(String empresa_telefono) {
        this.empresa_telefono = empresa_telefono;
    }

    public String getEmpresa_direccion() {
        return empresa_direccion;
    }

    public void setEmpresa_direccion(String empresa_direccion) {
        this.empresa_direccion = empresa_direccion;
    }

    public String getEmpresa_email() {
        return empresa_email;
    }

    public void setEmpresa_email(String empresa_email) {
        this.empresa_email = empresa_email;
    }

    public String getEmpresa_ciudad() {
        return empresa_ciudad;
    }

    public void setEmpresa_ciudad(String empresa_ciudad) {
        this.empresa_ciudad = empresa_ciudad;
    }

    public String getEmpresa_dpto() {
        return empresa_dpto;
    }

    public void setEmpresa_dpto(String empresa_dpto) {
        this.empresa_dpto = empresa_dpto;
    }

    public String getEmpresa_mensaje() {
        return empresa_mensaje;
    }

    public void setEmpresa_mensaje(String empresa_mensaje) {
        this.empresa_mensaje = empresa_mensaje;
    }

  

    
}
