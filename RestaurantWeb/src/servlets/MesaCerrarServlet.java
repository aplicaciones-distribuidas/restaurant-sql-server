package servlets;

import business_delegate.BusinessDelegate;
import excepciones.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MesaCerrarServlet", urlPatterns = { "/restaurant/mesa-cerrar" })
public class MesaCerrarServlet extends HttpServlet {
	private static final long serialVersionUID = 6773794400153768111L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long idMesaOcupacion = Long.parseLong(request.getParameter("mesa_ocupacion_id"));
		Long idFormaDePago = Long.parseLong(request.getParameter("forma_de_pago_id"));

		try {
			BusinessDelegate.getInstancia().cerrarMesa(idMesaOcupacion, idFormaDePago);
			response.sendRedirect("mesas-ocupadas");
		} catch (BaseDeDatosException | ConexionException | MesaOcupacionNoExisteException
				| FormaDePagoNoExisteException e) {
			response.sendError(500, e.getMessage());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
