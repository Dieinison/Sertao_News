package br.ufc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter{

@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {

	String uri = request.getRequestURI();
		
		// Essas páginas podem ser visualizadas por todos
		if(uri.endsWith("/") ||
		   uri.endsWith("login") ||
		   uri.endsWith("login_formulario") ||
		   uri.endsWith("cadastrarUsuarioForm") ||
		   uri.contains("exibir_classificado") ||
		   uri.contains("listar_por_secao") ||
		   uri.contains("exibir_noticia"))
			return true;
		
		// Libera, se o usuário logar
		if(request.getSession().getAttribute("usuario") != null){
			return true;
		}

		response.sendRedirect("login_formulario");
		return false;
	}
}
