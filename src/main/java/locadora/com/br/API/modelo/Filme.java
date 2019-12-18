package locadora.com.br.API.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="\"TAB_FILME\"")
public class Filme {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_FILME")
	private Long idFilme; 	
	
	@Column(name="NM_FILME") 
	private String nomeFilme;

	@Column(name="NM_DIRETOR") 
	private String nomeDiretor;
	
	@Column(name="DISPONIVEL")
	private Integer disponivel;	
	

	public Filme() {
		
	}
	

	public Filme(Long idFilme, String nomeFilme, String nomeDiretor, Integer disponivel) {
		super();
		this.idFilme = idFilme;
		this.nomeFilme = nomeFilme;
		this.nomeDiretor = nomeDiretor;
		this.disponivel = disponivel;
	}

	public Filme(String nomeFilme, String nomeDiretor, Integer disponivel) {
		super();
		this.nomeFilme = nomeFilme;
		this.nomeDiretor = nomeDiretor;
		this.disponivel = disponivel;
	}

	public Long getIdFilme() {
		return idFilme;
	}

	public void setIdFilme(Long idFilme) {
		this.idFilme = idFilme;
	}

	public String getNomeFilme() {
		return nomeFilme;
	}

	public void setNomeFilme(String nomeFilme) {
		this.nomeFilme = nomeFilme;
	}

	public String getNomeDiretor() {
		return nomeDiretor;
	}

	public void setNomeDiretor(String nomeDiretor) {
		this.nomeDiretor = nomeDiretor;
	}

	public Integer getdisponivel() {
		return disponivel;
	}

	public void setdisponivel(Integer disponivel) {
		this.disponivel = disponivel;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

