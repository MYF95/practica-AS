<%@include file="partials/header.jsp" %>

<h1>Aqu� est�n los pacientes creados en esta sesi�n: </h1>

<% MyPatientList myList = (MyPatientList)session.getAttribute("myPatientList"); %>

<p> <%= myList.printAll() %> </p>

<%@include file="partials/footer.jsp" %>
