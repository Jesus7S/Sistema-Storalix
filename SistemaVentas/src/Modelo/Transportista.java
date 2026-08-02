/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jesus
 */
public class Transportista {
        private String id_Transportista ;
    private String Tra_Nombre;
    private String Tra_NoCaja;
    private String Tra_Transportadora;

    public Transportista() {
    }

    public Transportista(String id_Transportista, String Tra_Nombre, String Tra_NoCaja, String Tra_Transportadora) {
        this.id_Transportista = id_Transportista;
        this.Tra_Nombre = Tra_Nombre;
        this.Tra_NoCaja = Tra_NoCaja;
        this.Tra_Transportadora = Tra_Transportadora;
    }

    public String getId_Transportista() {
        return id_Transportista;
    }

    public void setId_Transportista(String id_Transportista) {
        this.id_Transportista = id_Transportista;
    }

    public String getTra_Nombre() {
        return Tra_Nombre;
    }

    public void setTra_Nombre(String Tra_Nombre) {
        this.Tra_Nombre = Tra_Nombre;
    }

    public String getTra_NoCaja() {
        return Tra_NoCaja;
    }

    public void setTra_NoCaja(String Tra_NoCaja) {
        this.Tra_NoCaja = Tra_NoCaja;
    }

    public String getTra_Transportadora() {
        return Tra_Transportadora;
    }

    public void setTra_Transportadora(String Tra_Transportadora) {
        this.Tra_Transportadora = Tra_Transportadora;
    }

   

   
    
}
