<%@include file="partials/header.jsp" %>

<h1>Busque una consulta por DNI</h1>
<form action="FrontServlet">
    DNI: <br> 
    <input type="text" name="dni"> <br>
    <input type="hidden" name="command" value="SearchCommand">
    <input type="submit">
</form>

<% MedicalRecord record = (MedicalRecord)session.getAttribute("record");
   if(session.getAttribute("flag") == "true") { %>
<%= "<p>Consulta encontrada! Aquí tiene los datos de la consulta:</p>" %>
<%= "<p>DNI: " + record.getDni() + "</p>"%>
<%= "<p>Observaciones: " + record.getInfo() + "</p>"%>
<%= "<p>Date: " + record.getDate() + "</p>"%>
<% } session.setAttribute("flag", "false"); %>

<%@include file="partials/footer.jsp" %>
