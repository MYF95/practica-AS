<%@include file="partials/header.jsp" %>

<h1>Aqu� est�n todos los usuarios del servidor: </h1>

<% UserList userList = (UserList)session.getAttribute("userList"); %>

<p> <%= userList.printAll() %> </p>

<%@include file="partials/footer.jsp" %>
