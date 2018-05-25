package frontController;

import Stateful.User;
import Stateless.UserList;
import java.io.IOException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class LoginCommand extends FrontCommand{
    
    User user = userBean();
    UserList userList = userListBean();
    
    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("logged").equals("true")){ //Si el usuario está logueado aprovechamos la operación
            session.setAttribute("user", userBean());
            session.setAttribute("logged", "false");
            user.reset();
            forward("/index.jsp");
        } else {
            user.setDni(request.getParameter("dni"));
            user.setName(request.getParameter("nombre"));
            if(userList.isLogged(user.getDni()) != null){
                session.setAttribute("user", userList.isLogged(user.getDni()));
                session.setAttribute("logged", "true");
                forward("/index.jsp");
            } else {
                session.setAttribute("user", user);
                session.setAttribute("logged", "true");
                userList.add(user);
                session.setAttribute("userList", userList);
                forward("/index.jsp");
            }            
        }
    }
    
    private User userBean() {
        try {
            Context c = new InitialContext();
            return (User) c.lookup("java:global/HistorialMedico/HistorialMedico-ejb/User!Stateful.User");
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }
    
    private UserList userListBean() {
        try {
            Context c = new InitialContext();
            return (UserList) c.lookup("java:global/HistorialMedico/HistorialMedico-ejb/UserList!Stateless.UserList");
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }
}
