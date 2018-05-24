<%@include file="../partials/header.jsp" %>

<h1>Aquí están todas las consultas del servidor: </h1>

<% MedicalRecordList medicalRecordList = (MedicalRecordList)session.getAttribute("medicalRecordList"); %>

<p> <%= medicalRecordList.printAll() %> </p>

<%@include file="../partials/footer.jsp" %>
