package br.ufc.dao;

import java.util.List;

import br.ufc.model.Noticia;

public interface INoticiaDAO {
	public void cadastrarNoticia(Noticia noticia);
	public void alterarNoticia(Noticia noticia);
	public void apagar(Noticia n);
	public Noticia getNoticia(Integer id);
	public List<Noticia> listarNoticias();
}
