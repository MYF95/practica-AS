package Stateless;

import Stateful.User;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class UserList {
    private ArrayList<User> myList = new ArrayList<>();

    @PostConstruct
    public void init(){
        System.out.println("UserList::init");
    }
    
    public void add(User user){
        System.out.println("UserList::add::" + user.getDni());
        myList.add(user);
    }
    
    public String printAll(){
        if(myList.isEmpty()){
            return " No users recorded";
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
    
    public boolean isLogged(String dni){
        System.out.println("UserList::isLogged::" + dni);
        for(int i = 0; i<myList.size(); i++){
            if(myList.get(i).getDni().equals(dni)){
                return true; 
            }
        }
        return false;
    }
    
    @PreDestroy
    public void preDestroy(){
        System.out.println("UserList::preDestroy");
    }
    
    @Remove
    public void reset(){
        System.out.println("UserList::reset");
    }
    
}

