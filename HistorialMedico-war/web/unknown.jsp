<%@include file="partials/header.jsp" %>

<h1>Oops, ha ocurrido un error.</h1>
<p>C�mo has llegado aqu�?</p>
<% MedicalRecord record = (MedicalRecord)session.getAttribute("record"); %>
<p>Esto es: <%= record.getInfo() %>

<%@include file="partials/header.jsp" %>
