<%-- 
    Document   : searchPatient
    Created on : 22-may-2018, 17:07:38
    Author     : myf19
--%>

<%@page import="Stateful.myPatientList"%>
<%@page import="Stateful.Patient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscar paciente</title>
    </head>
    <body>
        <h1>Busque un paciente por DNI</h1>
        <form action="FrontServlet">
            DNI: <input type="text" name="patientDni">
            <input type="hidden" name="command" value="SearchPatientCommand">
            <input type="submit">
        </form>
        
        <%  
            myPatientList patientList = (myPatientList)session.getAttribute("patientList");
            Patient patient = (Patient)session.getAttribute("patient");
            
        %>
        <% if(session.getAttribute("flag") == "true") { %>
        <%= "<p>Paciente encontrado! Aquí tiene los datos del paciente:</p>" %>
        <%= "<p>Nombre: " + patient.getNombre() + "</p>"%>
        <%= "<p>DNI: " + patient.getDni() + "</p>"%>
        <%= "<p>Edad: " + patient.getEdad() + "</p>"%>
        <% } session.setAttribute("flag", "false"); %>
        
        <p>Todos los historiales: </p>
            <%= patientList.printAll() %>
            
        <p><a href="createPatient.jsp"> Crear un paciente </a></p>
        <p><a href="create.jsp"> Crear una consulta </a></p>
        <p><a href="search.jsp"> Buscar una consulta </a></p>
        <p><a href="index.jsp"> Volver al inicio </a></p>
    </body>
</html>
