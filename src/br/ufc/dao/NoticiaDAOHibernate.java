package br.ufc.dao;

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
	
}
