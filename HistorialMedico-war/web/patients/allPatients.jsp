<%@include file="../partials/header.jsp" %>

<h1>Aquí están todas los pacientes del servidor: </h1>

<% PatientList patientList = (PatientList)session.getAttribute("patientList"); %>

<p> <%= patientList.printAll() %> </p>

<%@include file="../partials/footer.jsp" %>
