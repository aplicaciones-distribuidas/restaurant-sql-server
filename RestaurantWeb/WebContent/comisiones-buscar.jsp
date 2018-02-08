<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="dto.SucursalView" %>
<%@ page import="java.util.List" %>
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
<h3>Comisiones</h3>
<form action="comisiones" method="post">
    <table>
        <tr>
            <td>Sucursal</td>
            <td>
                <select name="sucursal" id="sucursal">
                    <%
                        List<SucursalView> sucursales = (List<SucursalView>) request.getAttribute("sucursales");

                        for (SucursalView sucursal : sucursales) {
                    %>
                    <option value="<%=sucursal.getNombre()%>"><%=sucursal.getNombre()%>
                    </option>
                    <%
                        }
                    %>
                </select>
        </tr>
        <tr>
            <td colspan="2" align="right"><input type="submit" value="Ver"/></td>
        </tr>
    </table>
</form>
</body>
</html>