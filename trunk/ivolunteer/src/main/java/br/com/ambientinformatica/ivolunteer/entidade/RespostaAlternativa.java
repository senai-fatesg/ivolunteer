package br.com.ambientinformatica.ivolunteer.entidade;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class RespostaAlternativa {
	@Id
	@GeneratedValue(generator = "seq_respostaalternativa", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_respostaalternativa", sequenceName = "seq_respostaalternativa", allocationSize = 1, initialValue = 1)
	private Integer id;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Alternativa alternativa;	
	
	private Boolean marcado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Alternativa getAlternativa() {
		return alternativa;
	}

	public void setAlternativa(Alternativa alternativa) {
		this.alternativa = alternativa;
	}

	public Boolean getMarcado() {
		return marcado;
	}

	public void setMarcado(Boolean marcado) {
		this.marcado = marcado;
	}
	
	

}
