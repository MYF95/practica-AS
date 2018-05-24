package frontController;


import Stateful.Patient;
import Stateful.MyPatientList;
import Stateless.PatientList;
import java.io.IOException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class SearchPatientCommand extends FrontCommand{
    Patient patient = patientBean();
    MyPatientList myPatientList = myPatientListBean();
    PatientList patientList = patientListBean();
    
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
            forward("/patients/searchPatient.jsp");
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
    
    private MyPatientList myPatientListBean() {
        try {
            Context c = new InitialContext();
            return (MyPatientList) c.lookup("java:global/HistorialMedico/HistorialMedico-ejb/MyPatientList!Stateful.MyPatientList");
        } catch (NamingException ne) {
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
    
}
