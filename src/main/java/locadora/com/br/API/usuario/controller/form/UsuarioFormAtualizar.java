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

public class UsuarioFormAtualizar {

	@NotNull @NotEmpty @Length(min = 5)
	private String senha; // SENHA
	
	@NotNull
	private String situacao;	//'1-ATIVO / 0-INATIVO'

	private Integer idauto; 	//'ID DO USUÁRIO DE ATUALIZAÇÃO'
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();	
	
	

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
	public Integer getIdauto() {
		return idauto;
	}
	public void setIdauto(Integer idauto) {
		this.idauto = idauto;
	}
	public List<Perfil> getPerfis() {
		return perfis;
	}
	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public Usuario atualizar(Long id,UsuarioRepository usuarioRepository) {
		
		Usuario usuario = usuarioRepository.getOne(id);
		
		System.out.println(usuario.getLogin());
		
		usuario.setSenha(this.senha);
		usuario.setDtHrAtualizacao(new Date());
		usuario.setPerfis(perfis);
		usuario.setSituacao(situacao);
		usuario.setIdUsuarioAtualizacao(idauto);
		
		usuarioRepository.save(usuario);
		
		return usuario;
	}	
	
}
