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
<title>Sertão News - Cadastrar Jornalista</title>
</head>
<body>
	<h2 align="center"> Cadastrar Jornalista</h2>
	
		<form action="inserirJornalista" method="POST">
    	<table align="center">
       		<tr>
       			<td align="left">Nome do jornalista: </td>
       			<td><input type="text" name="nome"  /> </td> 
       		</tr>
       		
       		<tr>
       			<td align="left">Login:</td>
       			<td><input type="text" name="login" /> </td>
       			</tr>
       		<tr>
    
			<tr>
       			<td align="left">E-mail:</td>
       			<td><input type="text" name="email" /> </td>
       			</tr>
       		<tr>
       		
       		<tr>
       			<td align="left">Senha:</td>
       			<td><input type="password" name="senha" /> </td>
       			</tr>
       		<tr>
		<tr>
       		<td><input type="submit" value="Cadastrar Jornalista" /> </td> 
       		</tr>
       		
		</table>
	</form>
</body>
</html>