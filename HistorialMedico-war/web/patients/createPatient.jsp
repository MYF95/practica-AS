<%@include file="../partials/header.jsp" %>

<h1>Rellene el formulario para crear el nuevo usuario</h1>

<form action="FrontServlet">
    Nombre del paciente: <br>
    <input type="text" name="patientName"> <br>
    DNI: <br>
    <input type="text" name="patientDni"> <br>
    Edad: <br>
    <input type="number" name="patientAge"> <br>
    <input type="hidden" name="command" value="CreatePatientCommand">
    <input type="submit">
</form>

<% 
    MyPatientList myPatientList = (MyPatientList)session.getAttribute("myPatientList");
    Patient patient = (Patient)session.getAttribute("patient");
%>
<p>El último paciente tiene como DNI: </p>
<p> <%= patient.getDni() %> </p>
<p>Todos los historiales: </p>
<p> <%= myPatientList.printAll() %> </p>

<%@include file="../partials/footer.jsp" %>
