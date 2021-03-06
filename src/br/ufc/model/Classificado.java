package br.ufc.model;


import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity(name="CLASSIFICADO")
public class Classificado {

	@Id
	@Column(name="ID_CLASSIFICADO", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id_classificado;
	
	@Column(name="TITULO", nullable=false)
	private String titulo;
	
	@Column(name="TEXTO", nullable=false)
	private String texto;
	
	@Column(name="PRECO", nullable=false)
	private Float preco;
	
	@Column(name="TELEFONE", nullable=false)
	private String telefone;
	
	@Column(name="MELHOR_OFERTA")
	private Float melhor_oferta;
	
	@Version
	@Column(name="DATA_OFERTA", nullable = false, columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_oferta;
	
	/* Autor */
	@ManyToOne(optional=false, cascade=CascadeType.ALL)
	@JoinColumn(name="id_autor", referencedColumnName="id_usuario")
	private Usuario autor;
	
	/* GETTER's and SETTER's */
	
	public Integer getId_classificado() {
		return id_classificado;
	}

	public void setId_classificado(Integer id_classificado) {
		this.id_classificado = id_classificado;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Float getMelhor_oferta() {
		return melhor_oferta;
	}

	public void setMelhor_oferta(Float melhor_oferta) {
		this.melhor_oferta = melhor_oferta;
	}	
	
	public Date getData_oferta() {
		return data_oferta;
	}

	public void setData_oferta(Date data_oferta) {
		this.data_oferta = data_oferta;
	}

	public void setData_oferta(Timestamp data_oferta) {
		this.data_oferta = data_oferta;
	}
	
	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario usuario) {
		this.autor = usuario;
	}
	
}
