package servlets;

import business_delegate.BusinessDelegate;
import dto.ComisionView;
import dto.SucursalView;
import excepciones.BaseDeDatosException;
import excepciones.ConexionException;
import excepciones.SucursalNoExisteException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ComisionesServlet", urlPatterns = { "/restaurant/comisiones" })
public class ComisionesServlet extends HttpServlet {
	private static final long serialVersionUID = 6664967653268162514L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sucursal = request.getParameter("sucursal");
		try {
			List<ComisionView> comisiones = BusinessDelegate.getInstancia().getComisionesMozos(sucursal);
			request.setAttribute("sucursal", sucursal);
			request.setAttribute("comisiones", comisiones);
			request.getRequestDispatcher("/comisiones-ver.jsp").forward(request, response);
		} catch (ConexionException | BaseDeDatosException | SucursalNoExisteException e) {
			response.sendError(500, e.getMessage());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<SucursalView> sucursales = BusinessDelegate.getInstancia().getSucursales();
			request.setAttribute("sucursales", sucursales);
			request.getRequestDispatcher("/comisiones-buscar.jsp").forward(request, response);
		} catch (BaseDeDatosException | ConexionException ex) {
			response.sendError(500, ex.getMessage());
		}
	}
}
