<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
</head>
<body>
		<form method="post" action="realizarCompra">
			<strong>Inserir Oferta: </strong> <br />
			<input type="text" name="oferta"> <br />
			<input type="hidden" name="id_classificado" value="${classificado.id_classificado}"><br />
			<input class="success button" type="submit" value="Inserir" /><br />
		</form>
</body>
</html>
