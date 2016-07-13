<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Exibir Classificados</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	
	<h3 align="center">${classificadoResult.titulo}</h3>
	<hr>
	<div>
		<h4>${classificadoResult.texto}</h4>
	</div>
	<hr>
	<p>Preço inicial: R$  		 ${classificadoResult.preco}</p>
	<p>Telefone de contato:		 ${classificadoResult.telefone}</p>
	<p>Melhor oferta: R$  		 ${classificadoResult.melhor_oferta}</p>
	<p>Data da melhor oferta: ${classificadoResult.data_oferta}</p>
	<p>Autor da melhor oferta:${classificadoResult.autor.nome}</p>
	
	<!-- Só habilita o formulário de comentário se tiver usuário logado -->
	<c:if test="${usuario != null }">
    	<c:forEach var="papel" items="${usuario.papeis}" >		
			<c:if test="${papel.papel == 'Leitor'}">
		   		<c:if test="${usuario.getId_papel() == 1 }">
				<%@  include file="../classificado/oferta.jsp"%> 
		 		</c:if>
			</c:if>
		 </c:forEach>
	</c:if>
	
</body>
</html>