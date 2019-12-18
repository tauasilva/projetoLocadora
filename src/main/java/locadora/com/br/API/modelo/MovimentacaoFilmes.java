package locadora.com.br.API.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="\"TAB_MOVIMENTACAO\"")
public class MovimentacaoFilmes {
	


	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_MOVIMENTACAO")
	private Long idMovimento; 	
	
	@Column(name="ID_FILME")
	private Long idFilme; 	
	
	@Column(name="ID_USUARIO")
	private Long idUsuario; 
	
	@Column(name="DT_HR_RETIRADA")
	private Date horaRetirada;	
	
	@Column(name="DT_HR_DEVOLUCAO")
	private Date horaDevolucao;

	public Long getIdMovimento() {
		return idMovimento;
	}

	public void setIdMovimento(Long idMovimento) {
		this.idMovimento = idMovimento;
	}

	public Long getIdFilme() {
		return idFilme;
	}

	public void setIdFilme(Long idFilme) {
		this.idFilme = idFilme;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Date getHoraRetirada() {
		return horaRetirada;
	}

	public void setHoraRetirada(Date horaRetirada) {
		this.horaRetirada = horaRetirada;
	}

	public Date getHoraDevolucao() {
		return horaDevolucao;
	}

	public void setHoraDevolucao(Date horaDevolucao) {
		this.horaDevolucao = horaDevolucao;
	}

	public MovimentacaoFilmes(Long idMovimento, Long idFilme, Long idUsuario, Date horaRetirada,
			Date horaDevolucao) {
		super();
		this.idMovimento = idMovimento;
		this.idFilme = idFilme;
		this.idUsuario = idUsuario;
		this.horaRetirada = horaRetirada;
		this.horaDevolucao = horaDevolucao;
	}
	
	public MovimentacaoFilmes(Long idFilme, Long idUsuario, Date horaRetirada, Date horaDevolucao) {
		super();
		this.idFilme = idFilme;
		this.idUsuario = idUsuario;
		this.horaRetirada = horaRetirada;
		this.horaDevolucao = horaDevolucao;
	}
	
	public MovimentacaoFilmes() {
	}
	
	

}
