<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sertao News - Exibir Noticia</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	
	<h3>${noticiaResult.titulo}</h3>
	<small>Publicada em: ${noticiaResult.data_noticia} por ${noticiaResult.autor.nome} </small>
	<hr>
	<div>
		<img src= "<c:url  value="/resources/img/${noticiaResult.titulo}.png"/>" height="142" width="242"/>
		<p>${noticiaResult.texto}</p>
	</div>
	<hr>
	
	<!-- Só habilita o formulário de comentário se tiver usuário logado -->
	<c:if test="${usuario != null }">
    	<c:forEach var="papel" items="${usuario.papeis}" >		
			<c:if test="${papel.papel == 'Leitor'}">
		   		<c:if test="${usuario.getId_papel() == 1 }">
				<%@ include file="../comentario/cadastrar_comentario_form.jsp" %> 
		 		</c:if>
			</c:if>
		 </c:forEach>
	</c:if>
	
	<h5>
		<strong>Comentários</strong>
	</h5>
	
	<c:forEach var="comentario" items="${comentarios}">
			<p>Usuario: ${comentario.autor.nome}</p>
			<p>${comentario.texto} </p>
		<hr>
	</c:forEach>
</body>
</html>