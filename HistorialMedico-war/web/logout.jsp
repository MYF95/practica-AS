<%@include file="partials/header.jsp" %>

<h1>Logout</h1>

<form action="FrontServlet">
    �Quieres cerrar sesi�n?
    <input type="hidden" name="command" value="LoginCommand">
    <input type="submit" value="Si">
    <a href="index.jsp">No</a>
</form>

<% String user = (String)session.getAttribute("user"); %>
<p> Est�s conectado como usuario:
<%= user %></p>   

<%@include file="/partials/footer.jsp" %>
