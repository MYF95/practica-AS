package frontController;


import Singleton.Log;
import Stateful.Patient;
import Stateful.myPatientList;
import Stateless.PatientList;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class SearchPatientCommand extends FrontCommand{
    Patient patient = patientBean();
    myPatientList myPatientList = myPatientListBean();
    PatientList patientList = patientListBean();
    Log log = logBean();
    
    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession();
        String dni = request.getParameter("patientDni");
        patientList = (PatientList)session.getAttribute("patientList");
        patient = patientList.hasPatient(dni);
        if(patient == null){
            forward("/searchFail.jsp");
        } else{
            session.setAttribute("flag", "true");
            session.setAttribute("patient", patient);
            forward("/searchPatient.jsp");
        }
    }
    
    private Patient patientBean() {
        try {
            Context c = new InitialContext();
            return (Patient) c.lookup("java:global/HistorialMedico/HistorialMedico-ejb/Patient!Stateful.Patient");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    private myPatientList myPatientListBean() {
        try {
            Context c = new InitialContext();
            return (myPatientList) c.lookup("java:global/HistorialMedico/HistorialMedico-ejb/myPatientList!Stateful.myPatientList");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    private PatientList patientListBean() {
        try {
            Context c = new InitialContext();
            return (PatientList) c.lookup("java:global/HistorialMedico/HistorialMedico-ejb/PatientList!Stateless.PatientList");
        } catch (NamingException ne) {
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
}
