package locadora.com.br.API.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="\"PAD_USUARIO\"")
public class Usuario implements UserDetails{
	
	
	private static final long serialVersionUID = 1L;
	/*ID DO USUARIO*/
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_USUARIO")
    private Long id;
	
	//'ID_PESSOA DA TABELA PAD_PESSOA',
	@Column(name="NOME_USUARIO")
	private String nomePessoa; 	
	
	//'LOGIN',
	@Column(name="TX_LOGIN")
	private String login;
	
	//'SENHA'
	@Column(name="TX_SENHA")
	private String senha;
	
	//'1-ATIVO / 0-INATIVO'
	@Column(name="ST_SITUACAO")
	private String situacao;
	
	//'SYSDATE DATA E HORA DA ULTIMA ALTERAÇÃO'
	@Column(name="DT_HR_ATUALIZACAO")	
	private  Date DtHrAtualizacao;	
	
	//'ID DO USUÁRIO DE ATUALIZAÇÃO'
	@Column(name="ID_USUARIO_ATUALIZACAO")	
	private  Integer IdUsuarioAtualizacao;
		
	@ManyToMany(fetch = FetchType.EAGER)
	@Column(name="PERFIS")
	private List<Perfil> perfis = new ArrayList<>();
	

	public Usuario(Long id, String nomePessoa, String login, String situacao, Date dtHrAtualizacao,
			Integer idUsuarioAtualizacao, List<Perfil> perfis) {
		super();
		this.id = id;
		this.nomePessoa = nomePessoa;
		this.login = login;
		this.situacao = situacao;
		this.DtHrAtualizacao = dtHrAtualizacao;
		this.IdUsuarioAtualizacao = idUsuarioAtualizacao;
		this.perfis = perfis;
	}
	

	public Usuario(String nomePessoa, String login,String senha,String situacao, Date dtHrAtualizacao,
			Integer idUsuarioAtualizacao, List<Perfil> perfis) {
		super();
		this.nomePessoa = nomePessoa;
		this.login = login;
		this.senha = senha;
		this.situacao = situacao;
		this.DtHrAtualizacao = dtHrAtualizacao;
		this.IdUsuarioAtualizacao = idUsuarioAtualizacao;
		this.perfis = perfis;
	}	


	public Usuario(Long id, String nome, String login, String senha, List<Perfil> perfis) {
		super();
		this.login = login;
		this.senha = senha;
		this.perfis = perfis;
	}
	
	public Usuario(Long id, String nome, String senha, List<Perfil> perfis) {
		super();
		this.senha = senha;
		this.perfis = perfis;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	public String getNomePessoa() {
		return nomePessoa;
	}


	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}


	public String getSituacao() {
		return situacao;
	}


	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}


	public Date getDtHrAtualizacao() {
		return DtHrAtualizacao;
	}


	public void setDtHrAtualizacao(Date dtHrAtualizacao) {
		DtHrAtualizacao = dtHrAtualizacao;
	}


	public Integer getIdUsuarioAtualizacao() {
		return IdUsuarioAtualizacao;
	}


	public void setIdUsuarioAtualizacao(Integer idUsuarioAtualizacao) {
		IdUsuarioAtualizacao = idUsuarioAtualizacao;
	}


	public List<Perfil> getPerfis() {
		return perfis;
	}


	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public void setLogin(String login) {
		this.login = login;
	}



	public Usuario() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setEmail(String email) {
		this.login = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.perfis;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
