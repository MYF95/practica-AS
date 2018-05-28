<%@include file="partials/header.jsp" %>

<h1>Logs del sistema</h1>

<p><%= Log.readLog() %>
        
<%@include file="partials/footer.jsp" %>
