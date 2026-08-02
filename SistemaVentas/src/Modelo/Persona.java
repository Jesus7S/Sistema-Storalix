
package Modelo;

import java.util.Date;


public class Persona {
    
    private String id;
    private String cli_tipo_id;
    private String cli_nombre;
    private String cli_apellido;
    private String cli_piso;
    private String cli_cuenta;
    private String cli_celular;
    private String cli_correo;
    private String cli_direccion;

    public Persona (){
        
    }

    public Persona(String id, String cli_tipo_id, String cli_nombre, String cli_apellido, String cli_piso, String cli_cuenta, String cli_celular, String cli_correo, String cli_direccion) {
        this.id = id;
        this.cli_tipo_id = cli_tipo_id;
        this.cli_nombre = cli_nombre;
        this.cli_apellido = cli_apellido;
        this.cli_piso = cli_piso;
        this.cli_cuenta = cli_cuenta;
        this.cli_celular = cli_celular;
        this.cli_correo = cli_correo;
        this.cli_direccion = cli_direccion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCli_tipo_id() {
        return cli_tipo_id;
    }

    public void setCli_tipo_id(String cli_tipo_id) {
        this.cli_tipo_id = cli_tipo_id;
    }

    public String getCli_nombre() {
        return cli_nombre;
    }

    public void setCli_nombre(String cli_nombre) {
        this.cli_nombre = cli_nombre;
    }

    public String getCli_apellido() {
        return cli_apellido;
    }

    public void setCli_apellido(String cli_apellido) {
        this.cli_apellido = cli_apellido;
    }

    public String getCli_piso() {
        return cli_piso;
    }

    public void setCli_piso(String cli_piso) {
        this.cli_piso = cli_piso;
    }

    public String getCli_cuenta() {
        return cli_cuenta;
    }

    public void setCli_cuenta(String cli_cuenta) {
        this.cli_cuenta = cli_cuenta;
    }

    public String getCli_celular() {
        return cli_celular;
    }

    public void setCli_celular(String cli_celular) {
        this.cli_celular = cli_celular;
    }

    public String getCli_correo() {
        return cli_correo;
    }

    public void setCli_correo(String cli_correo) {
        this.cli_correo = cli_correo;
    }

    public String getCli_direccion() {
        return cli_direccion;
    }

    public void setCli_direccion(String cli_direccion) {
        this.cli_direccion = cli_direccion;
    }

   
   
    
}

