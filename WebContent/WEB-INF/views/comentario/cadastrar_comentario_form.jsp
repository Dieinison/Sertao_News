<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	
	<form method="post" action="inserirComentario">
		<strong>Comentário: </strong><br/>
		<textarea rows="4" name="texto"></textarea><br/>
		<input type="hidden" name="id_noticia" value="${noticia.id_noticia}"><br/>
		<input class="success button" type="submit" value="Comentar" /><br/>
	</form>
	
</body>
</html>