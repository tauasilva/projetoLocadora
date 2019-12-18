package locadora.com.br.API.movimentacao.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import locadora.com.br.API.filme.controller.form.FilmeForm;
import locadora.com.br.API.modelo.Filme;
import locadora.com.br.API.modelo.MovimentacaoFilmes;
import locadora.com.br.API.modelo.Usuario;
import locadora.com.br.API.movimentacao.controller.form.MovimentacaoForm;
import locadora.com.br.API.repository.FilmeRepository;
import locadora.com.br.API.repository.MovimentacaoRepository;
import locadora.com.br.API.repository.UsuarioRepository;

@CrossOrigin
@RestController
@RequestMapping("/Movimentacoes")
public class MovimentacaoController {

	@Autowired
	MovimentacaoRepository movimentacaoRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	FilmeRepository filmeRepository;
	
	/*POST -> lOCAR FILME*/
	/*POST -> DEVOLVER FILME*/
	
	@PostMapping("/locar")
	public ResponseEntity<MovimentacaoFilmes> LocarFilme(@RequestBody @Valid MovimentacaoForm form, UriComponentsBuilder uriBuilder) {
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Optional<Usuario> usuarioLogado = usuarioRepository.findBylogin(auth.getName());
		
		Usuario idUsuarioLogado = usuarioLogado.get();	
		
		Optional<Filme> filmeSolicitadoParam = filmeRepository.findById(form.getIdFilme());
		if(filmeSolicitadoParam.isPresent()) {
			
			Filme filmeSolicitado = filmeSolicitadoParam.get();
			
			
			if(filmeSolicitado.getdisponivel().equals(1)) {
				
				FilmeForm atualizaFilme = new FilmeForm();

				atualizaFilme.locaFilme(filmeSolicitado.getIdFilme(), filmeRepository);
				
				MovimentacaoFilmes movimentoDeLocacao = form.converterLocacao(idUsuarioLogado.getId(),movimentacaoRepository);
				
				movimentacaoRepository.save(movimentoDeLocacao);
				
				URI uri = uriBuilder.path("/filme/{idFilme}").buildAndExpand(movimentoDeLocacao.getIdFilme()).toUri();
				
				return ResponseEntity.created(uri).body(movimentoDeLocacao);					
			
			}
		}
		
		return ResponseEntity.notFound().build();

		
	}	
	
	@PutMapping("/devolver")
	public ResponseEntity<MovimentacaoFilmes> devolverFilme(@RequestBody @Valid MovimentacaoForm form, UriComponentsBuilder uriBuilder) {
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Optional<Usuario> usuarioLogado = usuarioRepository.findBylogin(auth.getName());
		
		Usuario idUsuarioLogado = usuarioLogado.get();
		
		Optional<MovimentacaoFilmes> filmeExist = movimentacaoRepository.findByidFilmeAndIdUsuarioAndHoraDevolucao(form.getIdFilme(),idUsuarioLogado.getId(),null);
		
		if(filmeExist.isPresent()) {
			
			MovimentacaoFilmes movimentacaoId = filmeExist.get();
			
			if(movimentacaoId.getHoraDevolucao()== null) {
				Optional<Filme> filmeSolicitadoParam = filmeRepository.findById(form.getIdFilme());
				
				if(filmeSolicitadoParam.isPresent()) {
					
					Filme filmeSolicitado = filmeSolicitadoParam.get();		
					
					FilmeForm atualizaFilme = new FilmeForm();

					atualizaFilme.devolveFilme(filmeSolicitado.getIdFilme(), filmeRepository);
				
					MovimentacaoFilmes movimentoDeDevolucao = form.converterLocacao(idUsuarioLogado.getId(),movimentacaoRepository);
					
					form.atualizar(movimentacaoId.getIdMovimento(), movimentacaoRepository);
								
					URI uri = uriBuilder.path("/filme/{idFilme}").buildAndExpand(movimentoDeDevolucao.getIdFilme()).toUri();
					
					return ResponseEntity.created(uri).body(movimentoDeDevolucao);			
				}
				
			}

		}
		
		return ResponseEntity.notFound().build();
		
	}		
	
}
