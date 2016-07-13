package br.ufc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.dao.IComentarioDAO;
import br.ufc.dao.INoticiaDAO;
import br.ufc.dao.IUsuarioDAO;
import br.ufc.model.Comentario;
import br.ufc.model.Usuario;

@Transactional
@Controller
public class ComentarioController {

	@Autowired
	@Qualifier("usuarioDAOHibernate")
	private IUsuarioDAO usuarioDAO;
	
	@Autowired
	@Qualifier("comentarioDAOHibernate")
	private IComentarioDAO comentarioDAO;

	@Autowired
	@Qualifier("noticiaDAOHibernate")
	private INoticiaDAO noticiaDAO;

	@RequestMapping("/inserirComentario")
	public String inserirComentario(Comentario comentario, HttpSession session){
		
		
		Usuario user = (Usuario) session.getAttribute("usuario");
		comentario.setAutor(usuarioDAO.recuperarUsuarioLogin((user.getLogin())));
		comentario.setNotica(noticiaDAO.getNoticia(comentario.getId_noticia()));
		
		comentarioDAO.inserir(comentario);
		
		return "redirect:exibir_noticia?id_noticia="+noticiaDAO.getNoticia(comentario.getId_noticia()).getId_noticia();
	}

	@RequestMapping("/comentario/listar")
	public String listarUsuario(Model model){
		model.addAttribute("comentarios", comentarioDAO.listar());		
		return "comentario/listar_comentarios";
	}
}
