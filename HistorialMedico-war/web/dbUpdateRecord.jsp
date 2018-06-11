<%@page import="Facades.RecordsFacade"%>
<%@include file="partials/header.jsp" %>

<%  RecordsFacade rf = InitialContext.doLookup("java:global/HistorialMedico/HistorialMedico-ejb/RecordsFacade!Facades.RecordsFacade");
    List <Records> records = rf.showRecords(); 
    int id = Integer.parseInt(request.getParameter("dbUpdateRecordId")); 
    Records r = records.get(id-1); %>

<h1>Modificando la consulta con DNI <%= r.getDni() %> e ID <%= r.getId() %></h1>

<form action="FrontServlet">
    DNI: <br>
    <input type="text" name="dni" value="<%= r.getDni() %>" maxlength="9"> <br>
    Detalles de la consulta: <br>
    <textarea name="info"><%= r.getInfo() %></textarea> <br>
    Fecha: <br>
    <input type="date" name="date"> <br>
    <input type="hidden" name="recordId" value="<%= r.getId() %>">
    <input type="hidden" name="command" value="UpdateRecordCommand">
    <input type="submit">
</form>

<%@include file="partials/footer.jsp" %>