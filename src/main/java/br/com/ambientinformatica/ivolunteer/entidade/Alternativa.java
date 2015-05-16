package br.com.ambientinformatica.ivolunteer.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Alternativa {

	@Id
	@GeneratedValue(generator = "alternativa_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "alternativa_seq", sequenceName = "alternativa_seq", allocationSize = 1, initialValue = 1)
	private Integer id;
	
	private String alternativa;

	private boolean status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAlternativa() {
		return alternativa;
	}

	public void setAlternativa(String alternativa) {
		this.alternativa = alternativa;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
