package br.ufc.dao;

import java.util.List;

import br.ufc.model.Comentario;

public interface IComentarioDAO {
	
	public void inserir(Comentario comentario);
	public void alterar(Comentario comentario);
	public void apagar(Comentario c);
	public List<Comentario> listar();
	public Comentario getNoticia(Integer id);
	public List<Comentario> listar_Comentarios_Noticia(Integer id_noticia);
}
