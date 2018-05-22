package frontController;


import Stateful.Patient;
import Stateful.myPatientList;
import java.io.IOException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class SearchPatientCommand extends FrontCommand{
    Patient patient = patientBean();
    myPatientList patientList = myPatientListBean();
    
    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession();
        String dni = request.getParameter("patientDni");
        patientList = (myPatientList)session.getAttribute("patientList");
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
            throw new RuntimeException(ne);
        }
    }
    
    private myPatientList myPatientListBean() {
        try {
            Context c = new InitialContext();
            return (myPatientList) c.lookup("java:global/HistorialMedico/HistorialMedico-ejb/myPatientList!Stateful.myPatientList");
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }
    
}
