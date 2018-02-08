<%@ page import="dto.EmpleadoView" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<select name="empleado_id" id="empleado_id">
    <%
        List<EmpleadoView> empleados = (List<EmpleadoView>) request.getAttribute("empleados");

        for (EmpleadoView empleado : empleados) {
    %>
    <option value="<%=empleado.getId()%>">
        <%=empleado.getNombre()%> <%=empleado.getApellido()%>
    </option>
    <%
        }
    %>
</select>