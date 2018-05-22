/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stateful;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

/**
 *
 * @author myf19
 */
@Stateful
@LocalBean
public class MedicalRecord {
    private String dni;
    private String info;
    private String date;

    public MedicalRecord(String dni, String info, String date) {
        this.dni = dni;
        this.info = info;
        this.date = date;
    }
    
    public MedicalRecord(String dni){
        this.dni = dni;
        this.info = "";
        this.date = "";
    }
    
    public MedicalRecord(){
        this.dni = "";
        this.info = "";
        this.date = "";
    }
    
    public void setDni(String dni) { 
        System.out.println("MedicalRecord::setDni()::" + dni);
        this.dni = dni; 
    }

    public void setInfo(String info) { 
        System.out.println("MedicalRecord::setInfo()::" + info);
        this.info = info; 
    }

    public void setDate(String date) { 
        System.out.println("MedicalRecord::setDate()::" + date);
        this.date = date; 
    }
      
    public String getDni() { 
        System.out.println("MedicalRecord::getDni()");
        return dni; 
    }

    public String getInfo() { 
        System.out.println("MedicalRecord::getInfo()");
        return info; 
    }
    
    public String getDate() { 
        System.out.println("MedicalRecord::getInfo()");
        return date; 
    }

    public String printAll(){
        System.out.println("MedicalRecord::printAll()");
        return "Dni: " + dni + " <br> Observaciones: " + info + " <br> Fecha: " + date + " <br> --------------------- <br>";
    }

    public boolean getRecord(String dni){
        System.out.println("MedicalRecord::getRecord()::" + dni);
        return this.dni.equals(dni);
    }
}
