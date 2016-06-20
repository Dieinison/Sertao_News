package br.ufc.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.dao.IUsuarioDAO;
import br.ufc.model.Usuario;

@Transactional /* os métodos da classe serão executados dentro de uma transação. */
@Controller
public class UsuarioController {

	@Autowired  /* Isso indica ao Spring que ele precisa resolver e injetar a dependência. */
	@Qualifier("usuarioDAOHibernate") /* Qualificando a dependência para o Sping saber qual implementação utilizar*/
	private IUsuarioDAO uDAO;
	
	@Autowired
	private ServletContext servletContext;
	
	/* O parâmetro usuario é o objeto que deverá ser populado 
	 * pelo Spring MVC com os dados que vierem da requisição. */
	@RequestMapping("/inserirUsuario")
	public String inserirUsuario(Usuario usuario){
		this.uDAO.inserir(usuario);
		return "usuario/usuarioInseridoOk";
	}
	
	@RequestMapping("/inserirUsuarioFormulario")
	public String inserirUsuarioFormulario(){
		return "usuario/inserirUsuarioFormulario";
	}
	
	@RequestMapping("/listarUsuario")
	public String listar(Model model){
		/* O método recebe um objeto que representa o modelo para o JSP */
		
		List<Usuario> usuarios = this.uDAO.listar();
		model.addAttribute("usuarios",usuarios);
		
		/* Dessa forma, será disponibilizado para o JSP um objeto 
		 * chamado alunos que pode ser acessado via 
		 * Expression-Language como ${alunos}*/
	
		return "usuario/listarUsuario";
	}
	
	@RequestMapping("/apagarUsuario")
	public String apagarUsuario(int id){
		this.uDAO.apagar(id);;
		/* Redirecionamento (feito no lado cliente),
		 * para a mesma pagina jsp atualizada. */
		return "redirect:listarUsuario";	
	}
	
	@RequestMapping("/alterarUsuarioFormulario")
	public String alterarAlunoFormulario(int id, Model model){
		
		Usuario u = this.uDAO.recuperar(id);
		model.addAttribute("usuario", u);
		return "aluno/alterarUsuarioFormulario";
	
	}
	
	@RequestMapping("/alterarAluno")
	public String alterarAluno(Usuario usuario){
		
		this.uDAO.alterar(usuario);
		return "redirect:listarAluno";
	}
}
