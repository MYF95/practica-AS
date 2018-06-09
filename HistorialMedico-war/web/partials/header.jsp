<%-- 
    Document   : header
    Created on : 22-may-2018, 18:35:43
    Author     : myf19
--%>

<%@page import="Entities.Records"%>
<%@page import="Entities.Patients"%>
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
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.util.List"%>
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
                        <a href="${pageContext.request.contextPath}/allRecords.jsp">Todas las consultas médicas en sesión</a>
                    </div>
                </li>
                <li class="dropdown">
                    <a href="javascript:void(0)" class="dropbtn">Pacientes</a>
                    <div class="dropdown-content">
                        <a href="${pageContext.request.contextPath}/createPatient.jsp">Crear paciente</a>
                        <a href="${pageContext.request.contextPath}/searchPatient.jsp">Buscar paciente</a>
                        <a href="${pageContext.request.contextPath}/currentPatients.jsp">Mi lista de pacientes</a>
                        <a href="${pageContext.request.contextPath}/allPatients.jsp">Todos los pacientes en sesión</a>
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
                <li class="dropdown">
                    <a href="javascript:void(0)" class="dropbtn">Base de datos</a>
                    <div class="dropdown-content">
                        <a href="${pageContext.request.contextPath}/dbPatients.jsp">Ver pacientes</a>
                        <a href="${pageContext.request.contextPath}/dbRecords.jsp">Ver consultas</a>
                        <a href="${pageContext.request.contextPath}/dbUsers.jsp">Ver usuarios</a>
                        <a href="${pageContext.request.contextPath}/dbPatientRecords.jsp">Ver consultas relacionadas</a>
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
                
                //DB
                Patients dbPatient = new Patients();
                Records dbRecord = new Records();
                
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
            Log.logJsp(request.getRequestURI());
            Stats.addPageVisit(request.getRequestURI());
        %>