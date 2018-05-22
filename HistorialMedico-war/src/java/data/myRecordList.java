/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;

/**
 *
 * @author myf19
 */
public class myRecordList {
    private ArrayList<MedicalRecord> myList;

    public myRecordList() {
        this.myList = new ArrayList<>();
    }

    public ArrayList<MedicalRecord> getMyList() { return myList; }

    public void setMyList(ArrayList<MedicalRecord> myList) { this.myList = myList; }
    
    public void add(MedicalRecord mr){
        myList.add(mr);
    }
    
    public String printAll(){
        if(myList.isEmpty()){
            return "No medical records";
        } else {
            ArrayList<String> myArray = new ArrayList<>();
            for(int i = 0; i<myList.size(); i++){
                if(myList.get(i).getDni() != null){
                    myArray.add(myList.get(i).printAll());
                }    
            }
            String old = myArray.toString();
            String newStr = old.replaceAll(",", "");
            return newStr;
        }
    }
    
    // Se podría devolver un arraylist de medicalrecord en caso de querer varios historiales del mismo DNI
    public MedicalRecord hasMedicalRecord(String dni){
        for(int i = 0; i<myList.size(); i++){
            if(myList.get(i).getRecord(dni)){
                return myList.get(i); 
            }
        }
        return null;
    }
}
