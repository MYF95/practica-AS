package frontController;


import Entities.Patients;
import Facades.PatientsFacade;
import Singleton.Log;
import Stateful.Patient;
import Stateful.myPatientList;
import Stateless.PatientList;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class CreatePatientCommand extends FrontCommand{
    Patient patient = patientBean();
    myPatientList myPatientList = myPatientListBean();
    PatientList patientList = patientListBean();
    Log log = logBean();
    PatientsFacade pf = patientsFacadeBean();
    
    @Override
    public void process() throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        patient.setNombre(request.getParameter("patientName"));
        patient.setDni(request.getParameter("patientDni"));
        patient.setEdad(Integer.parseInt(request.getParameter("patientAge")));
        myPatientList = (myPatientList)session.getAttribute("myPatientList");
        
        Patients dbPatient = new Patients(); 
        dbPatient.setId(pf.findLastPatient().get(0).getId()+1);
        dbPatient.setDni(request.getParameter("patientDni"));
        dbPatient.setName(request.getParameter("patientName"));
        dbPatient.setAge(Integer.parseInt(request.getParameter("patientAge")));
        
        if(patient.getDni().equals("")){
            forward("/createFail.jsp");
        } else {
            patientList.add(patient);
            session.setAttribute("patientList", patientList);
            
            pf.addPatient(dbPatient);
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

