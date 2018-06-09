package frontController;

import Entities.Patients;
import Facades.PatientsFacade;
import Singleton.Log;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

public class ShowPatientCommand extends FrontCommand{

    PatientsFacade pf = patientsFacadeBean();
    Log log = logBean();
    
    @Override
    public void process() throws ServletException, IOException{
        int index = 0;
        if(request.getParameter("index") != null){
            index = Integer.parseInt(request.getParameter("index"));
        }
        List<Patients> patients = pf.findRange(new int[]{index, index+2});
        request.setAttribute("list", patients);
        request.setAttribute("index", index);
        forward("/dbPatients.jsp");
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
    
    private PatientsFacade patientsFacadeBean() {
        try {
            Context c = new InitialContext();
            return (PatientsFacade) c.lookup("java:global/HistorialMedico/HistorialMedico-ejb/PatientsFacade!Facades.PatientsFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
