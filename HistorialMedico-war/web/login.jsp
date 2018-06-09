<%@include file="partials/header.jsp" %>


<h1>Login</h1>
<% if(session.getAttribute("logged") == "true"){ %>
<h1>Ya est�s logueado!</h1>
<% } else { %>

<h2>Login a la sesi�n</h2>
<form action="FrontServlet">
    DNI de la sesi�n: <br>
    <input type="text" name="dni"> <br>
    Inserte el nombre: <br>
    <input type="text" name="nombre"> <br>
    <input type="hidden" name="command" value="LoginCommand">
    <input type="submit" value="Login">
</form>

<h2>Login a la BD</h2>
<form action="FrontServlet">
    DNI: <br>
    <input type="text" name="dni"> <br>
    Inserte el nombre: <br>
    <input type="text" name="nombre"> <br>
    <input type="hidden" name="command" value="DBLoginCommand">
    <input type="submit" value="Login">
</form>
<% } %>
<%@include file="/partials/footer.jsp" %>