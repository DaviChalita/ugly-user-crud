

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
 * Servlet implementation class CadastraUsuarioServlet
 */
@WebServlet("/CadastraUsuarioServlet")
public class CadastraUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection con;
    RequestDispatcher rd;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastraUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario usuario = new Usuario(request.getParameter("nomeusuario"), request.getParameter("email"), request.getParameter("senha"));
		System.out.println(usuario.getEmail());
		try {
			con = Conector.getConnection();
			//UsuariosDAO.salvar(usuario, con);	
		} catch (ClassNotFoundException | SQLException e1) {
			System.out.println("Nao conseguiu se conectar");
			e1.printStackTrace();
		}
			rd = request.getRequestDispatcher("logausuario.jsp");
			rd.forward(request, response);
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
