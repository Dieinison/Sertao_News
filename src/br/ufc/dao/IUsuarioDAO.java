package br.ufc.dao;

import java.util.List;

import br.ufc.model.Usuario;

public interface IUsuarioDAO {
	
	public void inserir(Usuario usuario);
	public void alterar(Usuario usuario);
	public void apagar(Usuario usuario);
	public List<Usuario> listar();
	public Usuario recuperarUsuarioId(int id);
	public Usuario recuperarUsuarioLogin(String login);

}
