package locadora.com.br.API.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import locadora.com.br.API.modelo.Usuario;
import locadora.com.br.API.repository.UsuarioRepository;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	private UsuarioRepository repository;
	
	private TokenService tokenService;
	
	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;
	}
	
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);
		boolean valido = tokenService.isTokenValido(token);
		
			
		if (valido) {
			autenticarCliente(token);
			
			
		}
		
		System.out.println(token);
				
		System.out.println(valido);
		
		filterChain.doFilter(request, response);
		
	}

	private void autenticarCliente(String token) {
		
		Long idUsuariToken = tokenService.getIdUsuario(token); 
		
		System.out.println(idUsuariToken);
		
		Usuario usuario = repository.findById(idUsuariToken).get();
		
	
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}

	private String recuperarToken(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
		
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		
		}
		
		return token.substring(7,token.length());
		
	}
	

}
