package br.ufc.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufc.dao.IClassificadoDAO;
import br.ufc.dao.IPapelDAO;
import br.ufc.dao.IUsuarioDAO;
import br.ufc.model.Classificado;
import br.ufc.model.Usuario;

@Transactional
@Controller
public class ClassificadoController {

	@Autowired
	@Qualifier("classificadoDAOHibernate")
	private IClassificadoDAO classificadoDAO;
	
	@Autowired
	@Qualifier("usuarioDAOHibernate")
	private IUsuarioDAO usuarioDAO;
	
	@Autowired
	@Qualifier("papelDAOHibernate")
	private IPapelDAO papelDAO;
	
	@RequestMapping("/classificadoFormulario")
	public String classificadoFormulario(Model model, HttpSession session){
		
		if(session.getAttribute("usuario") == null){
			return "404";
		}
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if(usuario.getId_papel() != 3)
			return "404";
		
    	model.addAttribute("papeis", papelDAO.listar());
    	model.addAttribute("classificados", classificadoDAO.listarClassificados());
		
		
		return "classificado/classificadoFormulario";
	}
	
	@RequestMapping("/inserirClassificado")
	public String inserirClassificado(Classificado classificado, HttpSession session){
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		// Caso em que o editor tentar adicionar um classificado que já existe
		for(Classificado cl: classificadoDAO.listarClassificados()){
			if(cl.equals(classificado)){
				return "redirect:classificadoFormulario";
			}
		}
		
		Timestamp data = new Timestamp(System.currentTimeMillis());
		classificado.setData_oferta(data);
		float oferta = 0;
		classificado.setMelhor_oferta(oferta);
		
		// o id do autor é o cara que esta cadastrando
		classificado.setAutor(usuarioDAO.recuperarUsuarioLogin(usuario.getLogin()));
		
		this.classificadoDAO.inserirClassificado(classificado);
		
		return "redirect:classificadoFormulario";
	}
	
	@RequestMapping("/listarClassificado")
	public String listarClassificado(Model model, HttpSession session){
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		model.addAttribute("classificados",classificadoDAO.listarClassificados());
		model.addAttribute("usuario", usuario);
		
		return "listarClafissicado";
	}
	
	@RequestMapping("/exibir_classificado")
    public String exibir_classificado(Model model, @RequestParam(value = "id_classificado") Integer id_classificado){
   
    	fazer_oferta(model, classificadoDAO.recuperarClassificadoId(id_classificado));
    	model.addAttribute("classificadoResult", classificadoDAO.recuperarClassificadoId(id_classificado));
    	
    	return "/classificado/exibir_classificado";
    }
	
	@RequestMapping("/classificado/oferta")
    public String fazer_oferta(Model model, Classificado classificado){
    	model.addAttribute("classificado", classificado);
    	return "/classificado";
    }
	
	@RequestMapping("realizarCompra")
    public String realizarCompra(Model model, Float oferta, Classificado classificado, HttpSession session){
    	
    	Usuario usuario = (Usuario) session.getAttribute("usuario");
    	
    	Classificado cas = classificadoDAO.recuperarClassificadoId(classificado.getId_classificado());
    	
    	
    	if (usuario != null && usuario.getId_papel() == 1) {

			   if (oferta > cas.getPreco() && oferta > cas.getMelhor_oferta() ) {
				   cas.setData_oferta(new Timestamp(System.currentTimeMillis()));
				   cas.setMelhor_oferta(oferta);
				   cas.setAutor(usuarioDAO.recuperarUsuarioLogin(usuario.getLogin()));
				   
				   return "redirect:exibir_classificado?id_classificado="+cas.getId_classificado();
			   }
		}
        
    	
    	return "redirect:exibir_classificado?id_classificado="+cas.getId_classificado();
    }
	
	@RequestMapping("alterarClassificado")
    public String alterarClassificado(Classificado c){
    	this.classificadoDAO.alterarClassificado(c);;
    	return "redirect:listarClassificado";
    }
    
    @RequestMapping("excluirClassificado")
    public String excluirClassificado(Classificado c){
    	this.classificadoDAO.removerClassificado(c);
    	return "redirect:listarClassificado";
    }
	
}
