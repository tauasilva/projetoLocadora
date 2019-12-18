package locadora.com.br.API.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="\"PAD_PERFIL\"")
public class Perfil implements GrantedAuthority{


	private static final long serialVersionUID = 1L;
	@Column(name="ID_PERFIL")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NM_PERFIL")
	private String nome;

	@Column(name="DT_HR_ATUALIZACAO")
	private Date dhAtualizacao;
	
	@Column(name="ID_USUARIO_ATUALIZACAO")
	private Integer idUsuarioAtualizacao;
	
	
	public Perfil(Long id, String nome, Date dhAtualizacao,Integer idUsuarioAtualizacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.dhAtualizacao = dhAtualizacao;
		this.idUsuarioAtualizacao = idUsuarioAtualizacao;
	}

	public Perfil() {
		
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Date getDhAtualizacao() {
		return dhAtualizacao;
	}


	public void setDhAtualizacao(Date dhAtualizacao) {
		this.dhAtualizacao = dhAtualizacao;
	}


	public Integer getIdUsuarioAtualizacao() {
		return idUsuarioAtualizacao;
	}


	public void setIdUsuarioAtualizacao(Integer idUsuarioAtualizacao) {
		this.idUsuarioAtualizacao = idUsuarioAtualizacao;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return nome;
	}
	
}
