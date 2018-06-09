<%@page import="Entities.Users"%>
<%@page import="Facades.UsersFacade"%>
<%@page import="Facades.PatientlistFacade"%>
<%@page import="Entities.Patientlist"%>
<%@page import="Facades.RecordsFacade"%>
<%@page import="Facades.PatientsFacade"%>
<%@include file="partials/header.jsp" %>

<h1>Todas las consultas pertenecientes a usuarios en la BD</h1>

<%  PatientlistFacade plf = InitialContext.doLookup("java:global/HistorialMedico/HistorialMedico-ejb/PatientlistFacade!Facades.PatientlistFacade");
    UsersFacade uf = InitialContext.doLookup("java:global/HistorialMedico/HistorialMedico-ejb/UsersFacade!Facades.UsersFacade");
    PatientsFacade pf = InitialContext.doLookup("java:global/HistorialMedico/HistorialMedico-ejb/PatientsFacade!Facades.PatientsFacade");
    List <Patientlist> patientlist = plf.showPatientList((Integer)session.getAttribute("userId"));
    List <Users> user; 
    List <Patients> patients; %>

<table>
    <tr>
        <th>ID</th>
        <th>ID Usuario</th>
        <th>DNI Paciente</th>
        <th>Nombre Paciente</th>
    </tr>

<% if(plf != null){ 
    for(Patientlist p : patientlist){
    patients = pf.findPatientbyId(p.getPatientid()); 
    user = uf.findUserbyId(p.getUserid());%>
    <tr>
        <td><%= p.getId() %> </td>
        <td><%= user.get(0).getName() %></td>
        <td><%= patients.get(0).getDni() %></td>
        <td><%= patients.get(0).getName() %></td>
    </tr>
    
    <% } %>   
</table>
<p> Número total de pacientes del doctor: <%= patientlist.size() %> </p>

<% }else{ %>
<p>No records available</p>
<% } %>
<%@include file="partials/footer.jsp" %>