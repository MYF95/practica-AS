package Stateless;

import Stateful.MedicalRecord;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateless;

@LocalBean
@Stateless(mappedName="medicalRecordListBean")
/*
 Este listado podrá crear, borrar y modificar. Se guarda entre distintos 
 browsers y es de donde cada usuario podrá ver el listado general.
*/

public class MedicalRecordList {
    private ArrayList<MedicalRecord> myList = new ArrayList<>();
    
    @PostConstruct
    public void init(){
        System.out.println("MedicalRecordList::init");
    }
    
    public void add(MedicalRecord record){
        System.out.println("MedicalRecordList::add::" + record.getDni());
        myList.add(record);
    }
    
    public void remove(MedicalRecord record){
        System.out.println("MedicalRecordList::remove::" + record.getDni());
        myList.remove(record);
    }
    
    public void update(MedicalRecord oldRecord, MedicalRecord newRecord){
        System.out.println("MedicalRecordList::update::" + oldRecord.getDni() + "::" + newRecord.getDni());
        if(oldRecord != null && newRecord != null){
            myList.set(myList.indexOf(oldRecord), newRecord);
        }
    }
    
    public String printAll(){
        if(myList.isEmpty()){
            return " No medical records";
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
    public MedicalRecord hasMedicalRecord(String dni){
        System.out.println("MedicalRecord::hasMedicalRecord::" + dni);
        for(int i = 0; i<myList.size(); i++){
            if(myList.get(i).getRecord(dni)){
                return myList.get(i); 
            }
        }
        return null;
    }
    
    @PreDestroy
    public void preDestroy(){
        System.out.println("MedicalRecordList::preDestroy");
    }
    
    @Remove
    public void reset(){
        System.out.println("MedicalRecordList::removed");
        myList = null;
    }
    
}