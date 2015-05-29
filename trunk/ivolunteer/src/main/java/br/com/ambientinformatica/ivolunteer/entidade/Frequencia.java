package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Frequencia {

	@Id
	@GeneratedValue(generator = "frequencia_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "frequencia_seq", sequenceName = "frequencia_seq", allocationSize = 1, initialValue = 1)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	private Boolean presente = true;

	public void setPresenca() {
		this.presente = true;
		this.data = new Date();
	}

	public Integer getId() {
		return id;
	}

	public Date getData() {
		return data;
	}

	public Boolean isPresente() {
		return presente;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setPresente(Boolean presente) {
		this.presente = presente;
	}

}
