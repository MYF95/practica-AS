package Singleton;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@LocalBean
public class Log {
    
    private static final String path = "C:\\Users\\myf19\\Desktop\\Universidad\\2017-2018\\2º Cuatrimestre\\AS\\Práctica 2\\Logs\\systemLog.log";
    private static final Logger log = Logger.getLogger(Log.class.getName());;
    FileHandler files;
    private static int secs = 0;

    @PostConstruct
    public void init() {
        try {
            files = new FileHandler(path);
            log.addHandler(files);
            log.info("Starting Log Bean...");
        } catch (IOException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Schedule(hour = "*", minute = "*", second = "*/1")
    @Lock(LockType.WRITE)
    public void increaseTime() {
        secs++;
    }

    //@Schedule(hour = "*", minute = "*", second = "*/10")
    /*
    @Lock(LockType.WRITE)
    public void actualizarLog() {
        if(secs>=5){
            log.log(Level.INFO,"Working...");
            secs=0;
        }
    }
    */
    
    @Lock(LockType.WRITE)
    public static void logJsp(String jsppath) {
        log.log(Level.INFO, jsppath);
        secs=0;
    }

    @Lock(LockType.WRITE)
    public static void logCommand(String command) {
        log.log(Level.INFO, command);
        secs=0;
    }

    @Lock(LockType.WRITE)
    public static void logException(String exception) {
        log.log(Level.INFO, exception);
        secs=0;
    }

    @Lock(LockType.READ)
    public static String readLog() {
        String result = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            try {
                int counter = 0;
                String line = reader.readLine();
                while (line != null) {
                    result += line + "<br>";
                    line = reader.readLine();
                }
            } catch (IOException ex) {
                Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
                Log.logException(ex.getMessage());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
            Log.logException(ex.getMessage());
        }
        return result;
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("Log bean destroyed");
    }
}

