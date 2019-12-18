package locadora.com.br.API.usuario.controller.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import org.springframework.data.domain.Page;

import locadora.com.br.API.modelo.Perfil;
import locadora.com.br.API.modelo.Usuario;

public class UsuarioDto {

	private Long id; // ID PESSOA
	private String NomePessoa; //'ID_PESSOA DA TABELA PAD_PESSOA', 	
	private String login; 	//'LOGIN',
	private String situacao;	//'1-ATIVO / 0-INATIVO'
	private Date DtHrAtualizacao; 	//'SYSDATE DATA E HORA DA ULTIMA ALTERAÇÃO'	
	private Integer IdUsuarioAtualizacao; 	//'ID DO USUÁRIO DE ATUALIZAÇÃO'
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();
	
	public UsuarioDto(Long id, String NomePessoa, String login, String situacao, Date dtHrAtualizacao,
			Integer idUsuarioAtualizacao, List<Perfil> perfis) {
		super();
		this.id = id;
		this.NomePessoa = NomePessoa;
		this.login = login;
		this.situacao = situacao;
		DtHrAtualizacao = dtHrAtualizacao;
		IdUsuarioAtualizacao = idUsuarioAtualizacao;
		this.perfis = perfis;
	}

	
	public UsuarioDto(Usuario usuario) {
		super();
		this.id = usuario.getId();
		this.NomePessoa = usuario.getNomePessoa();
		this.login = usuario.getLogin();
		this.situacao = usuario.getSituacao();
		DtHrAtualizacao = usuario.getDtHrAtualizacao();
		IdUsuarioAtualizacao = usuario.getIdUsuarioAtualizacao();
		this.perfis = usuario.getPerfis();
	}

	public String getNomePessoa() {
		return NomePessoa;
	}


	public void setNomePessoa(String nomePessoa) {
		NomePessoa = nomePessoa																																																							;
	}


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public static Page<UsuarioDto> converter(Page<Usuario> usuarios) {		
		return usuarios.map(UsuarioDto::new);	
	}
}
