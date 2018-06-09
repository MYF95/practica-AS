<%@page import="Facades.RecordsFacade"%>
<%@page import="Entities.Records"%>
<%@include file="partials/header.jsp" %>

<h1>Todas las consultas de la base de datos</h1>

<%  RecordsFacade rf = InitialContext.doLookup("java:global/HistorialMedico/HistorialMedico-ejb/RecordsFacade!Facades.RecordsFacade");
    int index = (Integer) request.getAttribute("indexRecord");
    List <Records> records = (List<Records>) request.getAttribute("records"); 
    int max = rf.count();%>

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

<div style="margin: 0 auto; width: 140px;">
    <a href="/HistorialMedico-war/FrontServlet?command=ShowRecordCommand&indexRecord=<%= minus3(index)%>">Atras | </a>
    <a href="/HistorialMedico-war/FrontServlet?command=ShowRecordCommand&indexRecord=<%= plus3(index, max)%>">Siguiente</a>
</div>

<p> Número total de consultas: <%= rf.count() %> </p>

<%! public static int minus3(int index) {
        int newIndex = index - 3;
        if (newIndex < 0) {
            return 0;
        }
        return newIndex;
    }

    public static int plus3(int index, int max) {
        int newIndex = index + 3;
        if (newIndex > (max - 3)) {
            return (max - 3);
        }
        return newIndex;
    } %>
    
<%@include file="partials/footer.jsp" %>
