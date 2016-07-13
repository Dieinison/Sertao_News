package br.ufc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.ufc.model.Papel;

@Repository
public class PapelDAOHibernate implements IPapelDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void inserirPapel(Papel papel){
		this.manager.persist(papel);
	}
	
	public List<Papel> listar(){
		// retorna lista de papeis
		String hql = "SELECT r FROM PAPEL r";
		List<Papel> papeis = this.manager.createQuery(hql, Papel.class).getResultList();
		return papeis;
	}
	
	public Papel recuperarPapelId(Integer id){
		return this.manager.find(Papel.class, id);
	}
	
	public Integer recuperarPapelNome(String papel){
		
		String hql = " SELECT r FROM PAPEL r WHERE r.papel = :papel"; // 
		
		TypedQuery<Papel> query = this.manager.createQuery(hql, Papel.class);
		query.setParameter("papel", papel).getResultList();
		List<Papel> papeisUsuario = query.getResultList();
		Papel p = papeisUsuario.get(0);
		
		return p.getId_papel();	
	
	}
	
	public List<Papel> listarPapeisUsuario(Integer id_usuario){ 
		// Em hql utiliza-se da entidade ou seja da classe da aplicação e não da tabela do banco
		// Uma consulta usando JOIN FETCH nesse caso pega todos os papeis do usuário passado. 
		String hql = " SELECT r FROM PAPEL r JOIN FETCH r.usuarios u WHERE u.id_usuario = :id_usuario"; // 
	
		TypedQuery<Papel> query = this.manager.createQuery(hql, Papel.class);
		query.setParameter("id_usuario", id_usuario).getResultList();
		List<Papel> papeisUsuario = query.getResultList();
		
		return papeisUsuario;
		
	}
}
