<%@page import="Facades.RecordsFacade"%>
<%@page import="Facades.PatientsFacade"%>
<%@page import="Entities.Patientrecords"%>
<%@page import="Facades.PatientrecordsFacade"%>
<%@include file="partials/header.jsp" %>

<h1>Todas las consultas pertenecientes a usuarios en la BD</h1>

<%  PatientrecordsFacade prf = InitialContext.doLookup("java:global/HistorialMedico/HistorialMedico-ejb/PatientrecordsFacade!Facades.PatientrecordsFacade");
    PatientsFacade pf = InitialContext.doLookup("java:global/HistorialMedico/HistorialMedico-ejb/PatientsFacade!Facades.PatientsFacade");
    RecordsFacade rf = InitialContext.doLookup("java:global/HistorialMedico/HistorialMedico-ejb/RecordsFacade!Facades.RecordsFacade");
    List <Patientrecords> patientrecords = prf.showPatientrecords(); 
    List <Patients> patients; 
    List <Records> records; %>

<table>
    <tr>
        <th>ID</th>
        <th>ID Paciente</th>
        <th>DNI</th>
        <th>Nombre Paciente</th>
        <th>ID Historial</th>
        <th>Info Historial</th>
    </tr>

<% if(prf != null){ 
    for(Patientrecords p : patientrecords){
    patients = pf.findPatientbyId(p.getPatientid()); 
    records = rf.findRecordbyId(p.getRecordid()); %>
    <tr>
        <td><%= p.getId() %> </td>
        <td><%= patients.get(0).getId() %></td>
        <td><%= patients.get(0).getDni() %></td>
        <td><%= patients.get(0).getName() %></td>
        <td><%= records.get(0).getId() %></td>
        <td><%= records.get(0).getInfo() %></td>
    </tr>
    
    <% } %>   
</table>
<p> Número total de consultas: <%= prf.count() %> </p>

<% }else{ %>
<p>No records available</p>

<% } %>
<%@include file="partials/footer.jsp" %>