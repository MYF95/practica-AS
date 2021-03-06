package frontController;

import Singleton.Log;
import Singleton.Stats;
import Stateful.User;
import Stateless.UserList;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class LoginCommand extends FrontCommand{
    
    User user = userBean();
    UserList userList = userListBean();
    Log log = logBean();
    Stats stats = statsBean();
    
    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("logged").equals("true")){ //Si el usuario está logueado aprovechamos la operación
            session.setAttribute("user", "");
            session.setAttribute("logged", "false");
            user.reset();
            forward("/index.jsp");
        } else {
            user.setDni(request.getParameter("dni"));
            user.setName(request.getParameter("nombre"));
            if(userList.isLogged(user.getDni()) != null){
                session.setAttribute("user", userList.isLogged(user.getDni()).getName());
                session.setAttribute("logged", "true");
                stats.addLoggedUser();
                forward("/index.jsp");
            } else {
                session.setAttribute("user", user.getName());
                session.setAttribute("logged", "true");
                userList.add(user);
                session.setAttribute("userList", userList);
                stats.addLoggedUser();
                forward("/index.jsp");
            }            
        }
    }
    
    private User userBean() {
        try {
            Context c = new InitialContext();
            return (User) c.lookup("java:global/HistorialMedico/HistorialMedico-ejb/User!Stateful.User");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    private UserList userListBean() {
        try {
            Context c = new InitialContext();
            return (UserList) c.lookup("java:global/HistorialMedico/HistorialMedico-ejb/UserList!Stateless.UserList");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
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
}
