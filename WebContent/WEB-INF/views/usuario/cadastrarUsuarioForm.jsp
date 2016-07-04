<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="/resources/css/normalize.min.css">
<link rel="stylesheet" href="/resources/css/foundation.min.css">
<link href='/resources/css/foundation-icons.css' rel='stylesheet'
	type='text/css'>

<script src="/resources/js/modernizr.min.js"></script>

<script src="/resources/js/rn.js" charset="utf-8"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sertão News - Cadastre-se</title>
</head>
<body>
	
	<div class="row">
		<form method="post" action="inserirUsuario">

			<div class="large-6 columns">
				<div class="panel">
					<p>Nome:</p>
					<input type="text" name="nome" required>
				</div>
			</div>

			<div class="large-6 columns">
				<div class="panel">
					<p>Login:</p>
					<input type="text" name="login" required>
				</div>
			</div>

			<div class="large-6 columns">
				<div class="panel">
					<p>E-Mail:</p>
					<input type="text" name="email" required>
				</div>
			</div>

			<div class="large-6 columns">
				<div class="panel">
					<p>Senha:</p>
					<input type="password" name="senha" required>
				</div>
			</div>

				<center>
						<input class="success button" type="submit" value="Cadastrar" />
				</center>
			</form>
		</div>
</body>
</html>