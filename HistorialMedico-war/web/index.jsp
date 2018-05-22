<%-- 
    Document   : index
    Created on : 22-may-2018, 17:06:29
    Author     : myf19
--%>

<%@page import="Stateful.myPatientList"%>
<%@page import="Stateful.Patient"%>
<%@page import="Stateful.myRecordList"%>
<%@page import="Stateful.MedicalRecord"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Consulta médica</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <% 
            if(session.isNew()){ //Inicialización de los beans
                MedicalRecord record = new MedicalRecord();
                myRecordList myList = new myRecordList();
                Patient patient = new Patient();
                myPatientList patientList = new myPatientList();
                session.setAttribute("list", myList);
                session.setAttribute("record", record);
                session.setAttribute("patient", patient);
                session.setAttribute("patientList", patientList);
            }
        %>
        <h1>Consulta médica</h1>
        <p>Esta es la página principal de las consultas médicas, decida a donde quiere ir:</p>
        <p><a href="create.jsp">Crear una nueva consulta médica</a></p>
        <p><a href="search.jsp">Buscar una consulta médica por DNI</a></p>
        <p><a href="createPatient.jsp">Crear una nuevo paciente</a></p>
        <p><a href="searchPatient.jsp">Buscar un paciente por DNI</a></p>
    </body>
</html>

