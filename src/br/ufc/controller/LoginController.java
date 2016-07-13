package br.ufc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.criptografia.Criptografia;
import br.ufc.dao.IClassificadoDAO;
import br.ufc.dao.INoticiaDAO;
import br.ufc.dao.IPapelDAO;
import br.ufc.dao.ISecaoDAO;
import br.ufc.dao.IUsuarioDAO;
import br.ufc.model.Noticia;
import br.ufc.model.Papel;
import br.ufc.model.Secao;
import br.ufc.model.Usuario;

@Transactional
@Controller
public class LoginController {
	
	
	@Autowired
	@Qualifier("usuarioDAOHibernate")
	private IUsuarioDAO usuarioDAO;
	
	@Autowired
	@Qualifier("papelDAOHibernate")
	private IPapelDAO papelDAO;
	
	@Autowired
	@Qualifier("noticiaDAOHibernate")
	private INoticiaDAO noticiaDAO;
	
	@Autowired
	@Qualifier("secaoDAOHibernate")
	private ISecaoDAO secaoDAO;
	
	@Autowired
	@Qualifier("classificadoDAOHibernate")
	private IClassificadoDAO classificadoDAO;
	
	@RequestMapping("/login_formulario")
	public String login_formulario(Model model){
		model.addAttribute("papeis", papelDAO.listar());
		return "login_formulario";
	}
	
	/* Para logar o usuário na aplicação 
	 * criei uma ação que recebe os dados do formulário de login e a sessão HTTP.*/
	@RequestMapping("/login")
	public String login(Usuario usuario, HttpSession session, Model model){
		// Criando instância da classe criptografia para criptografar a senha do usuario
		Criptografia crip = new Criptografia();
		
		// Pegando senha passada para criptografar
		String senha = usuario.getSenha();
		String senha_criptografada = crip.criptografar(senha);
		String login = usuario.getLogin();
		Integer id_papel = usuario.getId_papel();
		
		Usuario ref = usuarioDAO.recuperarUsuarioLogin(login);
		
		if(ref != null){ //existe usuário com login passado
			
			// Lista de papeis do usuario
			List<Papel> papeis_usuario = ref.getPapeis();
			List<Integer> id_papeis = new ArrayList<Integer>();
			
			// Percorrendo lista de papeis para setar os id dos papeis
			for(Papel p: papeis_usuario){
				id_papeis.add(papelDAO.recuperarPapelNome(p.getPapel()));
			}
			
			// Usuário cadastrado pelo sistema
			if(ref.getSenha().equals(senha_criptografada) && id_papeis.contains(id_papel) && id_papel == 1){
				ref.setId_papel(id_papel);
				session.setAttribute("usuario", ref);
				model.addAttribute("usuario", ref);
				
				// Dados
				List<Noticia> noticias = this.noticiaDAO.listarNoticias();
				model.addAttribute("noticiasRecentes", noticias);
				
				List<Secao> secoes = this.secaoDAO.listarSecoes();
				model.addAttribute("secoes", secoes);
				
				model.addAttribute("classificados", classificadoDAO.listarClassificados());
				
				return "/usuario/logado";
			
			} else if(ref.getSenha().equals(senha_criptografada) && id_papeis.contains(id_papel) && id_papel != 1){
				session.setAttribute("usuario", ref);
				model.addAttribute("usuario", ref);
				
				// Dados
				List<Noticia> noticias = this.noticiaDAO.listarNoticias();
				model.addAttribute("noticiasRecentes", noticias);
				
				List<Secao> secoes = this.secaoDAO.listarSecoes();
				model.addAttribute("secoes", secoes);
				
				model.addAttribute("classificados", classificadoDAO.listarClassificados());
				
				return "/usuario/pageadmin";
			
			}	else{
					// usuario cadastrado manualmente
					if(ref.getSenha().equals(senha) && id_papeis.contains(id_papel) && id_papel == 1){
						ref.setId_papel(id_papel);
						session.setAttribute("usuario", usuario);
						model.addAttribute("usuario", usuario);
					
						// Dados
						List<Noticia> noticias = this.noticiaDAO.listarNoticias();
						model.addAttribute("noticiasRecentes", noticias);
					
						List<Secao> secoes = this.secaoDAO.listarSecoes();
						model.addAttribute("secoes", secoes);
						
						model.addAttribute("classificados", classificadoDAO.listarClassificados());
						
						return "/usuario/logado";
					}else if(ref.getSenha().equals(senha) && id_papeis.contains(id_papel) && id_papel != 1){
						session.setAttribute("usuario", ref);
						model.addAttribute("usuario", ref);
					
						// Dados
						List<Noticia> noticias = this.noticiaDAO.listarNoticias();
						model.addAttribute("noticiasRecentes", noticias);
					
						List<Secao> secoes = this.secaoDAO.listarSecoes();
						model.addAttribute("secoes", secoes);
					
						model.addAttribute("classificados", classificadoDAO.listarClassificados());
						
						return "/usuario/pageadmin";
					}else{
						return "redirect:login_formulario";
					}
				}
			}
		return "redirect:login_formulario";
	}
	
	@RequestMapping("/logOut")
	public String logOut(HttpSession session, Model model){
		session.invalidate();
		
		List<Noticia> noticias = noticiaDAO.listarNoticias();
		model.addAttribute("noticias", noticias);
		
		List<Secao> secoes = secaoDAO.listarSecoes();
		model.addAttribute("secoes",secoes);
		
		return "redirect:/";
	}
}
