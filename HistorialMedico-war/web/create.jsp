<%@page import="data.myRecordList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="data.MedicalRecord"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Consulta médica</title>
    </head>
    <body>
        <h1>Rellene el formulario para crear la nueva consulta</h1>
        
        <form action="FrontServlet">
            DNI: <input type="text" name="dni"> <br>
            Detalles de la consulta: <textarea name="info"></textarea> <br>
            Fecha: <input type="date" name="date"> <br>
            <input type="hidden" name="command" value="CreateCommand">
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
            <p>La última consulta creada tiene como DNI: 
            <%= record.getDni() %>     
            <p>Todos los historiales: </p>
            <%= myList.printAll() %>
        <p><a href="index.html">Volver al inicio</a></p>
        <p><a href="search.jsp">Buscar una consulta</a></p>
    </body>
</html>