package br.ufc.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/* @Entity indica que objetos dessa classe se tornem “persistível” no banco de dados */
@Entity(name="USUARIO")
public class Usuario {
	
	/* @Id indica que id_usuario será a chave primária. 
	 * @GeneratedValue diz que queremos que esta chave 
	 * seja populada pelo banco, nesse caso, auto incremente. */
	@Id
	@Column(name="ID_USUARIO", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private int id_usuario;
	
	@Column(name="LOGIN", nullable=false)
	private String login;
	
	@Column(name="SENHA", nullable=false)
	private String senha;
	
	@Column(name="NOME", nullable=false)
	private String nome;
	
	@Column(name="EMAIL", nullable=false)
	private String email;
	
	private int id_papel;
	
	/* Notícias escritas pelo usuário que é autor. */
	@OneToMany(mappedBy="autor", targetEntity=Noticia.class,fetch=FetchType.LAZY)
	private List<Noticia> noticas;
	
	/* Classificados cadastrados pelos usuários. */
	@OneToMany(mappedBy="autor", targetEntity=Classificado.class,fetch=FetchType.LAZY)
	private List<Classificado> classificados;
	
	/* Comentários escritos pelo usuário. */
	@OneToMany(mappedBy="autor", targetEntity=Comentario.class,fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Comentario> comentarios;
	
	/* Papeis dos usuários. */
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="USUARIO_PAPEL",
				joinColumns=@JoinColumn(name="ID_USUARIO", referencedColumnName="ID_USUARIO"),
				inverseJoinColumns=@JoinColumn(name="ID_PAPEL", referencedColumnName="ID_PAPEL"))
	private List<Papel> papeis;
	
	
	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Noticia> getNoticas() {
		return noticas;
	}

	public void setNoticas(List<Noticia> noticas) {
		this.noticas = noticas;
	}

	public int getId_papel() {
		return id_papel;
	}
	
	public void setId_papel(int id_papel) {
		this.id_papel = id_papel;
	}
	
	public List<Classificado> getClassificados() {
		return classificados;
	}

	public void setClassificados(List<Classificado> classificados) {
		this.classificados = classificados;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	

	public List<Papel> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}
} 