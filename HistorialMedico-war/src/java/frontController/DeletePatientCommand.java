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

/**
 *
 * @author myf19
 */
public class DeletePatientCommand extends FrontCommand{

    Log log = logBean();
    PatientsFacade pf = patientsFacadeBean();
    
    @Override
    public void process() throws ServletException, IOException {      
        Patients dbPatient = new Patients();
        dbPatient.setId(Integer.parseInt(request.getParameter("patientId")));
        pf.deletePatient(dbPatient);
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