

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import conector.Conector;
import model.Usuario;
import model.dao.UsuariosDAO;

/**
 * Servlet implementation class ConfereLogin
 */
@WebServlet("/ConfereLogin")
public class ConfereLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Usuario usuario;
    Connection con;
    RequestDispatcher rd;
    HttpSession session;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfereLogin() {
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
		doGet(request, response);
		try {
			con = Conector.getConnection();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		usuario = new Usuario((request.getParameter("email")).toString(), (request.getParameter("senha")).toString());		
			if(UsuariosDAO.autentica(usuario, con)) {	
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("usuario", usuario);
				rd = request.getRequestDispatcher("home.jsp");
		    	rd.forward(request, response);
			}else {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rd = request.getRequestDispatcher("loginfalho.jsp");
				rd.forward(request, response);
			}
		
	}

}
