package servlets;

import business_delegate.BusinessDelegate;
import dto.SucursalView;
import excepciones.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MesaAbrirServlet", urlPatterns = { "/restaurant/mesa-abrir" })
public class MesaAbrirServlet extends HttpServlet {
	private static final long serialVersionUID = 4645938354293793207L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sucursal = request.getParameter("sucursal");
		int cantidadDePersonas = Integer.parseInt(request.getParameter("cantidad_de_personas"));
		Long idEmpleado = Long.parseLong(request.getParameter("empleado_id"));

		try {
			BusinessDelegate.getInstancia().abrirMesa(sucursal, cantidadDePersonas, idEmpleado);
			response.sendRedirect("mesas-ocupadas");
		} catch (BaseDeDatosException | ConexionException | EmpleadoNoExisteException | NoHayMesasDisponiblesException
				| SucursalNoExisteException e) {
			response.sendError(500, e.getMessage());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<SucursalView> sucursales = BusinessDelegate.getInstancia().getSucursales();
			request.setAttribute("sucursales", sucursales);
			request.getRequestDispatcher("/mesa-abrir.jsp").forward(request, response);
		} catch (BaseDeDatosException | ConexionException ex) {
			response.sendError(500, ex.getMessage());
		}
	}
}
