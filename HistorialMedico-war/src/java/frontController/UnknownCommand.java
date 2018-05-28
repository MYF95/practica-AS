package frontController;

import Singleton.Log;
import Singleton.Stats;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

public class UnknownCommand extends FrontCommand{
    Log log = logBean();
    Stats stats = statsBean();
    
    @Override
    public void process() throws ServletException, IOException {
        stats.addCommandUsage("unknown");
        forward("/unknown.jsp");
    }
    
    private Log logBean() {
        try {
            Context c = new InitialContext();
            return (Log) c.lookup("java:global/HistorialMedico/HistorialMedico-ejb/Log!Singleton.Log");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    private Stats statsBean() {
        try {
            Context c = new InitialContext();
            return (Stats) c.lookup("java:global/HistorialMedico/HistorialMedico-ejb/Stats!Singleton.Stats");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
