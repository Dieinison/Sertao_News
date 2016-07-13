<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<h2 align="center">Efetuar Login</h2>
	
	<form action="login" method="post">
		<table align="center" border=1 frame="above">
			<tr>
				<td>Login: <input type="text" name="login"/> </td>
			</tr>
			
			<tr>
				<td>Senha: <input type="password" name="senha"/> </td>
			</tr>
			
			<tr>
			<td>Papel:
       				<select name="id_papel" >
						<c:forEach var="papel" items="${papeis}">
						<option value="${papel.id_papel}">${papel.papel}</option>
						</c:forEach>
					</select>
       			</td>	
			</tr>
			
			<tr>
				<td><input type="submit" value="ENVIAR" align="right"/> <br /> </td>
			</tr>
		</table>
	</form>
	
</body>
</html>