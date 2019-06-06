package control;

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
import model.Usuario;
import model.dao.UsuariosDAO;

/**
 * Servlet implementation class AtualizarUsuarioServlet
 */
@WebServlet("/AtualizarUsuarioServlet")
public class AtualizarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	Usuario usuario;
	RequestDispatcher rd;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AtualizarUsuarioServlet() {
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
			con = Conector.getConnection();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		usuario = new Usuario((request.getParameter("nomeusuario").toString()),
				(request.getParameter("email")).toString(), (request.getParameter("senha")).toString());
		try {
			UsuariosDAO.atualizar(usuario, con);
			con.close();
			request.setAttribute("usuario", usuario);
			rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
