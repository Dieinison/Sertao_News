<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
<script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>
<script>
$(function() {
    $( "#calendario" ).datepicker({
        showOn: "button",
        buttonImage: "calendario.png",
        buttonImageOnly: true
    });
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Sertão News - Cadastrar Noticia </title>
</head>
<body>

	<h2 align="center">Nova notícia</h2>
	
	<!-- O nosso form, ao ser submetido, chama uma ação, um método dentro de um @Controller que responde pela
	URL cadastrarNoticia. Esse método precisa receber os dados da requisição e gravar o que o usuário informou na tela. -->
	<form action="inserirNoticia" enctype="multipart/form-data" method="POST" >
    	<table align="center">
       		<tr>
       			<td align="left">Titulo: </td>
       			<td><input type="text" name="titulo"  /> </td> 
       		</tr>
       		
       		<tr>
       			<td align="left">Subtitulo:</td>
       			<td><input type="text" name="subtitulo" /> </td>
       			</tr>
       		
       		<tr>
       			<td align="left">Texto:</td> 
       			<td><textarea rows="5" name="texto"></textarea></td>
       		</tr>
       		
       		<tr>
       		<td align="left">Seção:</td>
       			<td>
       				<select name="id_secao">
						<c:forEach var="secao" items="${secoes}">
						<option value="${secao.id_secao}">${secao.titulo}</option>
						</c:forEach>
					</select>
       			</td>	
			</tr>
       		       		
       		<tr>
       			<td>Data: </td>
       			<td><input type="text" id="calendario" name="data_noticia"/></td>
       		</tr>
       		
       		<tr>
       			<td align="left"> Escolha uma imagem: </td>
       			<td> <input type="file" name="image" size="30" /> </td> 
       		</tr>
       		       		
       		<tr>
       		<td><input type="submit" value="Cadastrar Noticia" /> </td> 
       		</tr>
       		
		</table>
	</form>
	
</body>
</html>