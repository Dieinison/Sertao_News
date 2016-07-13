<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sertao News - Lista de todas as notícias</title>
</head>
<body>
	
	<h2>Listar todas as notícias</h2>
	<table border="1">
		<c:forEach var="noticia" items="${noticias}">
		<tr>
			
			<td><strong>ID:</strong> ${noticia.id_noticia } </td>
			<td><strong>TÍTULO:</strong> ${noticia.titulo } </td>
			<td><strong>TEXTO:</strong> ${noticia.texto} </td>
			<td><strong>PUBLICADO EM:</strong> ${noticia.data_noticia } </td>
			<td><strong>IMAGEM:</strong> <td> <img src= "<c:url  value="/resources/img/${noticia.titulo}.png"/>" height="40" width="40"/>  </td>
			<td><strong>JORNALISTA:</strong> ${noticia.autor.nome}</td>
			<td><strong><font color="red"> <a href="excluirNoticia?id_noticia=${noticia.id_noticia}"> REMOVER:</a></font></strong></td>
			
		</tr>
		</c:forEach>
	</table>
	

</body>
</html>