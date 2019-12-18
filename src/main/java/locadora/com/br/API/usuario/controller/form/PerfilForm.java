package locadora.com.br.API.usuario.controller.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import locadora.com.br.API.modelo.Perfil;
import locadora.com.br.API.repository.PerfilRepository;

public class PerfilForm {

	@NotNull
	private Long id;
	
	@NotNull @NotEmpty @Length(min = 5)
	private String nome;

	@NotNull
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

	public Integer getIdUsuarioAtualizacao() {
		return idUsuarioAtualizacao;
	}

	public void setIdUsuarioAtualizacao(Integer idUsuarioAtualizacao) {
		this.idUsuarioAtualizacao = idUsuarioAtualizacao;
	}

	public Perfil converter(PerfilRepository perfilRepository) {
		
		return new Perfil(id,nome,new Date(),idUsuarioAtualizacao);		

	}		
	
	
}
