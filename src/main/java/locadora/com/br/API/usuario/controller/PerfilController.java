package locadora.com.br.API.usuario.controller;

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

import locadora.com.br.API.modelo.Perfil;
import locadora.com.br.API.repository.PerfilRepository;
import locadora.com.br.API.usuario.controller.dto.PerfilDto;
import locadora.com.br.API.usuario.controller.form.PerfilForm;
import locadora.com.br.API.usuario.controller.form.PerfilFormAtualizar;
@CrossOrigin
@RestController
@RequestMapping("/perfil")
public class PerfilController {
	
	@Autowired
	PerfilRepository perfilRepository;
	
	@GetMapping
	public Page<PerfilDto> listaPerfil(@RequestParam(required = false) Long id,@PageableDefault(sort="id", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao){
		
		if (id == null) {		
			Page<Perfil> perfil = perfilRepository.findAll(paginacao);
			return PerfilDto.converter(perfil);
		}else {
			Page<Perfil> perfil = perfilRepository.findById(id,paginacao);
			return PerfilDto.converter(perfil);
		}		
		
	}
	
	@PostMapping 
	public ResponseEntity<PerfilDto> cadastraPerfil(@RequestBody @Valid PerfilForm form, UriComponentsBuilder uriBuilder) {
		
		
		System.out.println("idDoUsu");
		System.out.println(form.getId());
		
		Perfil perfil = form.converter(perfilRepository);
		
		perfilRepository.save(perfil);
		
		URI uri = uriBuilder.path("/perfil/{perfil}").buildAndExpand(perfil.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new PerfilDto(perfil));		
		
	}
	
	
	@PutMapping("/{idPerfil}")
	public ResponseEntity<PerfilDto> AtualizarPerfil(@PathVariable Long idPerfil,@RequestBody @Valid PerfilFormAtualizar form) {
		
		Optional<Perfil> perfilExist = perfilRepository.findById(idPerfil);
		
		Perfil perfil = perfilExist.get();
		
		
		if(perfilExist.isPresent()) {
			
			System.out.println("entoru");
			form.atualizar(perfil.getId(), perfilRepository);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping("/{idPerfil}")
	@Transactional
	public ResponseEntity<?> deletarPerfil(@PathVariable Long idPerfil) {
		Optional<Perfil> optional = perfilRepository.findById(idPerfil);
		if (optional.isPresent()) {
			perfilRepository.deleteById(idPerfil);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();		
		
		
	}
	

}
