package br.ufc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.ufc.model.Usuario;

@Repository /* Vamos declarando a classe como componente.*/
public class UsuarioDAOHibernate implements IUsuarioDAO{

	@PersistenceContext // Usaremos o atributo para declarar a dependência.
	private EntityManager manager;

	@Override
	public void inserir(Usuario usuario) {
		manager.persist(usuario);
	}

	@Override
	public void alterar(Usuario usuario) {
		manager.merge(usuario);
	}

	@Override
	public List<Usuario> listar() {
		String hql = "SELECT u FROM USUARIO u";
		return manager.createQuery(hql,Usuario.class).getResultList();
	}

	@Override
	public Usuario recuperar(int id) {
		return manager.find(Usuario.class, id);
	}

	@Override
	public void apagar(int id) {
		Usuario u = this.recuperar(id);
		manager.remove(u);
	}

	@Override
	public Usuario recuperar(String login) {
		String hql = "SELECT u FROM USUARIO as u WHERE u.login = :var_login";
		Query query = manager.createQuery(hql);
		List<Usuario> usuarios = query.setParameter("var_login", hql).getResultList();
		
		if(usuarios.size() != 0){
			return usuarios.get(0);
		}
		
		return null;
	} 
	
	
}
