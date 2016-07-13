package br.ufc.dao;

import java.util.List;

import br.ufc.model.Secao;

public interface ISecaoDAO {
	
	public void cadastrarSecao(Secao secao);
	public void apagarSecao(Secao s);
	public void alterarSecao(Secao s);
	public Secao getSecao(Integer id);
	public List<Secao> listarSecoes();
	
}
