package locadora.com.br.API.movimentacao.controller.form;
import java.util.Date;

import javax.validation.constraints.NotNull;
import locadora.com.br.API.modelo.MovimentacaoFilmes;
import locadora.com.br.API.repository.MovimentacaoRepository;

public class MovimentacaoForm {
	@NotNull 
	private Long idFilme; 
	
	public Long getIdFilme() {
		return idFilme;
	}

	public void setIdFilme(Long idFilme) {
		this.idFilme = idFilme;
	}


 
	public MovimentacaoFilmes converterLocacao(Long idUsuario,MovimentacaoRepository movimentacaoRepository) {
		
		return new MovimentacaoFilmes(idFilme,idUsuario,new Date(),null);	

	}		
	
	public MovimentacaoFilmes atualizar(Long id, MovimentacaoRepository movimentacaoRepository) {
		
		
		MovimentacaoFilmes movimento = movimentacaoRepository.getOne(id);
		
		movimento.setHoraDevolucao(new Date(0));
		
		movimentacaoRepository.save(movimento);
		
		return movimento;
		
	}
	
}