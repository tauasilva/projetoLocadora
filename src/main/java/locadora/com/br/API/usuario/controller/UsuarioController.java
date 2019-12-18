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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import locadora.com.br.API.modelo.Usuario;
import locadora.com.br.API.repository.UsuarioRepository;
import locadora.com.br.API.usuario.controller.dto.UsuarioDto;
import locadora.com.br.API.usuario.controller.form.UsuarioForm;
import locadora.com.br.API.usuario.controller.form.UsuarioFormAtualizar;
@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	@RequestMapping(value="/cript/{pass}", method=RequestMethod.GET)
	public String criptografa(@PathVariable String pass)
	{
		System.out.println("senha cript");
		System.out.println(new BCryptPasswordEncoder().encode(pass));
		return new BCryptPasswordEncoder().encode(pass);
	}
	
	@GetMapping
	public Page<UsuarioDto> ListaUsuarios(@RequestParam(required = false) String login,@PageableDefault(sort="id", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao){
		
		if (login == null) {		
			Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
			return UsuarioDto.converter(usuarios);
		}else {
			Page<Usuario> usuarios = usuarioRepository.findBylogin(login,paginacao);
			return UsuarioDto.converter(usuarios);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioDto> cadastraUsuario(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {
				
	
		Usuario user = form.converter(usuarioRepository);
		
		usuarioRepository.save(user);
		
		URI uri = uriBuilder.path("/usuario/{email}").buildAndExpand(user.getLogin()).toUri();
		
		return ResponseEntity.created(uri).body(new UsuarioDto(user));
		
	}
	
	@PutMapping("/{login}")
	@Transactional	
	public ResponseEntity<UsuarioDto> atualizarUsuario(@PathVariable String login, @RequestBody @Valid UsuarioFormAtualizar form) {
		Optional<Usuario> userExist = usuarioRepository.findBylogin(login);
		
		Usuario userId = userExist.get();
		
		
		if(userExist.isPresent()) {
			System.out.println("entoru");
			form.atualizar(userId.getId(), usuarioRepository);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {
		Optional<Usuario> optional = usuarioRepository.findById(id);
		if (optional.isPresent()) {
			usuarioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();		
		
		
	}
	
	
}
