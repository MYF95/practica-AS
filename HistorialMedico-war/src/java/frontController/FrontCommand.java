package frontController;

import Stateless.MedicalRecordList;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class FrontCommand {
    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    
    public void init(
            ServletContext servContext,
            HttpServletRequest servRequest, 
            HttpServletResponse servResponse){
        this.context = servContext;
        this.request = servRequest;
        this.response = servResponse;
    }
    
    public abstract void process() throws ServletException, IOException;
    
    public void forward(String target) throws ServletException, IOException{
        RequestDispatcher dp = context.getRequestDispatcher(target);
        dp.forward(request, response);
    }
}
