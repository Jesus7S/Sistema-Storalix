/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jesus
 */
public class Empleado {
   private String id_Empleado;
    private String Nombre;
    private String Cargo;
    private String Departamento;

    public Empleado() {
    }

    public Empleado(String id_Empleado, String Nombre, String Cargo, String Departamento) {
        this.id_Empleado = id_Empleado;
        this.Nombre = Nombre;
        this.Cargo = Cargo;
        this.Departamento = Departamento;
    }

    public String getId_Empleado() {
        return id_Empleado;
    }

    public void setId_Empleado(String id_Empleado) {
        this.id_Empleado = id_Empleado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }

    public String getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(String Departamento) {
        this.Departamento = Departamento;
    }

   
    
    
}
