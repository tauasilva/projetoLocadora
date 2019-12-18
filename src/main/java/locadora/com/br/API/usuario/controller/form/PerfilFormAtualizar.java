package locadora.com.br.API.usuario.controller.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import locadora.com.br.API.modelo.Perfil;
import locadora.com.br.API.repository.PerfilRepository;

public class PerfilFormAtualizar {

	@NotNull @NotEmpty @Length(min = 5)
	private String nome;

	@NotNull
	private Integer idUsuarioAtualizacao;	
	
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

	public Perfil atualizar(Long id,PerfilRepository perfilRepostiroy) {
		
		Perfil perfil = perfilRepostiroy.getOne(id);
		
		System.out.println(perfil.getNome());
		
		perfil.setNome(this.nome);
		perfil.setIdUsuarioAtualizacao(this.idUsuarioAtualizacao);
		perfil.setDhAtualizacao(new Date());
		
		perfilRepostiroy.save(perfil);
		
		return perfil;
	}	
	
	
}
