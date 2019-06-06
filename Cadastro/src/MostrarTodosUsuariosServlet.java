

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conector.Conector;
import model.Usuario;
import model.dao.UsuariosDAO;

/**
 * Servlet implementation class MostrarTodosUsuariosServlet
 */
@WebServlet("/MostrarTodosUsuariosServlet")
public class MostrarTodosUsuariosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection con;   
    List<Usuario> usuarios;
    RequestDispatcher rd;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarTodosUsuariosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		try {
			con = Conector.getConnection();
			usuarios = UsuariosDAO.getAll(con);
			req.setAttribute("listausuarios", usuarios);
			rd = req.getRequestDispatcher("mostrarusuarios.jsp");
	    	rd.forward(req, res);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
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
	}

}
