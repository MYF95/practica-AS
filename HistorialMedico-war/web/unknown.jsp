<%-- 
    Document   : unknown
    Created on : 26-feb-2018, 9:14:44
    Author     : myf19
--%>

<%@page import="Stateful.MedicalRecord"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! MedicalRecord record = new MedicalRecord(); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página no encontrada</title>
    </head>
    <body>
        <h1>Oops, ha ocurrido un error.</h1>
        <p>Cómo has llegado aquí?</p>
        <% record = (MedicalRecord)session.getAttribute("record"); %>
        <p>Esto es: <%= record.getInfo() %>
            
        <p><a href="create.jsp"> Crear una consulta </a></p>  
        <p><a href="search.jsp"> Buscar una consulta </a></p>
        <p><a href="createPatient.jsp"> Crear un paciente </a></p>
        <p><a href="searchPatient.jsp"> Buscar un paciente </a></p>
        <p><a href="index.jsp"> Volver al inicio </a></p>
    </body>
</html>
