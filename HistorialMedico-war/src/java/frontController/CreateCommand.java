package frontController;


import data.MedicalRecord;
import data.myRecordList;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class CreateCommand extends FrontCommand{
    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession();
        MedicalRecord record = new MedicalRecord(request.getParameter("dni"), request.getParameter("info"), request.getParameter("date"));
        myRecordList myList = (myRecordList)session.getAttribute("list");
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
}
