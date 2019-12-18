package locadora.com.br.API.filme.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import locadora.com.br.API.filme.controller.form.FilmeForm;
import locadora.com.br.API.modelo.Filme;
import locadora.com.br.API.repository.FilmeRepository;

@CrossOrigin
@RestController
@RequestMapping("/filme")
public class FilmeController {

	@Autowired
	FilmeRepository filmeRepository;
	
	@GetMapping
	public Page<Filme> listaFilmes(@RequestParam(required = false) Long idFilme,@PageableDefault(sort="idFilme", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao){
		
	
		if (idFilme == null) {		
			Page<Filme> filme = filmeRepository.findAll(paginacao);
			return filme;
		}else {
			Page<Filme> filme = filmeRepository.findByIdFilme(idFilme,paginacao);
			return filme;
		}		
		
	}
	
	@PostMapping 
	@Transactional
	public ResponseEntity<Filme> cadastraFilme(@RequestBody @Valid FilmeForm form, UriComponentsBuilder uriBuilder) {
		
		Filme filme = form.converter(filmeRepository);
		
		filmeRepository.save(filme);
		
		URI uri = uriBuilder.path("/filme/{idFilme}").buildAndExpand(filme.getIdFilme()).toUri();
		
		return ResponseEntity.created(uri).body(filme);		
		
	}	
	
	
	@PutMapping("/{idFilme}")
	@Transactional	
	public ResponseEntity<Filme> atualizarFilme(@PathVariable Long idFilme, @RequestBody @Valid FilmeForm form) {
		
		Optional<Filme> filmeExist = filmeRepository.findByIdFilme(idFilme);
		
		Filme filmeId = filmeExist.get();
		if(filmeExist.isPresent()) {
			form.atualizar(filmeId.getIdFilme().longValue(), filmeRepository);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}	
	

	@DeleteMapping("/{idFilme}")
	@Transactional
	public ResponseEntity<?> deletarFilme(@PathVariable Long idFilme) {
		Optional<Filme> optional = filmeRepository.findByIdFilme(idFilme);
		if (optional.isPresent()) {
			filmeRepository.deleteById(idFilme);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();		
	}	
	
	@GetMapping("/disponiveis")
	public Page<Filme> listaFilmesDisponiveis(@PageableDefault(sort="idFilme", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao){
	
		Page<Filme> filme = filmeRepository.findByDisponivel(1,paginacao);
		return filme;
		
	}
	
	@GetMapping("/diretor")
	public Page<Filme> listaFilmesPorDiretor(@RequestParam(required = false) String nomeDiretor,@PageableDefault(sort="idFilme", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao){
		Page<Filme> filme = filmeRepository.findByNomeDiretor(nomeDiretor,paginacao);
		return filme;

	}
	
}
