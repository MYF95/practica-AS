<%-- 
    Document   : header
    Created on : 22-may-2018, 18:35:43
    Author     : myf19
--%>

<%@page import="Singleton.Log"%>
<%@page import="Singleton.Stats"%>
<%@page import="Stateless.UserList"%>
<%@page import="Stateful.User"%>
<%@page import="Stateless.PatientList"%>
<%@page import="Stateless.MedicalRecordList"%>
<%@page import="Stateful.myPatientList"%>
<%@page import="Stateful.Patient"%>
<%@page import="Stateful.myRecordList"%>
<%@page import="Stateful.MedicalRecord"%>
<%@page import="java.util.Map"%>
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
                <li class="dropdown">
                    <a href="${pageContext.request.contextPath}/index.jsp">Inicio</a>
                </li>
                <li class="dropdown">
                    <a href="javascript:void(0)" class="dropbtn">Consultas médicas</a>
                    <div class="dropdown-content">
                        <a href="${pageContext.request.contextPath}/create.jsp">Crear consulta médica</a>
                        <a href="${pageContext.request.contextPath}/search.jsp">Buscar consulta médica</a>
                        <a href="${pageContext.request.contextPath}/currentRecords.jsp">Mi lista de consultas</a>
                        <a href="${pageContext.request.contextPath}/allRecords.jsp">Todas las consultas médicas</a>
                    </div>
                </li>
                <li class="dropdown">
                    <a href="javascript:void(0)" class="dropbtn">Pacientes</a>
                    <div class="dropdown-content">
                        <a href="${pageContext.request.contextPath}/createPatient.jsp">Crear paciente</a>
                        <a href="${pageContext.request.contextPath}/searchPatient.jsp">Buscar paciente</a>
                        <a href="${pageContext.request.contextPath}/currentPatients.jsp">Mi lista de pacientes</a>
                        <a href="${pageContext.request.contextPath}/allPatients.jsp">Todos los pacientes</a>
                    </div>
                </li>
                <li class="dropdown">
                    <a href="javascript:void(0)" class="dropbtn">Opciones</a>
                    <div class="dropdown-content">
                        <a href="${pageContext.request.contextPath}/allUsers.jsp">Ver usuarios</a>
                        <a href="${pageContext.request.contextPath}/logs.jsp">Ver logs</a>
                        <a href="${pageContext.request.contextPath}/stats.jsp">Ver estadísticas</a>
                        <% if(session.getAttribute("logged") == "true"){ %>
                            <a href="${pageContext.request.contextPath}/logout.jsp">Logout</a>         
                        <% } else { %>
                            <a href="${pageContext.request.contextPath}/login.jsp">Login</a> 
                        <% } %>
                    </div>
                </li>

            </ul>            
        </nav>
        
        <% 
            if(session.isNew()){ //Inicialización de los beans
                MedicalRecord record = new MedicalRecord();
                myRecordList myList = new myRecordList();
                Patient patient = new Patient();
                myPatientList myPatientList = new myPatientList();
                MedicalRecordList medicalRecordList = new MedicalRecordList();
                PatientList patientList = new PatientList();
                User user = new User();
                UserList userList = new UserList();
                session.setAttribute("list", myList);
                session.setAttribute("record", record);
                session.setAttribute("patient", patient);
                session.setAttribute("myPatientList", myPatientList);
                session.setAttribute("medicalRecordList", medicalRecordList);
                session.setAttribute("patientList", patientList);
                session.setAttribute("user", user);
                session.setAttribute("userList", userList);
                session.setAttribute("logged", "false");
            }
            String requestURL = request.getRequestURI();
            Log.logJsp(requestURL);
            Stats.addPageVisit(requestURL);
        %>