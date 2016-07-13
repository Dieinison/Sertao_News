package br.ufc.controller;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.criptografia.Criptografia;
import br.ufc.dao.IPapelDAO;
import br.ufc.dao.IUsuarioDAO;
import br.ufc.model.Papel;
import br.ufc.model.Usuario;

@Transactional /* os métodos da classe serão executados dentro de uma transação. */
@Controller
public class UsuarioController {

	@Autowired  /* Isso indica ao Spring que ele precisa resolver e injetar a dependência. */
	@Qualifier("usuarioDAOHibernate") /* Qualificando a dependência para o Sping saber qual implementação utilizar*/
	private IUsuarioDAO usuarioDAO;
	
	@Autowired
	@Qualifier("papelDAOHibernate")
	private IPapelDAO papelDAO;
	
	
	/* O parâmetro usuario é o objeto que deverá ser populado 
	 * pelo Spring MVC com os dados que vierem da requisição. */
	@RequestMapping("/inserirUsuario")
	public String inserirUsuario(Usuario usuario, HttpSession session){
		
		// Criando instância da criptografia
		Criptografia crip = new Criptografia();
		// Pegar senha passada do usuário para criptografar
		String senha = usuario.getSenha();
		String senha_criptografada = crip.criptografar(senha);
		
		// Pegando o ID referente ao papel de Leitor,
		// todo usuário cadastrado é um leitor.
		Integer id_ref_leitor = papelDAO.recuperarPapelNome("Leitor");
		
		// Testa se o Usuário já está cadastrado..
		if(usuarioDAO.recuperarUsuarioLogin(usuario.getLogin()) != null){ 
			 return "redirect:cadastrarUsuarioForm";
		}
		
		Papel papel = papelDAO.recuperarPapelId(id_ref_leitor);
		List<Papel> papeis = papelDAO.listarPapeisUsuario(usuario.getId_usuario());
		papeis.add(papel);
		
		usuario.setId_papel(id_ref_leitor);
		usuario.setPapeis(papeis);
		// Alterando a senha do usuario para a criptografada
		usuario.setSenha(senha_criptografada);
		
		this.usuarioDAO.inserir(usuario);
		return "redirect:login_formulario";
	}
	
	@RequestMapping("/cadastrarUsuarioForm")
	public String cadastrarUsuarioForm(){
		return "usuario/cadastrarUsuarioForm";
	}
	
}
