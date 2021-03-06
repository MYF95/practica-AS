package frontController;


import Singleton.Log;
import Stateful.MedicalRecord;
import Stateful.myRecordList;
import Stateless.MedicalRecordList;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class SearchCommand extends FrontCommand{
    MedicalRecord record = medicalRecordBean();
    myRecordList myList = myRecordListBean();
    MedicalRecordList medicalRecordList = medicalRecordListBean();
    Log log = logBean();
    
    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession();
        String dni = request.getParameter("dni");
        medicalRecordList = (MedicalRecordList)session.getAttribute("medicalRecordList");
        record = medicalRecordList.hasMedicalRecord(dni);
        if(record == null){
            forward("/searchFail.jsp");
        } else if(record.getDni().equals("as")){
            record = (MedicalRecord)session.getAttribute("record");
            session.setAttribute("record", record);
            forward("/unknown.jsp");
        } else{
            session.setAttribute("flag", "true");
            session.setAttribute("record", record);
            forward("/search.jsp");
        }
    }
    
    private MedicalRecord medicalRecordBean() {
        try {
            Context c = new InitialContext();
            return (MedicalRecord) c.lookup("java:global/HistorialMedico/HistorialMedico-ejb/MedicalRecord!Stateful.MedicalRecord");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    private myRecordList myRecordListBean() {
        try {
            Context c = new InitialContext();
            return (myRecordList) c.lookup("java:global/HistorialMedico/HistorialMedico-ejb/myRecordList!Stateful.myRecordList");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    private MedicalRecordList medicalRecordListBean() {
        try {
            Context c = new InitialContext();
            return medicalRecordList = (MedicalRecordList) c.lookup("java:global/HistorialMedico/HistorialMedico-ejb/MedicalRecordList!Stateless.MedicalRecordList");
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
    
}
