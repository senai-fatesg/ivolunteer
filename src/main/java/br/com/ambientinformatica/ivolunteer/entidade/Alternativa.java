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

	@Override
   public int hashCode() {
	   final int prime = 31;
	   int result = 1;
	   result = prime * result
	         + ((alternativa == null) ? 0 : alternativa.hashCode());
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
	   if (alternativa == null) {
		   if (other.alternativa != null)
			   return false;
	   } else if (!alternativa.equals(other.alternativa))
		   return false;
	   return true;
   }

	
}
