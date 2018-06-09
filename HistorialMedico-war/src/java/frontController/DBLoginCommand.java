package frontController;

import Entities.Users;
import Facades.UsersFacade;
import Singleton.Log;
import Singleton.Stats;
import Stateful.User;
import Stateless.UserList;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class DBLoginCommand extends FrontCommand{
    
    Log log = logBean();
    Stats stats = statsBean();
    UsersFacade uf = usersFacadeBean();
    
    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("logged").equals("true")){ //Si el usuario está logueado aprovechamos la operación
            session.setAttribute("user", null);
            session.setAttribute("logged", "false");
            forward("/index.jsp");
        } else {            
            List <Users> user = uf.findUserbyDni(request.getParameter("dni")); 
            if(user != null && user.isEmpty() != true){
                session.setAttribute("user", user.get(0).getName());
                session.setAttribute("userId", user.get(0).getId());
                session.setAttribute("logged", "true");
                stats.addLoggedUser();
                forward("/index.jsp");
            } else {
                session.setAttribute("flash", "El usuario no existe en la BD");
                forward("/index.jsp");
            }
        }
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
    
    private UsersFacade usersFacadeBean() {
        try {
            Context c = new InitialContext();
            return (UsersFacade) c.lookup("java:global/HistorialMedico/HistorialMedico-ejb/UsersFacade!Facades.UsersFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
