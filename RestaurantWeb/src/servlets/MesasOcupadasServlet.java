package servlets;

import business_delegate.BusinessDelegate;
import dto.FormaPagoView;
import dto.MesaOcupacionView;
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

@WebServlet(name = "MesasOcupadasServlet", urlPatterns = { "/restaurant/mesas-ocupadas" })
public class MesasOcupadasServlet extends HttpServlet {
	private static final long serialVersionUID = -3554610850640430777L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sucursal = request.getParameter("sucursal");
		try {
			List<MesaOcupacionView> mesas = BusinessDelegate.getInstancia().mesasOcupadas(sucursal);
			if (mesas.size() == 0) {
				response.sendError(404, "No hay mesas ocupadas");
				return;
			}
			List<FormaPagoView> formasDePago = BusinessDelegate.getInstancia().getFormasDePago();
			request.setAttribute("mesas", mesas);
			request.setAttribute("formasDePago", formasDePago);
			//request.getRequestDispatcher("/mesas-ocupadas-lista.jsp").forward(request, response);
			//response.setStatus(200);
			getServletContext().getRequestDispatcher("/mesas-ocupadas-lista.jsp").forward(request, response);
		} catch (BaseDeDatosException | SucursalNoExisteException | ConexionException ex) {
			response.sendError(500, ex.getMessage());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<SucursalView> sucursales = BusinessDelegate.getInstancia().getSucursales();
			request.setAttribute("sucursales", sucursales);
			//request.getRequestDispatcher("/mesas-ocupadas-buscar.jsp").forward(request, response);
			getServletContext().getRequestDispatcher("/mesas-ocupadas-buscar.jsp").forward(request, response);
		} catch (BaseDeDatosException | ConexionException ex) {
			response.sendError(500, ex.getMessage());
		}
	}
}
