<%@include file="../partials/header.jsp" %>

<h1>Aquí están las consultas de esta sesión: </h1>

<% myRecordList myList = (myRecordList)session.getAttribute("list"); %>

<p> <%= myList.printAll() %> </p>

<%@include file="../partials/footer.jsp" %>

