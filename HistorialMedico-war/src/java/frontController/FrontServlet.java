package frontController;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Singleton.Log;
import Singleton.Stats;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FrontServlet", urlPatterns = {"/FrontServlet"})
public class FrontServlet extends HttpServlet {
    
    @Override
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FrontCommand command = getCommand(request);
        command.init(getServletContext(), request, response);
        command.process();   
    }
    
    private FrontCommand getCommand(HttpServletRequest request){
        try {
            FrontCommand f = (FrontCommand) getCommandClass(request).newInstance();
            return f;
        } catch (Exception e){
            return new UnknownCommand();
        }
    }
    
    private Class getCommandClass(HttpServletRequest request){
        Class result;
        final String command = "frontController." + (String)request.getParameter("command");
        try {
            result = Class.forName(command);
        }
        catch(ClassNotFoundException e) {
            result = UnknownCommand.class;
        }
        Log.logCommand(command);
        Stats.addCommandUsage(command);
        return result;
    }
    
}
