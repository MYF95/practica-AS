<%@page import="Facades.RecordsFacade"%>
<%@page import="Entities.Records"%>
<%@include file="partials/header.jsp" %>

<h1>Todas las consultas de la base de datos</h1>

<%  RecordsFacade rf = InitialContext.doLookup("java:global/HistorialMedico/HistorialMedico-ejb/RecordsFacade!Facades.RecordsFacade");
    List <Records> records = rf.showRecords(); %>

<table>
    <tr>
        <th>ID</th>
        <th>DNI</th>
        <th>Info</th>
        <th>Fecha</th>
        <th>Opciones</th>
    </tr>
    
<% for(Records r : records){ %>
<tr>
    <td><%= r.getId()%></td>
    <td><%= r.getDni()%></td>
    <td><%= r.getInfo()%></td>
    <td><%= r.getDate()%></td>
    <td>
        <form action="dbUpdateRecord.jsp">
            <input type="hidden" name="dbUpdateRecordId" value="<%=r.getId()%>">
            <input type="submit" value="Actualizar consulta">
        </form>
    </td>
    <td>
        <form action="FrontServlet">
            <input type="hidden" name="command" value="DeleteRecordCommand">
            <input type="hidden" name="recordId" value="<%=r.getId()%>">
            <input type="submit" value="Eliminar">
        </form>
    </td>      
</tr>

<% } %>

</table>

<p> Número total de consultas: <%= rf.count() %> </p>
    
<%@include file="partials/footer.jsp" %>
