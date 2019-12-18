package locadora.com.br.API.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import locadora.com.br.API.modelo.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

	Page<Perfil> findById(Long id, Pageable paginacao);

}
