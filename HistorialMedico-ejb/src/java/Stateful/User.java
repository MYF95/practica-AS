package Stateful;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;

@Stateful
@LocalBean

public class User {
    String name;
    String dni;
    
    @PostConstruct
    public void init(){
        System.out.println("User::init");
    }

    public User() {
        this.name = "";
        this.dni = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("User::setName::" + name);
        this.name = name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        System.out.println("User::setDni::" + dni);
        this.dni = dni;
    }
    
    public String printAll(){
        return "Nombre: " + name + " <br> DNI: " + dni + " <br> --------------------- <br>";
    }
    
    @PreDestroy
    public void preDestroy(){
        System.out.println("User::preDestroy");
    }
    
    @Remove
    public void reset(){
        System.out.println("User::remove");
        this.name = null;
        this.dni = null;
    }
}
