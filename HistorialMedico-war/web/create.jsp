<%@include file="partials/header.jsp" %>

<h1>Rellene el formulario para crear la nueva consulta</h1>

<form action="FrontServlet">
    DNI: <br>
    <input type="text" name="dni"> <br>
    Detalles de la consulta: <br>
    <textarea name="info"></textarea> <br>
    Fecha: <br>
    <input type="date" name="date"> <br>
    <input type="hidden" name="command" value="CreateCommand">
    <input type="submit">
</form>

<% 
    myRecordList myList = (myRecordList)session.getAttribute("list");
    MedicalRecord record = (MedicalRecord)session.getAttribute("record");
%>

<p> La última consulta creada tiene como DNI: </p>
<p> <%= record.getDni() %> </p>   

<p> Tus historiales de la sesión: </p>
<p> <%= myList.printAll() %> </p>

<%@include file="partials/footer.jsp" %>