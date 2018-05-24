package frontController;


import Stateful.Patient;
import Stateful.myPatientList;
import Stateless.PatientList;
import java.io.IOException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class CreatePatientCommand extends FrontCommand{
    Patient patient = patientBean();
    myPatientList myPatientList = myPatientListBean();
    PatientList patientList = patientListBean();
    
    @Override
    public void process() throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        patient.setNombre(request.getParameter("patientName"));
        patient.setDni(request.getParameter("patientDni"));
        patient.setEdad(Integer.parseInt(request.getParameter("patientAge")));
        myPatientList = (myPatientList)session.getAttribute("myPatientList");
        if(patient.getDni().equals("")){
            forward("/createFail.jsp");
        } else {
            patientList.add(patient);
            session.setAttribute("patientList", patientList);
            
            myPatientList.add(patient);
            session.setAttribute("patient", patient);
            session.setAttribute("myPatientList", myPatientList);
            forward("/createPatient.jsp");
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
    
    private PatientList patientListBean() {
        try {
            Context c = new InitialContext();
            return (PatientList) c.lookup("java:global/HistorialMedico/HistorialMedico-ejb/PatientList!Stateless.PatientList");
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }
}

