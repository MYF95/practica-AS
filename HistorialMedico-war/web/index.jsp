<%-- 
    Document   : index
    Created on : 22-may-2018, 17:06:29
    Author     : myf19
--%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@include file="partials/header.jsp" %>

<% User user = (User)session.getAttribute("user"); %>

<% if(session.getAttribute("logged") == "true"){%>
<h1>Bievenido a la página usuario <%= user.getName() %></h1>
<% } %>

<h1>Consulta médica</h1>
<p>Esta es la página principal de las consultas médicas, decida a donde 
    quiere ir elija desde el navegador a donde quiere ir</p>
        
<%@include file="partials/footer.jsp" %>