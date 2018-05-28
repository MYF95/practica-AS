
<%@include file="partials/header.jsp" %>

<h1>Estad�sticas del sistema</h1>

<h2>N�mero de usuarios que se han logueado: <%= Stats.getLoggedUsers() %></h2>

<h2>P�ginas visitadas: </h2>

<% for (Map.Entry<String, Integer> entry : Stats.getPageVisits().entrySet()) {%>
        <p><%= entry.getKey()%>: <%= entry.getValue()%></p> 
<% }%>

<h2>Funcionalidades utilizadas: </h2>

<% for (Map.Entry<String, Integer> entry : Stats.getCommandUsage().entrySet()) {%>
        <p><%= entry.getKey()%>: <%= entry.getValue()%></p> 
<% }%>
        
<%@include file="partials/footer.jsp" %>
