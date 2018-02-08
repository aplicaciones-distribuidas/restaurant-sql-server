<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="dto.SucursalView" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Restaurant</title>
    <link rel="stylesheet" href="estilos.css">
    <script type="application/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
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
<h3>Abrir Mesa</h3>
<form action="mesa-abrir" method="post">
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
            <td>Cantidad de Personas</td>
            <td>
                <input name="cantidad_de_personas" type="text">
            </td>
        </tr>
        <tr>
            <td>Empleado</td>
            <td id="container-empleados">
                <input name="empleado_id" type="text">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="right"><input type="submit" value="Abrir"/></td>
        </tr>
    </table>
</form>

<script>
    $(function () {
        $comboSucursal = $('#sucursal');
        $comboSucursal.change(function () {
            cargarEmpleados($comboSucursal.val());
        });

        console.log($comboSucursal.val());

        cargarEmpleados($comboSucursal.val());
    });

    function cargarEmpleados(sucursal) {
        $.get('empleados', {sucursal: sucursal}, function (data) {
            $('#container-empleados').html(data);
        });
    }

</script>

</body>
</html>