package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Questao {
	
	@Id
	@GeneratedValue(generator = "questao_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "questao_seq", sequenceName = "questao_seq", allocationSize = 1, initialValue = 1)
	private Integer id;
	
	private int ordem;

	private String pergunta;	

	@OneToMany
	private List<Objetiva> objetiva;

	@OneToOne
	private Discursiva discursiva;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}	
	
	public Discursiva getDiscursiva() {
		return discursiva;
	}

	public void setDiscursivas(Discursiva discursiva) {
		this.discursiva = discursiva;
	}

	public List<Objetiva> getObjetiva() {
		return objetiva;
	}

	public void setObjetiva(List<Objetiva> objetiva) {
		this.objetiva = objetiva;
	}

	public void setDiscursiva(Discursiva discursiva) {
		this.discursiva = discursiva;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	@Override
   public int hashCode() {
	   final int prime = 31;
	   int result = 1;
	   result = prime * result + ordem;
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
	   Questao other = (Questao) obj;
	   if (ordem != other.ordem)
		   return false;
	   return true;
   }
	

}
