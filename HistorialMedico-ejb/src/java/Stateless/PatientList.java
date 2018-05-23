package Stateless;

import Stateful.MedicalRecord;
import Stateful.Patient;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class PatientList {
    private ArrayList<Patient> myList = new ArrayList<>();

    @PostConstruct
    public void init(){
        System.out.println("PatientList::init");
    }
    
    public void add(Patient patient){
        System.out.println("PatientList::add::" + patient.getDni());
        myList.add(patient);
    }
    
    public void remove(Patient patient){
        System.out.println("PatientList::remove::" + patient.getDni());
        myList.remove(patient);
    }
    
    public void update(Patient oldPatient, Patient newPatient){
        System.out.println("PatientList::update::" + oldPatient.getDni() + "::" + newPatient.getDni());
        if(oldPatient != null && newPatient != null){
            myList.set(myList.indexOf(oldPatient), newPatient);
        }
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
        System.out.println("PatientList::hasPatient::" + dni);
        for(int i = 0; i<myList.size(); i++){
            if(myList.get(i).getPatient(dni)){
                return myList.get(i); 
            }
        }
        return null;
    }
    
    @PreDestroy
    public void preDestroy(){
        System.out.println("PatientList::preDestroy");
    }
    
    @Remove
    public void reset(){
        System.out.println("PatientList::reset");
    }
    
}
