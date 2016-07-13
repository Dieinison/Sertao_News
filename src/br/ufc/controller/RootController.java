package br.ufc.controller;

import java.io.IOException;
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
import br.ufc.model.Papel;
import br.ufc.model.Usuario;

@Transactional
@Controller
public class RootController {
	
	@Autowired
	@Qualifier("noticiaDAOHibernate")
	private INoticiaDAO noticiaDAO;
	
	@Autowired
	@Qualifier("secaoDAOHibernate")
	private ISecaoDAO secaoDAO;
	
	@Autowired
	@Qualifier("usuarioDAOHibernate")
	private IUsuarioDAO usuarioDAO;
	
	@Autowired
	@Qualifier("papelDAOHibernate")
	private IPapelDAO papelDAO;
	
	@Autowired
	@Qualifier("classificadoDAOHibernate")
	private IClassificadoDAO classificadoDAO;
	
	private Usuario admin;
	
	@RequestMapping("/")
	public String home(Model model){
		model.addAttribute("noticiasRecentes", noticiaDAO.listarNoticias());
		model.addAttribute("secoes", secaoDAO.listarSecoes());
		model.addAttribute("classificados", classificadoDAO.listarClassificados());
		
		return "home";
	}
	
	@RequestMapping("/usuario/pageadmin")
	public String pageadmin(Model model) {
		
		model.addAttribute("noticiasRecentes", noticiaDAO.listarNoticias());
		model.addAttribute("secoes", secaoDAO.listarSecoes());
		
		return "/usuario/pageadmin";
	}
	
	@RequestMapping("/cadastrarJornalistaForm")
	 public String cadastrarJornalistaForm(HttpSession session){
		
		if(session.getAttribute("usuario") == null){
			return "404";
		}
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if(usuario.getId_papel() != 3)
			return "404";
		
		admin = (Usuario) session.getAttribute("usuario");
		 return "/usuario/cadastrarJornalistaForm"; 
	 }
	 
	 @RequestMapping("inserirJornalista")
	 public String adicionarJornalista(Usuario usuario, HttpSession session, Model model) throws IOException{
		 
		 // Criando instância da classe para criptografar a senha do usuario
		 Criptografia crip = new Criptografia();
		 
		 // Pegando senha passada do usuário para criptografar
		 String senha = usuario.getSenha();
		 String senha_criptografada = crip.criptografar(senha);
		 
		 Usuario u = usuarioDAO.recuperarUsuarioLogin(usuario.getLogin());
		 
		 // Pegando o ID referente ao papel de Jornalista
		 Integer id_ref_jornalista = papelDAO.recuperarPapelNome("Jornalista");
		 
		 // Caso em que o Usuário está no banco, atualiza e seta um novo papel
		  if(u != null){ 
			  
			  	 Papel papelUsuario = papelDAO.recuperarPapelId(id_ref_jornalista); 
				 List<Papel> papeis = papelDAO.listarPapeisUsuario(u.getId_usuario());
				 papeis.add(papelUsuario);
				 
				 usuario.setId_usuario(u.getId_usuario());
				 usuario.setId_papel(id_ref_jornalista);
			 	 usuario.setPapeis(papeis);
			 	 
				 // Alterando a senha do usuario
				 usuario.setSenha(senha_criptografada);
				 
			 	 this.usuarioDAO.alterar(usuario);
			 	 
			 	 session.setAttribute("usuario", admin);
			 	 
			 	 return "redirect:cadastrarJornalistaForm";   

		  }else{	 // Caso em que o Usuário não está no banco, atualiza os papéis do usuário e insere o mesmo
	
 		         Papel papel = papelDAO.recuperarPapelId(id_ref_jornalista); 
		         List<Papel> papeis = papelDAO.listarPapeisUsuario(usuario.getId_usuario());
		         papeis.add(papel);
		         
		         usuario.setId_papel(id_ref_jornalista);;
	 	         usuario.setPapeis(papeis);
	 	         
		 
	 			 // Criptografando a senha do usuario
	 			 usuario.setSenha(senha_criptografada);
	 			 
		         this.usuarioDAO.inserir(usuario); 
		         
		         session.setAttribute("usuario", admin);
		
		         return "redirect:cadastrarJornalistaForm";  
	       }
	 }
	
}
