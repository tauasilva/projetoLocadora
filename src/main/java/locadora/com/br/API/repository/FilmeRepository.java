package locadora.com.br.API.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import locadora.com.br.API.modelo.Filme;


public interface FilmeRepository extends JpaRepository<Filme, Long> {

	Page<Filme> findByIdFilme(Long id, Pageable paginacao);
	
	Optional<Filme> findByIdFilme(Long id);
	
	Page<Filme> findByDisponivel(Integer disponivel, Pageable paginacao);
	
	Page<Filme> findByNomeDiretor(String nomeDiretor, Pageable paginacao);


}