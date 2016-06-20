package br.ufc.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="COMENTARIO")
public class Comentario {
	
	@Id
	@Column(name="ID_COMENTARIO", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id_comentario;
	
	@Column(name = ("ID_NOTICIA"), nullable = false, insertable=false, updatable=false)
	private Integer id_noticia;

	@Column(name = ("ID_AUTOR"), nullable = false, insertable=false, updatable=false)
	private Integer id_autor;
	
	@ManyToOne(optional=false, cascade=CascadeType.ALL)
	@JoinColumn(name="ID_NOTICIA", referencedColumnName="ID_NOTICIA")
	private Noticia noticia;
	
	/* Autor do comentário. */
	@ManyToOne(optional=false, cascade=CascadeType.ALL)
	@JoinColumn(name="ID_AUTOR", referencedColumnName="ID_USUARIO")
	private Usuario autor;
	
	/* Campo de texto. */
	@Column(name="TEXTO", nullable=false)
	private String texto;

	public Integer getId_comentario() {
		return id_comentario;
	}

	public void setId_comentario(Integer id_comentario) {
		this.id_comentario = id_comentario;
	}

	public Noticia getNotica() {
		return noticia;
	}

	public void setNotica(Noticia notica) {
		this.noticia = notica;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario usuario) {
		this.autor = usuario;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}	
	public Integer getId_autor() {
		return id_autor;
	}
	public void setId_autor(Integer id_autor) {
		this.id_autor = id_autor;
	}
	public Integer getId_noticia() {
		return id_noticia;
	}
	public void setId_noticia(Integer id_noticia) {
		this.id_noticia = id_noticia;
	}
}
