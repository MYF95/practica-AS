package Stateful;

import java.util.ArrayList;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author myf19
 */
@Stateful
@LocalBean
public class myPatientList {
    
    private ArrayList<Patient> myList;

    public myPatientList() {
        this.myList = new ArrayList<>();
    }

    public ArrayList<Patient> getMyList() { return myList; }

    public void setMyList(ArrayList<Patient> myList) { this.myList = myList; }
    
    public void add(Patient patient){
        myList.add(patient);
    }
    
    public String printAll(){
        if(myList.isEmpty()){
            return " No patients recorded";
        } else {
            ArrayList<String> myArray = new ArrayList<>();
            for(int i = 0; i<myList.size(); i++){
                if(myList.get(i).getDni() != null){
                    myArray.add(myList.get(i).printAll());
                }    
            }
            String old = myArray.toString();
            String newStr = old.replaceAll(",", "");
            newStr = newStr.substring(1, newStr.length()-1);
            return newStr;
        }
    }
    
    // Se podría devolver un arraylist de medicalrecord en caso de querer varios historiales del mismo DNI
    public Patient hasPatient(String dni){
        for(int i = 0; i<myList.size(); i++){
            if(myList.get(i).getPatient(dni)){
                return myList.get(i); 
            }
        }
        return null;
    }
    
}
