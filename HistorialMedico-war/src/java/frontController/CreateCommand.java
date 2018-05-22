package frontController;


import Stateful.MedicalRecord;
import Stateful.myRecordList;
import java.io.IOException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class CreateCommand extends FrontCommand{
    MedicalRecord record = medicalRecordBean();
    myRecordList myList = myRecordListBean();
    
    @Override
    public void process() throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        record.setDni(request.getParameter("dni"));
        record.setInfo(request.getParameter("info"));
        record.setDate(request.getParameter("date"));
        myList = (myRecordList)session.getAttribute("list");
        if(record.getDni().equals("")){
            forward("/createFail.jsp");
        } else if (record.getDni().equals("as")){
            record.setDni("as");
            record.setInfo("Contenido secreto via unknown");
            myList.add(record);
            session.setAttribute("record", record);
            forward("/create.jsp");
        } else {
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
            throw new RuntimeException(ne);
        }
    }
    
    private myRecordList myRecordListBean() {
        try {
            Context c = new InitialContext();
            return (myRecordList) c.lookup("java:global/HistorialMedico/HistorialMedico-ejb/myRecordList!Stateful.myRecordList");
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }
}
