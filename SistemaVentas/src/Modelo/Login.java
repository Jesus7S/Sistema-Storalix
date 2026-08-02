package Modelo;

/*En esta parte empezaremos por elaborar los valores que usaremos para la parte de acceso del sistema, también elaboraremos los métodos getter y setter 
para que se pueda bridar una propiedad a los valores y también obtener el valor de la propiedad que recibirá los valores que definimos 
en la base de datos. */
public class Login {
    private int id;
    private String nombre;
    private String correo;
    private String pass;
    private String rol;
    private String estado;
    
    public Login (){
        
    }

    public Login(int id, String nombre, String correo, String pass, String rol, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.pass = pass;
        this.rol = rol;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

   
    
}
