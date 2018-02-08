package servlets;

import business_delegate.BusinessDelegate;
import excepciones.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MesaAgregarProductoServlet", urlPatterns = { "/restaurant/mesa-agregar-producto" })
public class MesaAgregarProductoServlet extends HttpServlet {
	private static final long serialVersionUID = 2147107503097011291L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long idMesaOcupacion = Long.parseLong(request.getParameter("mesa_ocupacion_id"));
		Long idProducto = Long.parseLong(request.getParameter("producto_id"));
		int cantidadProducto = Integer.parseInt(request.getParameter("cantidad_producto"));

		try {
			BusinessDelegate.getInstancia().agregarProductoAMesa(idMesaOcupacion, idProducto, cantidadProducto);
			response.sendRedirect("mesas-ocupadas");
		} catch (ProductoNoExisteException | ProductoSinStockException | InsumoNoExisteException | ConexionException
				| MesaOcupacionNoExisteException | BaseDeDatosException e) {
			response.sendError(500, e.getMessage());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
