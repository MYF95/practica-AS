<%@include file="partials/header.jsp" %>

<h1>Busque un paciente por DNI</h1>
<form action="FrontServlet">
    DNI: <br> 
    <input type="text" name="patientDni"> <br>
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
<p> <%= patientList.printAll() %> </p>

<%@include file="partials/header.jsp" %>
