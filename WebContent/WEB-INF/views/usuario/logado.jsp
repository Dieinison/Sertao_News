<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sertão News</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    
    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
    .row.content {height: 450px}
    
    /* Setar plano de fundo cinza and 100% height */
    .sidenav {
      padding-top: 20px;
      background-color: #f1f1f1;
      height: 100%;
    }
    
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height:auto;}
    }
  </style>
</head>
<body>
	
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Sertão News</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="/Sertao_News/">Home</a></li>
        
        <c:forEach var="secao" items="${secoes}">
			<li> <a href="listar_por_secao?id_secao=${secao.id_secao}" 
			title="${secao.descricao}"> ${secao.titulo} </a> </li>
		</c:forEach> 
            	
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="logOut"><span class="glyphicon glyphicon-log-out"></span> Log out</a></li>
      </ul>
    </div>
  </div>
</nav>

<div class="container-fluid text-center">
  	<div class="row content">
  	
    <div class="col-sm-2 sidenav">
    </div>
    
    <div class="col-sm-8 text-left">
      
      <h1>Bem vindo!</h1>
      <p>Lorem ipsum dolor sit amet.</p>
      <hr>
      <c:forEach var="noticia" items="${noticiasRecentes}">
      	<h4><a href="exibir_noticia?id_noticia=${noticia.id_noticia}"> ${noticia.titulo} </a> </h4>
	    <h6>${noticia.subtitulo} </h6>
	  </c:forEach>
	
	</div>
    
    <div class="col-sm-2 sidenav">
      <c:forEach var="classificado" items="${classificados}">
      	
      	<div class="well">
        	<p> <a href="exibir_classificado?id_classificado=${classificado.id_classificado}"> ${classificado.titulo} </a> </p>
	    	<p>Oferta: </p> 
	    	<p>R$  ${classificado.melhor_oferta} </p>
      	</div>
	  	
	  </c:forEach>
    </div>
  
  </div>
</div>
	
</body>
</html>