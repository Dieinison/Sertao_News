package br.ufc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.ufc.model.Noticia;

@Repository /* Vamos declarando a classe como componente.*/
public class NoticiaDAOHibernate implements INoticiaDAO {

	@PersistenceContext
	EntityManager manager;
	
	@Override
	public void cadastrarNoticia(Noticia noticia) {
		manager.persist(noticia);
	}

	@Override
	public void alterarNoticia(Noticia noticia) {
		this.manager.merge(noticia);
	}

	@Override
	public void apagar(Noticia n) {
		Noticia noticia = this.manager.find(Noticia.class,n.getId_noticia());
		this.manager.remove(noticia);
	}

	@Override
	public Noticia getNoticia(Integer id) {
		return this.manager.find(Noticia.class, id);
	}

	@Override
	public List<Noticia> listarNoticias() {
		String hql = "SELECT n FROM NOTICIA n ORDER BY n.data_noticia DESC";
		List<Noticia> noticias = this.manager.createQuery(hql, Noticia.class).getResultList();
		return noticias;
	}
	
}
