package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Questao {
	
	@Id
	@GeneratedValue(generator = "questao_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "questao_seq", sequenceName = "questao_seq", allocationSize = 1, initialValue = 1)
	private Integer id;

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

	
	
	

}
