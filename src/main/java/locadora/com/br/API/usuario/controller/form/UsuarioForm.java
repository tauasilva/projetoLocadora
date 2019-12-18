package locadora.com.br.API.usuario.controller.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import locadora.com.br.API.modelo.Perfil;
import locadora.com.br.API.modelo.Usuario;
import locadora.com.br.API.repository.UsuarioRepository;

public class UsuarioForm {
	
	private String NomePessoa; //'ID_PESSOA DA TABELA PAD_PESSOA',
	
	@NotNull @NotEmpty @Length(min = 5)
	private String login; 	//'LOGIN',
	
	@NotNull @NotEmpty @Length(min = 5)
	private String senha; // SENHA
	
	@NotNull
	private String situacao;	//'1-ATIVO / 0-INATIVO'

	private Integer idauto; 	//'ID DO USUÁRIO DE ATUALIZAÇÃO'
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();	

	
	
	public String getNomePessoa() {
		return NomePessoa;
	}
	public void setNomePessoa(String nomePessoa) {
		NomePessoa = nomePessoa;
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
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public Integer getidauto() {
		return idauto;
	}
	public void setidauto(Integer idauto) {
		this.idauto = idauto;
	}
	public List<Perfil> getPerfis() {
		return perfis;
	}
	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public Usuario converter(UsuarioRepository usuarioRepository) {
	
		return new Usuario(NomePessoa, login, senha,	situacao, new Date(), idauto, perfis); 			

	}

	
}
