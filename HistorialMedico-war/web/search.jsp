<%@page import="data.myRecordList"%>
<%@page import="data.MedicalRecord"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscar Consulta Médica</title>
    </head>
    <body>
        <h1>Busque una consulta por DNI</h1>
        <form action="FrontServlet">
            DNI: <input type="text" name="dni">
            <input type="hidden" name="command" value="SearchCommand">
            <input type="submit">
        </form>
        
        <%  
            if(session.isNew()){
                MedicalRecord record = new MedicalRecord();
                myRecordList myList = new myRecordList();
                session.setAttribute("list", myList);
                session.setAttribute("record", record);
            }
            myRecordList myList = (myRecordList)session.getAttribute("list");
            MedicalRecord record = (MedicalRecord)session.getAttribute("record");
            
        %>
        <% if(session.getAttribute("flag") == "true") { %>
        <%= "<p>Usuario encontrado! Aquí tiene los datos de la consulta:</p>" %>
        <%= "<p>DNI: " + record.getDni() + "</p>"%>
        <%= "<p>Info: " + record.getInfo() + "</p>"%>
        <%= "<p>Date: " + record.getDate() + "</p>"%>
        <% } session.setAttribute("flag", "false"); %>
        
        <p>Todos los historiales: </p>
            <%= myList.printAll() %>
        <p><a href="index.html">Volver al inicio</a></p>
        <p><a href="create.jsp">Crear una consulta</a></p>
    </body>
</html>
