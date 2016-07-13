package br.ufc.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;


/* @Entity indica que objetos dessa classe se tornem “persistível” no banco de dados*/
@Entity(name="NOTICIA")
public class Noticia {

	/* @Id indica que id_noticia será a chave primária. 
	 * @GeneratedValue diz que queremos que esta chave 
	 * seja populada pelo banco, nesse caso, auto incremente. */
	@Id
	@Column(name="ID_NOTICIA", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id_noticia;
	
	@Column(name="TITULO", nullable=false)
	private String titulo;
	
	@Column(name="SUBTITULO", nullable=false)
	private String subtitulo;
	
	@Column(name="TEXTO")
	private String texto;
	
	@Version
	@Column (name = ("DATA_NOTICIA"), nullable = false, columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_noticia;
	
	/* Comentários da notícia */
	 @OneToMany(mappedBy="noticia",targetEntity=Comentario.class,cascade=CascadeType.REMOVE)
	 private List<Comentario> comentarios;
	
	@Column (name = "ID_SECAO", nullable = false, insertable=false, updatable=false)
	private Integer id_secao;
	
	/* Chave estrangeira id_secao referencia id_secao da tabela secao. */
	@ManyToOne(optional=false)
	@JoinColumn(name="id_secao", referencedColumnName="id_secao")
	private Secao secao; 
	
	/* Chave estrangeira id_autor referencia id_usuario da tabela usuario. */
	@ManyToOne(optional=true)
	@JoinColumn(name="id_autor", referencedColumnName="id_usuario")
	private Usuario autor; 
	
	/* GETTER's and SETTER's */
	
	public int getId_noticia() {
		return id_noticia;
	}

	public void setId_noticia(int id_noticia) {
		this.id_noticia = id_noticia;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getData_noticia() {
		return data_noticia;
	}

	public void setData_noticia(Date data_noticia) {
		this.data_noticia = data_noticia;
	}

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}
	
	public Integer getId_secao() {
		return id_secao;
	}

	public void setId_secao(Integer id_secao) {
		this.id_secao = id_secao;
	}

	public void setId_noticia(Integer id_noticia) {
		this.id_noticia = id_noticia;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}
	
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
}
