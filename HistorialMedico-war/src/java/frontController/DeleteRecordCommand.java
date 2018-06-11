/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontController;

import Entities.Records;
import Facades.RecordsFacade;
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
public class DeleteRecordCommand extends FrontCommand{

    Log log = logBean();
    RecordsFacade rf = recordsFacadeBean();
    
    @Override
    public void process() throws ServletException, IOException { 
        HttpSession session = request.getSession();
        Records dbRecord = new Records();
        dbRecord.setId(Integer.parseInt(request.getParameter("recordId")));
        rf.deleteRecord(dbRecord);
        session.setAttribute("flash", "Consulta borrada");
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