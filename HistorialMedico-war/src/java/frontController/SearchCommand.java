package frontController;


import data.MedicalRecord;
import data.myRecordList;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class SearchCommand extends FrontCommand{
    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession();
        String dni = request.getParameter("dni");
        myRecordList myList = (myRecordList)session.getAttribute("list");
        MedicalRecord record = myList.hasMedicalRecord(dni);
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
}
