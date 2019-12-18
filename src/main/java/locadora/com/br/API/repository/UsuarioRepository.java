package locadora.com.br.API.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import locadora.com.br.API.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findBylogin(String email);
	
	Page<Usuario> findBylogin(String email, Pageable paginacao);

}
