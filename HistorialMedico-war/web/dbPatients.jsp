<%@page import="Facades.PatientsFacade"%>
<%@page import="Entities.Patients"%>
<%@include file="partials/header.jsp" %>

<h1>Todos los pacientes de la base de datos</h1>

<%  PatientsFacade pf = InitialContext.doLookup("java:global/HistorialMedico/HistorialMedico-ejb/PatientsFacade!Facades.PatientsFacade");
    int index = (Integer) request.getAttribute("index");
    List <Patients> patients = (List<Patients>) request.getAttribute("list");
    int max = pf.count(); %>

<table>
    <tr>
        <th>ID</th>
        <th>DNI</th>
        <th>Nombre</th>
        <th>Edad</th>
        <th>Opciones</th>
    </tr>
    
<% for(Patients p : patients){ %>
<tr>
    <td><%= p.getId()%></td>
    <td><%= p.getDni()%></td>
    <td><%= p.getName()%></td>
    <td><%= p.getAge()%></td>
    <td>
        <form action="dbUpdatePatient.jsp">
            <input type="hidden" name="dbUpdatePatientId" value="<%=p.getId()%>">
            <input type="submit" value="Actualizar paciente">
        </form>
    </td>
    <td>
        <form action="FrontServlet">
            <input type="hidden" name="command" value="DeletePatientCommand">
            <input type="hidden" name="patientId" value="<%=p.getId()%>">
            <input type="submit" value="Eliminar">
        </form>
    </td>      
</tr>

<% } %>

</table>

<div style="margin: 0 auto; width: 140px;">
    <a href="/HistorialMedico-war/FrontServlet?command=ShowPatientCommand&index=<%= minus3(index)%>">Atras | </a>
    <a href="/HistorialMedico-war/FrontServlet?command=ShowPatientCommand&index=<%= plus3(index, max)%>">Siguiente</a>
</div>

<p> Número total de pacientes: <%= pf.count() %> </p>

<%! public static int minus3(int index) {
        int newIndex = index - 3;
        if (newIndex < 0) {
            return 0;
        }
        return newIndex;
    }

    public static int plus3(int index, int max) {
        int newIndex = index + 3;
        if (newIndex > (max - 3)) {
            return (max - 3);
        }
        return newIndex;
    } %>
    
<%@include file="partials/footer.jsp" %>
