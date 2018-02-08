<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.ComisionView" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Restaurant</title>
    <link rel="stylesheet" href="estilos.css">
</head>

<body>
<ul>
    <li>Mesas</li>
    <ul>
        <li><a href="">Mesas Disponibles</a></li>
        <li><a href="mesas-ocupadas">Mesas Ocupadas</a></li>
        <li><a href="mesa-abrir">Abrir Mesa</a></li>
        <li><a href="">Reservar Mesa</a></li>
    </ul>
    <li>Comisiones</li>
    <ul>
        <li><a href="comisiones">Obtener comisiones</a></li>
    </ul>
    <li>Platos</li>
    <ul>
        <li><a href="plato-directo-crear">Crear Plato Directo</a></li>
    </ul>
</ul>
<h3>
    Comisiones para la sucursal <%=request.getAttribute("sucursal")%>
</h3>
<form action="comisiones" method="post">
    <table>
        <tr>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Comisión</th>
        </tr>
        <%
            List<ComisionView> comisiones = (List<ComisionView>) request.getAttribute("comisiones");

            for (ComisionView comision : comisiones) {
        %>

        <tr>
            <td>
                <%=comision.getNombre()%>
            </td>
            <td>
                <%=comision.getApellido()%>
            </td>
            <td>
                <%=comision.getComision()%>
            </td>
        </tr>

        <%
            }
        %>
    </table>
</form>
</body>
</html>