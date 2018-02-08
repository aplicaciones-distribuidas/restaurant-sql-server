<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
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
<h3>Crear Plato Directo</h3>
<form action="plato-directo-crear" method="post">
    <table>
        <tr>
            <td>Rubro</td>
            <td>
                <input name="rubro" type="text">
            </td>
        </tr>
        <tr>
            <td>Caducidad</td>
            <td>
                <input name="caducidad" type="text">
            </td>
        </tr>
        <tr>
            <td>Comisión Mozo</td>
            <td>
                <input name="comision_mozo" type="text">
            </td>
        </tr>
        <tr>
            <td>Fecha</td>
            <td>
                <input name="fecha" type="date">
            </td>
        </tr>
        <tr>
            <td>Precio</td>
            <td>
                <input name="precio" type="text">
            </td>
        </tr>
        <tr>
            <td>Área</td>
            <td>
                <select name="area" id="area">
                    <option value="barra">Barra</option>
                    <option value="cafeteria">Cafeteria</option>
                    <option value="cocina">Cocina</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Insumo</td>
            <td>
                <input name="insumo_id" type="text">
            </td>
        </tr>
        <tr>
            <td>Cantidad de Insumo</td>
            <td>
                <input name="cantidad_insumo" type="text">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="right"><input type="submit" value="Crear"/></td>
        </tr>
    </table>
</form>
</body>
</html>