/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stateful;
import java.util.ArrayList;
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
public class myRecordList {
    private ArrayList<MedicalRecord> myList;
    
    @PostConstruct
    public void init(){
        System.out.println("myRecordList::init");
    }

    public myRecordList() {
        this.myList = new ArrayList<>();
    }

    public ArrayList<MedicalRecord> getMyList() { return myList; }

    public void setMyList(ArrayList<MedicalRecord> myList) { this.myList = myList; }
    
    public void add(MedicalRecord mr){
        System.out.println("myRecordList::add::" + mr.getDni());
        myList.add(mr);
    }
    
    public String printAll(){
        if(myList.isEmpty()){
            return " No hay historial médicos creados en la sesión";
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
        System.out.println("myRecordList::hasMedicalRecord::" + dni);
        for(int i = 0; i<myList.size(); i++){
            if(myList.get(i).getRecord(dni)){
                return myList.get(i); 
            }
        }
        return null;
    }
    
    @PreDestroy
    public void preDestroy(){
        System.out.println("myRecordList::preDestroy");
    }
    
    @Remove
    public void reset(){
        System.out.println("myRecordList::remove");
        this.myList = null;
    }
}