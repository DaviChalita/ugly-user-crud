
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conector.Conector;
import model.dao.UsuariosDAO;

/**
 * Servlet implementation class DeletarUsuarioServlet
 */
@WebServlet("/DeletarUsuarioServlet")
public class DeletarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeletarUsuarioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		try {
			String email = request.getParameter("email");
			con = Conector.getConnection();
			UsuariosDAO.deletar(email, con);
			RequestDispatcher rd = request.getRequestDispatcher("MostrarTodosUsuariosServlet");
			rd.forward(request, response);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
		}
	}

}
