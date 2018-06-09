package frontController;

import Entities.Records;
import Facades.RecordsFacade;
import Singleton.Log;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

public class ShowRecordCommand extends FrontCommand{

    RecordsFacade rf = recordsFacadeBean();
    Log log = logBean();
    
    @Override
    public void process() throws ServletException, IOException{
        int index = 0;
        if(request.getParameter("indexRecord") != null){
            index = Integer.parseInt(request.getParameter("indexRecord"));
        }
        List<Records> records = rf.findRange(new int[]{index, index+2});
        request.setAttribute("records", records);
        request.setAttribute("indexRecord", index);
        forward("/dbRecords.jsp");
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

