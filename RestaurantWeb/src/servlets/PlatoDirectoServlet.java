package servlets;

import business_delegate.BusinessDelegate;
import excepciones.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "PlatoDirectoServlet", urlPatterns = { "/restaurant/plato-directo-crear" })
public class PlatoDirectoServlet extends HttpServlet {
	private static final long serialVersionUID = 481012789114860369L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String rubro = request.getParameter("rubro");
		int caducidad = Integer.parseInt(request.getParameter("caducidad"));
		float comisionMozo = Float.parseFloat(request.getParameter("comision_mozo"));
		Date fecha = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
			fecha = formatter.parse(request.getParameter("fecha"));
		} catch (ParseException e) {
			response.sendError(500, e.getMessage());
		}
		float precio = Float.parseFloat(request.getParameter("precio"));
		String nombreArea = request.getParameter("area");
		Long idInsumo = Long.parseLong(request.getParameter("insumo_id"));
		float cantInsumo = Float.parseFloat(request.getParameter("cantidad_insumo"));

		try {
			BusinessDelegate.getInstancia().crearPlatoDirecto(rubro, caducidad, comisionMozo, fecha, precio, nombreArea,
					idInsumo, cantInsumo);
			response.sendRedirect("plato-directo-crear");
		} catch (BaseDeDatosException | ConexionException | RubroNoExisteException | InsumoNoExisteException
				| AreaNoExisteException e) {
			response.sendError(500, e.getMessage());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/plato-directo-crear.jsp").forward(request, response);
	}
}
