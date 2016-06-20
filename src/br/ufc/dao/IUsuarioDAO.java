package br.ufc.dao;

import java.util.List;

import br.ufc.model.Usuario;

public interface IUsuarioDAO {
	public void inserir(Usuario usuario);
	public void alterar(Usuario usuario);
	public List<Usuario> listar();
	public Usuario recuperar(int id);
	public void apagar(int id);
	public Usuario recuperar(String login);
}
