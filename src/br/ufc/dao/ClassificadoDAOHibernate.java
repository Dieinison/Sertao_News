package br.ufc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.ufc.model.Classificado;

@Repository
public class ClassificadoDAOHibernate implements IClassificadoDAO{

	@PersistenceContext
	EntityManager manager;
	
	@Override
	public void inserirClassificado(Classificado classificado) {
		this.manager.persist(classificado);
	}

	@Override
	public void alterarClassificado(Classificado classificado) {
		this.manager.merge(classificado);
	}

	@Override
	public void removerClassificado(Classificado classificado) {
		Classificado c = this.manager.find(Classificado.class, classificado.getId_classificado());
		this.manager.remove(c);
	}

	@Override
	public List<Classificado> listarClassificados() {
		String hql = "SELECT l FROM CLASSIFICADO l";
		List<Classificado> classificados = this.manager.createQuery(hql, Classificado.class).getResultList();
		
		return classificados;
	}

	@Override
	public Classificado recuperarClassificadoId(Integer id_classificado) {
		String hql = "SELECT l FROM CLASSIFICADO l WHERE l.id_classificado = :id_classificado";
		
		TypedQuery<Classificado> query = this.manager.createQuery(hql, Classificado.class);
		query.setParameter("id_classificado", id_classificado).getResultList();
		List<Classificado> classificados = query.getResultList();
		Classificado c = classificados.get(0);
		
		return c;
	}

}
