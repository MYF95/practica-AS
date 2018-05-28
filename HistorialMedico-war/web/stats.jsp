
<%@include file="partials/header.jsp" %>

<h1>Estadísticas del sistema</h1>

<h2>Número de usuarios que se han logueado: <%= Stats.getLoggedUsers() %></h2>

<h2>Páginas visitadas: </h2>

<% for (Map.Entry<String, Integer> entry : Stats.getPageVisits().entrySet()) {%>
        <p><%= entry.getKey()%>: <%= entry.getValue()%></p> 
<% }%>

<h2>Funcionalidades utilizadas: </h2>

<% for (Map.Entry<String, Integer> entry : Stats.getCommandUsage().entrySet()) {%>
        <p><%= entry.getKey()%>: <%= entry.getValue()%></p> 
<% }%>
        
<%@include file="partials/footer.jsp" %>
