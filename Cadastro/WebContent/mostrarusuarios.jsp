<%@page import="model.Usuario"%>
<%@page import="java.util.ArrayList"%>
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
			ArrayList<Usuario> usuarios = (ArrayList<Usuario>) request.getAttribute("listausuarios");
			for (Usuario usuario : usuarios) {
		%>
		<tr>
			<td><%=usuario.getEmail()%></td>
			<td><%=usuario.getNome()%></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>