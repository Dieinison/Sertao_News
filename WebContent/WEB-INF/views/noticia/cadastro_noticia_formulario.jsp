<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Cadastrar Noticia </title>
</head>
<body>

	<h2>Nova notícia</h2>
	
	<!-- O nosso form, ao ser submetido, chama uma ação, um método dentro de um @Controller que responde pela
	URL cadastrarNoticia. Esse método precisa receber os dados da requisição e gravar o que o usuário informou na tela. -->
	<form action="cadastrar_noticia" method="POST">
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
       			<td><input type="text" name="texto" /></td>
       		</tr>
       		
       		<tr>
       			<select name="id_secao">  
                        <c:forEach var="secao" items="${secoes}">
                           <option value= "${secao.id_secao}"> ${secao.titulo} </option> 
                        </c:forEach>
                     </select> 
                      Seção: </td>  
       		</tr>
       		
       		<tr>
       			<td align="left">Id do autor: </td>
       			<td><input type="text" name="id_autor"  /> </td> 
       		</tr>
       		
       		<tr>
       			<td align="left">Data da noticia: </td>
       			<td><input type="text" name="data_noticia"  /> </td> 
       		</tr>
       		
       		<tr>
       		<td><input type="submit" value="Cadastrar" /> </td> 
       		</tr>
		</table>
	</form>

</body>
</html>