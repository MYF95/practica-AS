<%@include file="../partials/header.jsp" %>

<h1>Aqu� est�n las consultas de esta sesi�n: </h1>

<% myRecordList myList = (myRecordList)session.getAttribute("list"); %>

<p> <%= myList.printAll() %> </p>

<%@include file="../partials/footer.jsp" %>

