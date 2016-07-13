package br.ufc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.dao.ISecaoDAO;
import br.ufc.model.Secao;
import br.ufc.model.Usuario;

@Transactional
@Controller
public class SecaoController {
	
	@Autowired
	@Qualifier("secaoDAOHibernate")
	private ISecaoDAO secaoDAO;
	
	
	@RequestMapping("/cadastrarSecaoFormulario")
	public String cadastrarSecaoFormulario(HttpSession session){
		
		if(session.getAttribute("usuario") == null){
			return "404";
		}
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if(usuario.getId_papel() != 3)
			return "404";
		
		return "secao/cadastrarSecaoFormulario";
	}
	
	@RequestMapping("/cadastrarSecao")
	public String cadastrarSecao(Secao secao){
		this.secaoDAO.cadastrarSecao(secao);
		return "redirect:cadastrarSecaoFormulario";
	}
}
