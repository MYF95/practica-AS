package Singleton;

import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@LocalBean
public class Stats {
    
    private static int loggedUsers = 0;
    private static HashMap<String,Integer> pageVisits = new HashMap<>();
    private static HashMap<String,Integer> commandUsage = new HashMap<>();
    
    @PostConstruct
    public void init(){
        System.out.println("Stats bean starting...");
    }
    
    @Lock(LockType.READ)
    public static int getLoggedUsers() {
        System.out.println("Stats::getLoggedUsers()");
        return loggedUsers;
    }
    
    @Lock(LockType.WRITE)
    public static void addLoggedUser(){
        System.out.println("Stats::newLoggedUser");
        loggedUsers++;
    }

    @Lock(LockType.READ)
    public static HashMap<String,Integer> getPageVisits() {
        System.out.println("Stats::getPageVisits()");
        return pageVisits;
    }
    
    @Lock(LockType.WRITE)
    public static void addPageVisit(String page){
        System.out.println("Stats::addPageVisit::+" + page);
        if(pageVisits.containsKey(page)){
            pageVisits.put(page, pageVisits.get(page)+1);
        } else {
            pageVisits.put(page, 1);
        }
    }
    
    @Lock(LockType.READ)
    public static HashMap<String,Integer> getCommandUsage() {
        System.out.println("Stats::getCommandUsage()");
        return commandUsage;
    }
    
    @Lock(LockType.WRITE)
    public static void addCommandUsage(String command){
        System.out.println("Stats::addCommandUsage::+" + command);
        if(commandUsage.containsKey(command)){
            commandUsage.put(command, commandUsage.get(command)+1);
        } else {
            commandUsage.put(command, 1);
        }
    }
    
    @PreDestroy
    public void preDestroy(){
        System.out.println("Stats bean destroyed");
    }
}