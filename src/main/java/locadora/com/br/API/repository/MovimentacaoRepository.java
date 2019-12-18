package locadora.com.br.API.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import locadora.com.br.API.modelo.MovimentacaoFilmes;

public interface MovimentacaoRepository extends JpaRepository<MovimentacaoFilmes, Long> {

	Page<MovimentacaoFilmes> findByIdMovimento(Long id, Pageable paginacao);
	
	Optional<MovimentacaoFilmes> findByIdMovimento(Long id);
	
	Optional<MovimentacaoFilmes> findByidFilmeAndIdUsuarioAndHoraDevolucao(Long IdFilme,Long IdUsuario,Date horaDevolucao);
}

