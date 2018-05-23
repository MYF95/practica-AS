/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stateful;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author myf19
 */
@Stateful
@LocalBean
public class Patient {
    private String nombre;
    private String dni;
    private int edad;
    
    @PostConstruct
    public void init(){
        System.out.println("Patient::init");
    }

    public Patient(String nombre, String dni, int edad) {
        this.nombre = nombre;
        this.dni = dni;
        this.edad = edad;
    }
    
    public Patient(String dni){
        this.nombre = "";
        this.dni = dni;
        this.edad = 0;
    }
    
    public Patient() {
        this.nombre = "";
        this.dni = "";
        this.edad = 0;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        System.out.println("Patient::setNombre()::" + nombre);
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        System.out.println("Patient::setDni()::" + dni);
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        System.out.println("Patient::setEdad()::" + edad);
        this.edad = edad;
    }
    
    public String printAll(){
        return "Nombre: " + nombre + " <br> DNI: " + dni + " <br> Edad: " + edad + " <br> --------------------- <br>";
    }

    public boolean getPatient(String dni){
        System.out.println("Patient::getPatient()::" + dni);
        return this.dni.equals(dni);
    }
    
    @PreDestroy
    public void preDestroy(){
        System.out.println("Patient::preDestroy");
    }
    
    @Remove
    public void reset(){
        System.out.println("Patient::remove");
        this.nombre = null;
        this.dni = null;
        this.edad = 0;
    }
    
}
