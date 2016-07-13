package br.ufc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.ufc.model.Secao;

@Repository
public class SecaoDAOHibernate implements ISecaoDAO {
	
	@PersistenceContext
	EntityManager manager;
	
	@Override
	public void cadastrarSecao(Secao secao) {
		this.manager.persist(secao);
	}

	@Override
	public void alterarSecao(Secao s) {
		this.manager.merge(s);
	}
	
	@Override
	public void apagarSecao(Secao s) {
		Secao secao = this.manager.find(Secao.class, s.getId_secao());
		manager.remove(secao);
	}

	@Override
	public Secao getSecao(Integer id) {
		return this.manager.find(Secao.class, id);
	}

	@Override
	public List<Secao> listarSecoes() {
		String hql = "SELECT s FROM SECAO s";
		return this.manager.createQuery(hql, Secao.class).getResultList();
	}

	

}
