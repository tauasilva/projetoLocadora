package locadora.com.br.API.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import locadora.com.br.API.config.security.TokenService;
import locadora.com.br.API.controller.dto.TokenDto;
import locadora.com.br.API.controller.form.LoginForm;
@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AutenticacaoController {


	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@CrossOrigin
	@PostMapping 
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form ) {
		
//		System.out.println("credenciais");
//		System.out.println(form.getLogin());
//		System.out.println(form.getSenha());
		
		
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		try {
			Authentication authenticate = authManager.authenticate(dadosLogin);
			
			String token = tokenService.gerarToken(authenticate);
			System.out.println(token);
			
			
			return ResponseEntity.ok(new TokenDto(token,"bearer"));

			
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
					
		}
		
		
	}
	
	
}
