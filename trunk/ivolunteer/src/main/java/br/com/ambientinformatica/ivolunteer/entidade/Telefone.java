package br.com.ambientinformatica.ivolunteer.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Telefone {

	@Id
   @GeneratedValue(generator="telefone_seq", strategy=GenerationType.SEQUENCE)
   @SequenceGenerator(name="telefone_seq", sequenceName="telefone_seq", allocationSize=1, initialValue=1)
   private Integer id;
	
	private EnumTipoTelefone enumTipoTelefone;
	private String numeroTelefone;
	private String nomePessoaRecado;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNumeroTelefone() {
		return numeroTelefone;
	}
	
	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public String getNomePessoaRecado() {
		return nomePessoaRecado;
	}

	public void setNomePessoaRecado(String nomePessoaRecado) {
		this.nomePessoaRecado = nomePessoaRecado;
	}

	public EnumTipoTelefone getEnumTipoTelefone() {
		return enumTipoTelefone;
	}

	public void setEnumTipoTelefone(EnumTipoTelefone enumTipoTelefone) {
		this.enumTipoTelefone = enumTipoTelefone;
	}

	@Override
   public int hashCode() {
	   final int prime = 31;
	   int result = 1;
	   result = prime * result
	         + ((enumTipoTelefone == null) ? 0 : enumTipoTelefone.hashCode());
	   result = prime * result + ((id == null) ? 0 : id.hashCode());
	   result = prime * result
	         + ((nomePessoaRecado == null) ? 0 : nomePessoaRecado.hashCode());
	   result = prime * result
	         + ((numeroTelefone == null) ? 0 : numeroTelefone.hashCode());
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
	   Telefone other = (Telefone) obj;
	   if (enumTipoTelefone != other.enumTipoTelefone)
		   return false;
	   if (id == null) {
		   if (other.id != null)
			   return false;
	   } else if (!id.equals(other.id))
		   return false;
	   if (nomePessoaRecado == null) {
		   if (other.nomePessoaRecado != null)
			   return false;
	   } else if (!nomePessoaRecado.equals(other.nomePessoaRecado))
		   return false;
	   if (numeroTelefone == null) {
		   if (other.numeroTelefone != null)
			   return false;
	   } else if (!numeroTelefone.equals(other.numeroTelefone))
		   return false;
	   return true;
   }
}
