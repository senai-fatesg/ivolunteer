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
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	private boolean presente = true;

	public void setPresenca() {
		this.presente =true;
		this.data = new Date();
	}

	public int getId() {
		return id;
	}

	public Date getData() {
		return data;
	}

	public boolean isPresente() {
		return presente;
	}

}
