package br.ufc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
	public Usuario recuperarUsuarioId(int id) {
		return manager.find(Usuario.class, id);
	}

	@Override
	public void apagar(Usuario usuario) {
		Usuario u = this.manager.find(Usuario.class, usuario.getId_usuario());;
		manager.remove(u);
	}

	@Override
	public Usuario recuperarUsuarioLogin(String login) {
		String sql = "select u from USUARIO u where u.login = :login";
		TypedQuery<Usuario> query = this.manager.createQuery(sql,Usuario.class);
		query.setParameter("login", login).getResultList();
		List<Usuario> user = query.getResultList();
		if(user.size() == 0){
			return null;
		}else{
			return user.get(0); 
		}
	} 
	
	
}
