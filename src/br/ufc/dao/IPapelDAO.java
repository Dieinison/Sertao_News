package br.ufc.dao;

import java.util.List;

import br.ufc.model.Papel;

public interface IPapelDAO {
	
	public void inserirPapel(Papel papel);
	public List<Papel> listar();
	public Papel recuperarPapelId(Integer id);
	public Integer recuperarPapelNome(String nome);
	public List<Papel> listarPapeisUsuario(Integer id_usuario);
	
}
