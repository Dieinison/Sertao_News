package br.ufc.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name="PAPEL")
public class Papel {
	
	@Id
	@Column(name="ID_PAPEL",nullable=false)
	private Integer id_papel;
	
	@Column(name="PAPEL", nullable=false)
	private String papel;
	
	@ManyToMany(mappedBy="papeis", fetch=FetchType.LAZY)
	private List<Usuario> usuarios;

	public Integer getId_papel() {
		return id_papel;
	}

	public void setId_papel(Integer id_papel) {
		this.id_papel = id_papel;
	}

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	
}
