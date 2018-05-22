<%@page import="Stateful.myRecordList"%>
<%@page import="Stateful.MedicalRecord"%>
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
            myRecordList myList = (myRecordList)session.getAttribute("list");
            MedicalRecord record = (MedicalRecord)session.getAttribute("record");  
        %>
        <% if(session.getAttribute("flag") == "true") { %>
        <%= "<p>Consulta encontrada! Aquí tiene los datos de la consulta:</p>" %>
        <%= "<p>DNI: " + record.getDni() + "</p>"%>
        <%= "<p>Info: " + record.getInfo() + "</p>"%>
        <%= "<p>Date: " + record.getDate() + "</p>"%>
        <% } session.setAttribute("flag", "false"); %>
        
        <p>Todos los historiales: </p>
            <%= myList.printAll() %>
            
        <p><a href="create.jsp"> Crear una consulta </a></p>
        <p><a href="createPatient.jsp"> Crear un paciente </a></p>
        <p><a href="searchPatient.jsp"> Buscar un paciente </a></p>
        <p><a href="index.jsp"> Volver al inicio </a></p>
    </body>
</html>
