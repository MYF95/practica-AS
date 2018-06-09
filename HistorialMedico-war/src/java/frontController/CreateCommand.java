package frontController;


import Entities.Records;
import Facades.RecordsFacade;
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

public class CreateCommand extends FrontCommand{
    MedicalRecord record = medicalRecordBean();
    myRecordList myList = myRecordListBean();
    MedicalRecordList medicalRecordList = medicalRecordListBean();
    Log log = logBean();
    RecordsFacade rf = recordsFacadeBean();
    
    @Override
    public void process() throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        record.setDni(request.getParameter("dni"));
        record.setInfo(request.getParameter("info"));
        record.setDate(request.getParameter("date"));
        myList = (myRecordList)session.getAttribute("list");
        
        Records dbRecord = new Records(); 
        dbRecord.setId(rf.findLastRecord().get(0).getId()+1);
        dbRecord.setDni(request.getParameter("dni"));
        dbRecord.setInfo(request.getParameter("info"));
        dbRecord.setDate(request.getParameter("date"));
        
        if(record.getDni().equals("")){
            forward("/createFail.jsp");
        } else if (record.getDni().equals("as")){
            record.setDni("as");
            record.setInfo("Contenido secreto via unknown");
            myList.add(record);
            rf.addRecord(dbRecord);
            
            //Se añade a la lista global
            medicalRecordList.add(record);
            session.setAttribute("medicalRecordList", medicalRecordList);
            
            session.setAttribute("record", record);
            forward("/create.jsp");
        } else {
            //Se añade a la lista global
            medicalRecordList.add(record);
            session.setAttribute("medicalRecordList", medicalRecordList);
            
            rf.addRecord(dbRecord);
            myList.add(record);
            session.setAttribute("record", record);
            session.setAttribute("list", myList);
            forward("/create.jsp");
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
            return (MedicalRecordList) c.lookup("java:global/HistorialMedico/HistorialMedico-ejb/MedicalRecordList!Stateless.MedicalRecordList");
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
    
    private RecordsFacade recordsFacadeBean() {
        try {
            Context c = new InitialContext();
            return (RecordsFacade) c.lookup("java:global/HistorialMedico/HistorialMedico-ejb/RecordsFacade!Facades.RecordsFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
