<%@page import="Facades.PatientsFacade"%>
<%@page import="Entities.Patients"%>
<%@include file="partials/header.jsp" %>

<h1>Todos los pacientes de la base de datos</h1>

<%  PatientsFacade pf = InitialContext.doLookup("java:global/HistorialMedico/HistorialMedico-ejb/PatientsFacade!Facades.PatientsFacade");
    List <Patients> patients = pf.findAll(); %>

<table>
    <tr>
        <th>ID</th>
        <th>DNI</th>
        <th>Nombre</th>
        <th>Edad</th>
        <th>Opciones</th>
    </tr>
    
<% for(Patients p : patients){ %>
<tr>
    <td><%= p.getId()%></td>
    <td><%= p.getDni()%></td>
    <td><%= p.getName()%></td>
    <td><%= p.getAge()%></td>
    <td>
        <form action="dbUpdatePatient.jsp">
            <input type="hidden" name="dbUpdatePatientId" value="<%=p.getId()%>">
            <input type="submit" value="Actualizar paciente">
        </form>
    </td>
    <td>
        <form action="FrontServlet">
            <input type="hidden" name="command" value="DeletePatientCommand">
            <input type="hidden" name="patientId" value="<%=p.getId()%>">
            <input type="submit" value="Eliminar">
        </form>
    </td>      
</tr>

<% } %>

</table>

<p> Número total de pacientes: <%= pf.count() %> </p>
    
<%@include file="partials/footer.jsp" %>
