package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class UsuariosDAO {
	static List<Usuario> usuarios = new ArrayList<Usuario>();

	public static boolean autentica(Usuario usuario, Connection con) {
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select email, nomeusuario, senha from usuario where email='"
					+ usuario.getEmail() + "' and senha='" + usuario.getSenha() + "'");
			if (rs.next()) {
				String nome = rs.getString(2);
				usuario.setNome(nome);
				con.close();
				return true;
			} else {
				con.close();
				return false;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void salvar(Usuario usuario, Connection con) throws SQLException {
		try {
			PreparedStatement st = con.prepareStatement("insert into usuario values(?,?,?)");
			st.setString(1, usuario.getEmail());
			st.setString(2, usuario.getNome());
			st.setString(3, usuario.getSenha());
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void atualizar(Usuario usuario, Connection con) throws SQLException {
		try {
			PreparedStatement st = con.prepareStatement("update usuario set nomeusuario=?, senha=? where email=?");
			st.setString(1, usuario.getNome());
			st.setString(2, usuario.getSenha());
			st.setString(3, usuario.getEmail());
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deletar(String email, Connection con) throws SQLException {
		try {
			System.out.println(email);
			PreparedStatement st = con.prepareStatement("delete from usuario where email=?");
			st.setString(1, email);
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Usuario> getAll(Connection con) throws SQLException {
		try {
			usuarios.clear();
			PreparedStatement st = con.prepareStatement("select * from usuario");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setEmail(rs.getString(1));
				usuario.setNome(rs.getString(2));
				usuario.setSenha(rs.getString(3));
				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.close();
		return usuarios;
	}

}
