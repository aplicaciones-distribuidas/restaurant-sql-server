package servlets;

import business_delegate.BusinessDelegate;
import dto.EmpleadoView;
import excepciones.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmpleadosServlet", urlPatterns = { "/restaurant/empleados" })
public class EmpleadosServlet extends HttpServlet {
	private static final long serialVersionUID = 7855174417238754151L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sucursal = request.getParameter("sucursal");
		try {
			List<EmpleadoView> empleados = BusinessDelegate.getInstancia().getEmpleadosBySucursal(sucursal);
			request.setAttribute("empleados", empleados);
			request.getRequestDispatcher("/empleados-select.jsp").forward(request, response);
		} catch (BaseDeDatosException | ConexionException | SucursalNoExisteException ex) {
			response.sendError(500, ex.getMessage());
		}
	}
}
