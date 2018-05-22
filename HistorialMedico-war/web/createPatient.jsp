<%-- 
    Document   : createPatient
    Created on : 22-may-2018, 17:07:27
    Author     : myf19
--%>
<%@page import="Stateful.myPatientList"%>
<%@page import="Stateful.Patient"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Consulta médica</title>
    </head>
    <body>
        <h1>Rellene el formulario para crear el nuevo usuario</h1>
        
        <form action="FrontServlet">
            Nombre del paciente: <input type="text" name="patientName"> <br>
            DNI: <input type="text" name="patientDni"> <br>
            Edad: <input type="number" name="patientAge"> <br>
            <input type="hidden" name="command" value="CreatePatientCommand">
            <input type="submit">
        </form>
        
        <% 
            myPatientList patientList = (myPatientList)session.getAttribute("patientList");
            Patient patient = (Patient)session.getAttribute("patient");
        %>
            <p>El último paciente tiene como DNI: 
            <%= patient.getDni() %>     
            <p>Todos los historiales: </p>
            <%= patientList.printAll() %>
        
        <p><a href="searchPatient.jsp"> Buscar un paciente </a></p>
        <p><a href="create.jsp"> Crear una consulta </a></p>
        <p><a href="search.jsp"> Buscar una consulta </a></p>
        <p><a href="index.jsp"> Volver al inicio </a></p>
    </body>
</html>
