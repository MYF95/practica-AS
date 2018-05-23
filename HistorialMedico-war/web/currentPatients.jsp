<%@include file="partials/header.jsp" %>

<h1>Aquí están los pacientes creados en esta sesión: </h1>

<% MyPatientList myList = (MyPatientList)session.getAttribute("myPatientList"); %>

<p> <%= myList.printAll() %> </p>

<%@include file="partials/footer.jsp" %>
