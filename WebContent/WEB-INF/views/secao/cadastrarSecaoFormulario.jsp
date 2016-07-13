<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastrar Secao</title>
</head>
<body>
	<form action="cadastrarSecao" method="post">
		Título:<br /> <input type="text" name="titulo" /> <br />
		Descrição:<br /> <textarea name="descricao" id="mensagem"></textarea><br />
	<input type="submit" value="Cadastrar" />
	</form>
</body>
</html>