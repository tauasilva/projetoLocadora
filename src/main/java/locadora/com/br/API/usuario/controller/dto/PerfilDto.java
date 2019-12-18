package locadora.com.br.API.usuario.controller.dto;

import java.util.Date;

import org.springframework.data.domain.Page;

import locadora.com.br.API.modelo.Perfil;

public class PerfilDto {
	
	
	private Long id;
	private String nome;
	private Date dhAtualizacao;
	private Integer idUsuarioAtualizacao;
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
	

	public PerfilDto(Perfil perfil) {
		super();
		this.id = perfil.getId();
		this.nome = perfil.getNome();
		this.dhAtualizacao = perfil.getDhAtualizacao();
		this.idUsuarioAtualizacao =  perfil.getIdUsuarioAtualizacao();
	}
	
	public PerfilDto(Long id, String nome, Date dhAtualizacao, Integer idUsuarioAtualizacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.dhAtualizacao = dhAtualizacao;
		this.idUsuarioAtualizacao = idUsuarioAtualizacao;
	}
	public static Page<PerfilDto> converter(Page<Perfil> perfil) {		
		return perfil.map(PerfilDto::new);	
	}	

}
