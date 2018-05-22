package frontController;


import Stateful.Patient;
import Stateful.myPatientList;
import java.io.IOException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class CreatePatientCommand extends FrontCommand{
    Patient patient = patientBean();
    myPatientList patientList = myPatientListBean();
    
    @Override
    public void process() throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        patient.setNombre(request.getParameter("patientName"));
        patient.setDni(request.getParameter("patientDni"));
        patient.setEdad(Integer.parseInt(request.getParameter("patientAge")));
        patientList = (myPatientList)session.getAttribute("patientList");
        if(patient.getDni().equals("")){
            forward("/createFail.jsp");
        } else {
            patientList.add(patient);
            session.setAttribute("patient", patient);
            session.setAttribute("patientList", patientList);
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
}

