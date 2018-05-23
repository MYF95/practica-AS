<%-- 
    Document   : header
    Created on : 22-may-2018, 18:35:43
    Author     : myf19
--%>

<%@page import="Stateless.MedicalRecordList"%>
<%@page import="Stateful.myPatientList"%>
<%@page import="Stateful.Patient"%>
<%@page import="Stateful.myRecordList"%>
<%@page import="Stateful.MedicalRecord"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Consulta médica</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom.css">
    </head>
    <body>
        <nav>
            <ul>
                <li><a href="index.jsp">Inicio</a></li>
                <li class="dropdown">
                    <a href="javascript:void(0)" class="dropbtn">Consultas médicas</a>
                    <div class="dropdown-content">
                        <a href="create.jsp">Crear consulta médica</a>
                        <a href="search.jsp">Buscar consulta médica</a>
                        <a href="currentRecords.jsp">Mi lista de consultas</a>
                        <a href="allRecords.jsp">Todas las consultas médicas</a>
                    </div>
                </li>
                <li class="dropdown">
                    <a href="javascript:void(0)" class="dropbtn">Pacientes</a>
                    <div class="dropdown-content">
                        <a href="createPatient.jsp">Crear paciente</a>
                        <a href="searchPatient.jsp">Buscar paciente</a>
                        <a href="#">Mi lista de pacientes</a>
                        <a href="#">Todos los pacientes</a>
                    </div>
                </li>
            </ul>            
        </nav>
        
        <% 
            if(session.isNew()){ //Inicialización de los beans
                MedicalRecord record = new MedicalRecord();
                myRecordList myList = new myRecordList();
                Patient patient = new Patient();
                myPatientList patientList = new myPatientList();
                MedicalRecordList medicalRecordList = new MedicalRecordList();
                session.setAttribute("list", myList);
                session.setAttribute("record", record);
                session.setAttribute("patient", patient);
                session.setAttribute("patientList", patientList);
                session.setAttribute("medicalRecordList", medicalRecordList);
            }
        %>