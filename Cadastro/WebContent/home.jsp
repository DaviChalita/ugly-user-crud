<%@page import="model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<%
			Usuario usuario = (Usuario) request.getAttribute("usuario");
		%>
		<tr>
			<td>Email</td>
			<td><%=usuario.getEmail()%></td>
			<td>Nome</td>
			<td><%=usuario.getNome()%></td>
			<td><a href="EncaminhaAtualizarUsuarioServlet">Atualizar Usuario</a></td>
			<td>
			<form action="DeletarUsuarioServlet" method="post">
			<input type="hidden" name="email" value="<%=usuario.getEmail()%>">
			<input type="submit" value="Deletar Usuario">		
			</form>
			</td>
		</tr>
		<tr>
			<td><a href="MostrarTodosUsuariosServlet">Todos
					usuarios</a>
		</tr>
	</table>
</body>
</html>