package br.ufc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.dao.IComentarioDAO;
import br.ufc.dao.INoticiaDAO;
import br.ufc.dao.ISecaoDAO;
import br.ufc.dao.IUsuarioDAO;
import br.ufc.model.Noticia;
import br.ufc.model.Secao;
import br.ufc.model.Usuario;
import br.ufc.util.FileUtil;

@Transactional
@Controller
public class NoticiaController {

	@Autowired/* Isso indica ao Spring que ele precisa resolver e injetar a dependência. */
	@Qualifier("noticiaDAOHibernate") /* Qualificando a dependência para o Sping saber qual implementação utilizar*/
	private INoticiaDAO noticiaDAO;
	
	@Autowired
	@Qualifier("secaoDAOHibernate")
	private ISecaoDAO secaoDAO;
	
	@Autowired
	@Qualifier("comentarioDAOHibernate")
	private IComentarioDAO comentarioDAO;
	
	@Autowired
	@Qualifier("usuarioDAOHibernate")
	private IUsuarioDAO usuarioDAO;
	
	@Autowired
	private ServletContext servletContext;
	
	/* O parâmetro usuario é o objeto que deverá ser populado 
	 * pelo Spring MVC com os dados que vierem da requisição. */
	@RequestMapping("/inserirNoticia")
	public String inserirNoticia(Noticia noticia,
			@RequestParam(value="image",required=false) MultipartFile image, 
			HttpSession session){
		
		Usuario usuario = (Usuario)session.getAttribute("usuario");
		
		if(image!=null & !image.isEmpty()){
			String path = servletContext.getRealPath("/")+"resources/img/"+noticia.getTitulo()+".png";
			FileUtil.saveFile(path, image);
		}
		
		noticia.setAutor(usuarioDAO.recuperarUsuarioId(usuario.getId_usuario()));
		noticia.setSecao(secaoDAO.getSecao(noticia.getId_secao()));
		
		noticiaDAO.cadastrarNoticia(noticia);
		return "redirect:cadastro_noticia_formulario";
	}
	
	
	@RequestMapping("/cadastro_noticia_formulario")
	public String inserirNoticiaFormulario(Model model){
		/* Uso o model para acessar via Expression Language no JSP */
		model.addAttribute("secoes",secaoDAO.listarSecoes());
		return "noticia/cadastro_noticia_formulario";
	}
	
	@RequestMapping("listar_por_secao")
	public String exibirNoticiaPorSecao(Model model, @RequestParam(value = "id_secao") Integer id_secao){

		List<Noticia> noticias = this.noticiaDAO.listarNoticias();
		List<Noticia> noticiaResult = new ArrayList<Noticia>();

		Secao secao = new Secao();

		for (Noticia noticia : noticias) {
			if(noticia.getId_secao() == id_secao){
				noticiaResult.add(noticia);
				secao = secaoDAO.getSecao(id_secao);
			}
		}

		model.addAttribute("secao",secao);
		model.addAttribute("secoes",secaoDAO.listarSecoes());
		model.addAttribute("noticias", noticiaResult);

		return "noticia/listar_por_secao";
	}
	
	@RequestMapping("exibir_noticia")
		public String exibirNoticia(Model model, @RequestParam(value="id_noticia") Integer id_noticia){

		comentar(model, noticiaDAO.getNoticia(id_noticia));

		model.addAttribute("noticiaResult", noticiaDAO.getNoticia(id_noticia));
		model.addAttribute("secoes", secaoDAO.listarSecoes());

		if(noticiaDAO.getNoticia(id_noticia).getComentarios().size() > 0){
			model.addAttribute("comentarios", noticiaDAO.getNoticia(id_noticia).getComentarios());
		}

		return "noticia/exibir_noticia";
	}
	
	@RequestMapping("/listar_todas_noticias")
	public String listar_todas_noticias(Model model){
		model.addAttribute("noticias", noticiaDAO.listarNoticias());
		model.addAttribute("secoes",secaoDAO.listarSecoes());
		return "noticia/listar_todas_noticias";
	}
	
	@RequestMapping("cadastrar_comentario_form")
	public String comentar(Model model, Noticia noticia){
		model.addAttribute("noticia", noticia);
		return "comentario";
	}
	
	@RequestMapping("excluirNoticia") 
	public String excluirNoticia(Model model, 
			@RequestParam(value = "id_noticia") Integer id_noticia, HttpSession session){
		
		Noticia noticia = noticiaDAO.getNoticia(id_noticia);
		Usuario user = (Usuario) session.getAttribute("usuario");
		
		if(user.getNome().equals(noticia.getAutor().getNome()) && user.getId_papel() == 2){ // Jornalista
			// para não da remoção cascade
			noticia.setAutor(null);
			noticiaDAO.apagar(noticia);
			return "redirect:listar_todas_noticias";
		}else if(user.getId_papel() == 3){ // Editor
			noticiaDAO.apagar(noticia);;
			return "redirect:listar_todas_noticias";
		}else{
			return "404";
		}

	}
}
