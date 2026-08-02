
package Modelo;


public class Combo {
    
   
    private String nombre ;

    public Combo() {
    }

    
    public Combo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   
    
    @Override
    public String toString(){
        return this.getNombre();
    }
    
}
