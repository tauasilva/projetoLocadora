package locadora.com.br.API.filme.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import locadora.com.br.API.modelo.Filme;
import locadora.com.br.API.repository.FilmeRepository;

public class FilmeForm {

	@NotNull @NotEmpty @Length(min = 5)
	private String nomeFilme;
 
	@NotNull @NotEmpty @Length(min = 10)
	private String nomeDiretor;

	private Integer disponivel;

	public String getNomeFilme() {
		return nomeFilme;
	}

	public void setNomeFilme(String nomeFilme) {
		this.nomeFilme = nomeFilme;
	}

	public String getNomeDiretor() {
		return nomeDiretor;
	}

	public void setNomeDiretor(String nomeDiretor) {
		this.nomeDiretor = nomeDiretor;
	}


	public Integer getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Integer disponivel) {
		this.disponivel = disponivel;
	}

	public Filme converter(FilmeRepository filmeRepository) {
		
		return new Filme(nomeFilme,nomeDiretor,disponivel);	

	}	

	public Filme atualizar(Long id,FilmeRepository filmeRepository) {
		
		Filme filme = filmeRepository.getOne(id);
		
		System.out.println(filme);

		filme.setNomeDiretor(nomeDiretor);
		filme.setNomeFilme(nomeFilme);
		filme.setdisponivel(disponivel);

		filmeRepository.save(filme);
		
		return filme;
	}
	
	public Filme locaFilme(Long id,FilmeRepository filmeRepository) {
		
		Filme filme = filmeRepository.getOne(id);
		
		filme.setdisponivel(0);

		filmeRepository.save(filme);
		
		return filme;
	}	
	
	public Filme devolveFilme(Long id,FilmeRepository filmeRepository) {
		
		Filme filme = filmeRepository.getOne(id);
		
		filme.setdisponivel(1);

		filmeRepository.save(filme);
		
		return filme;
	}	
	
}
