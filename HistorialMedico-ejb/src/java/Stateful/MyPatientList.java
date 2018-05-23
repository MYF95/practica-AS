package Stateful;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;

@Stateful
@LocalBean
public class MyPatientList {
    private ArrayList<Patient> myList;

    @PostConstruct
    public void init(){
        System.out.println("MyPatientList::init");
    }
    
    public MyPatientList() {
        this.myList = new ArrayList<>();
    }

    public ArrayList<Patient> getMyList() { return myList; }

    public void setMyList(ArrayList<Patient> myList) { this.myList = myList; }
    
    public void add(Patient patient){
        System.out.println("MyPatientList::add::" + patient.getDni());
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
        System.out.println("MyPatientList::hasPatient::" + dni);
        for(int i = 0; i<myList.size(); i++){
            if(myList.get(i).getPatient(dni)){
                return myList.get(i); 
            }
        }
        return null;
    }
    
    @PreDestroy
    public void preDestroy(){
        System.out.println("MyPatientList::preDestroy");
    }
    
    @Remove
    public void reset(){
        System.out.println("MyPatientList::reset");
    }
    
}

