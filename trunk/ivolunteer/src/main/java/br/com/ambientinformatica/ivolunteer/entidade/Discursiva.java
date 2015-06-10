package br.com.ambientinformatica.ivolunteer.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;

@Entity
public class Discursiva {

	@Id
	@GeneratedValue(generator = "discursiva_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "discursiva_seq", sequenceName = "discursiva_seq", allocationSize = 1, initialValue = 1)
	private Integer id;

	private Integer numeroLinhas;

	public Integer getNumeroLinhas() {
		return numeroLinhas;
	}

	public void setNumeroLinhas(Integer numeroLinhas) {
		this.numeroLinhas = numeroLinhas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
