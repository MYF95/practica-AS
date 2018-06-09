<%@page import="Entities.Users"%>
<%@page import="Facades.UsersFacade"%>
<%@include file="partials/header.jsp" %>

<h1>Todas las consultas de la base de datos</h1>

<%  UsersFacade uf = InitialContext.doLookup("java:global/HistorialMedico/HistorialMedico-ejb/UsersFacade!Facades.UsersFacade");
    List <Users> users = uf.showUsers(); %>

<table>
    <tr>
        <th>ID</th>
        <th>DNI</th>
        <th>Nombre</th>
    </tr>
    
<% for(Users u : users){ %>
<tr>
    <td><%= u.getId()%></td>
    <td><%= u.getDni()%></td>
    <td><%= u.getName()%></td>
</tr>

<% } %>

</table>

<p> Número total de consultas: <%= uf.count() %> </p>
    
<%@include file="partials/footer.jsp" %>
