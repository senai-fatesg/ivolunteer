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

	public Integer getId() {
		return id;
	}

	public Date getData() {
		return data;
	}

	public Boolean getPresente() {
		return presente;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setPresente(Boolean presente) {
		this.presente = presente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((presente == null) ? 0 : presente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Frequencia other = (Frequencia) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (presente == null) {
			if (other.presente != null)
				return false;
		} else if (!presente.equals(other.presente))
			return false;
		return true;
	}

}
