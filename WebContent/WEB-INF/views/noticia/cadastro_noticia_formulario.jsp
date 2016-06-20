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

	<form action:"cadastrarNoticia" method="post" enctype="multipart/form-data">
       Titulo: <input type="text" name="titulo" /> <br />
       Subtitulo: <input type="text" name="subtitulo" /> <br />
       Texto: <input type="text" name="texto" /> <br />
       Id da seção: <select name="id_secao">  
                        <c:forEach var="secao" items="${secoes}">
                           <option value= "${secao.id_secao}"> ${secao.titulo} </option> 
                        </c:forEach>
                     </select> 
                      Seção:
       Data da Noticia: <input type="date" name="data" />
       Imagem da Noticia: <input type="file" name="imagem" />
       <input type="submit" value="Cadastrar" /> </td>
	</form>

</body>
</html>