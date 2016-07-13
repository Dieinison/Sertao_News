package br.ufc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.ufc.model.Comentario;

@Repository
public class ComentarioDAOHibernate implements IComentarioDAO{

	@PersistenceContext
	EntityManager manager;
	
	@Override
	public void inserir(Comentario comentario) {
		this.manager.persist(comentario);		
	}

	@Override
	public void alterar(Comentario comentario) {
		this.manager.merge(comentario);
	}

	@Override
	public void apagar(Comentario c) {
		Comentario comentario = this.manager.find(Comentario.class, c.getId_comentario());
		this.manager.remove(comentario);
	}

	@Override
	public Comentario getNoticia(Integer id) {
		return this.manager.find(Comentario.class, id);
	}

	@Override
	public List<Comentario> listar_Comentarios_Noticia(Integer id_noticia) {
		String hql = " SELECT * FROM COMENTARIO c WHERE c.id_noticia = :id_noticia";  

		TypedQuery<Comentario> query = this.manager.createQuery(hql, Comentario.class);
		query.setParameter("id_noticia", id_noticia).getResultList();
		List<Comentario> comentarios = query.getResultList();

		return comentarios;
	}
	
	public List<Comentario> listar(){
		String hql = "select c from COMENTARIO c";
		List<Comentario> comentarios = this.manager.createQuery(hql, Comentario.class).getResultList();

		return comentarios;
	}
	
}
