<%@include file="partials/header.jsp" %>

<h1>Logout</h1>

<form action="FrontServlet">
    ¿Quieres cerrar sesión?
    <input type="hidden" name="command" value="LoginCommand">
    <input type="submit" value="Si">
    <a href="index.jsp">No</a>
</form>

<% User user = (User)session.getAttribute("user"); %>
<p> Estás conectado como usuario:
<%= user.getName() %></p>   

<%@include file="/partials/footer.jsp" %>
