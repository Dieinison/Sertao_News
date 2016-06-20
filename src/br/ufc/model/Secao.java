package br.ufc.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/* @Entity indica que objetos dessa classe se tornem “persistível” no banco de dados*/
@Entity(name="SECAO")
public class Secao {
	
	/* @Id indica que id_secao será a chave primária. 
	 * @GeneratedValue diz que queremos que esta chave 
	 * seja populada pelo banco, nesse caso, auto incremente. */
	@Id 
	@Column(name="ID_SECAO", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id_secao;
	
	@Column(name="TITULO", nullable=false)
	private String titulo;
	
	@Column(name="DESCRICAO", nullable=false)
	private String descricao;
	
	@OneToMany(mappedBy="secao", targetEntity=Noticia.class,fetch = FetchType.EAGER)
	private List<Noticia> noticias;
	
	public Integer getId_secao() {
		return id_secao;
	}

	public void setId_secao(Integer id_secao) {
		this.id_secao = id_secao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public List<Noticia> getNoticias() {
		return noticias;
	}
	
	public void setNoticias(List<Noticia> noticias) {
		this.noticias = noticias;
	}
}
