/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontController;

import Entities.Patients;
import Facades.PatientsFacade;
import Singleton.Log;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author myf19
 */
public class UpdatePatientCommand extends FrontCommand{

    Log log = logBean();
    PatientsFacade pf = patientsFacadeBean();
    
    @Override
    public void process() throws ServletException, IOException {      
        HttpSession session = request.getSession();
        Patients dbPatient = new Patients();
        dbPatient.setId(Integer.parseInt(request.getParameter("patientId")));
        dbPatient.setDni(request.getParameter("patientDni"));
        dbPatient.setName(request.getParameter("patientName"));
        dbPatient.setAge(Integer.parseInt(request.getParameter("patientAge")));
        pf.updatePatient(dbPatient);
        session.setAttribute("flash", "Paciente actualizado");
        forward("/index.jsp");
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