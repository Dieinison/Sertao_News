package br.ufc.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.dao.INoticiaDAO;
import br.ufc.model.Noticia;

@Transactional
@Controller
public class NoticiaController {

	@Autowired/* Isso indica ao Spring que ele precisa resolver e injetar a dependência. */
	@Qualifier("noticiaDAOHibernate") /* Qualificando a dependência para o Sping saber qual implementação utilizar*/
	private INoticiaDAO noticiaDAO;
	
	@Autowired
	private ServletContext servlet;
	
	/* O parâmetro usuario é o objeto que deverá ser populado 
	 * pelo Spring MVC com os dados que vierem da requisição. */
	@RequestMapping("/cadastrar_noticia")
	public String cadastrarNoticia(Noticia noticia){
		noticiaDAO.cadastrarNoticia(noticia);
		return "noticia/noticia_cadastrada";
	}
	
	@RequestMapping("/cadastro_noticia_formulario")
	public String inserirNoticiaFormulario(){
		return "noticia/cadastro_noticia_formulario";
	}
	
}
