<%@page import="Facades.PatientsFacade"%>
<%@page import="Entities.Patients"%>
<%@include file="partials/header.jsp" %>

<%  PatientsFacade pf = InitialContext.doLookup("java:global/HistorialMedico/HistorialMedico-ejb/PatientsFacade!Facades.PatientsFacade");
    List <Patients> patients = pf.showPatients(); 
    int id = Integer.parseInt(request.getParameter("dbUpdatePatientId")); 
    Patients p = patients.get(id-1); %>

<h1>Modificando el usuario <%= p.getName() %> con ID <%= p.getId() %></h1>

<form action="FrontServlet">
    Nombre del paciente: <br>
    <input type="text" name="patientName" value="<%= p.getName() %> "> <br>
    DNI: <br>
    <input type="text" name="patientDni" value="<%= p.getDni() %>" maxlength="9"> <br>
    Edad: <br>
    <input type="number" name="patientAge"><br>
    <input type="hidden" name="patientId" value="<%= p.getId() %>">
    <input type="hidden" name="command" value="UpdatePatientCommand">
    <input type="submit">   
</form>
    
<%@include file="partials/footer.jsp" %>