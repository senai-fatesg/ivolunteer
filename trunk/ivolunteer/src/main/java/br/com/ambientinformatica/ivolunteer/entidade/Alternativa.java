package br.com.ambientinformatica.ivolunteer.entidade;

import java.io.Serializable;

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

	private String descricao;	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
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
		Alternativa other = (Alternativa) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.toLowerCase().replace(" ", "")
				.equals(other.descricao.toLowerCase().replace(" ", "")))
			return false;
		return true;
	}
	
}
