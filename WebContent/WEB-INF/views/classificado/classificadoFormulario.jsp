<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastrar Classificado</title>
</head>
<body>
	<h2 align="center"> Novo Classificado </h2>
	
	<form action="inserirClassificado" method="POST" >
    	<table align="center">
       		<tr>
       			<td align="left">Titulo do classificado: </td>
       			<td><input type="text" name="titulo"  /> </td> 
       		</tr>
       		
       		<tr>
       		<td align="left">Texto do classificado:</td> 
       			<td><textarea rows="5" name="texto"></textarea></td>
       		</tr>
       		
       		<tr>
       			<td align="left">Preço inicial:</td>
       			<td><input type="text" name="preco" /> </td>
       			</tr>
       		<tr>
       		
       		<tr>
       			<td align="left">Telefone para contato:</td>
       			<td><input type="text" name="telefone" /> </td>
       			</tr>
       		<tr>
       		  		
       		<tr>
       		<td><input type="submit" value="Cadastrar Classificado" /> </td> 
       		</tr>
       		
		</table>
	</form>
	
</body>
</html>