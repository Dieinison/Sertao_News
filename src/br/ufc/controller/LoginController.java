package br.ufc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.dao.IPapelDAO;
import br.ufc.dao.IUsuarioDAO;
import br.ufc.model.Usuario;

@Transactional
@Controller
public class LoginController {
	
	
	@Autowired
	@Qualifier("usuarioDAOHibernate")
	private IUsuarioDAO usuarioDAO;
	

	@RequestMapping("/login_formulario")
	public String login_formulario(){
		return "login_formulario";
	}
	
	/* Para logar o usuário na aplicação 
	 * criei uma ação que recebe os dados do formulário de login e a sessão HTTP.*/
	@RequestMapping("/login")
	public String login(HttpSession session ,Usuario usuario){
		Usuario ref = usuarioDAO.recuperarUsuarioLogin(usuario.getLogin());
		
		if(ref != null){ //existe aluno com login passado
			if(ref.getSenha().equals(usuario.getSenha())){ //verifica senha
				// se sim, inicia uma sessão e guarda usuário dentro da mesma
				session.setAttribute("aluno_logado", ref);
				return "home";
			}
		}
		return "redirect:login_formulario";
	}
	
	@RequestMapping("/logOut")
	public String logOut(HttpSession session){
		session.invalidate();
		return "redirect:home";
	}
}
