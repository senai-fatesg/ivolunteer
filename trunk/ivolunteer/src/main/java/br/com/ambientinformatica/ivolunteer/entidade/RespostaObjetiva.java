package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class RespostaObjetiva {
	
	@Id
	@GeneratedValue(generator="seq_objetivaresposta", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="seq_objetivaresposta", sequenceName="seq_objetivaresposta", allocationSize=1, initialValue=1)
	private Integer id;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Objetiva objetiva;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="respostaobjetiva_id")
	private List<RespoastaAlternativa> alternativas;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Objetiva getObjetiva() {
		return objetiva;
	}

	public void setObjetiva(Objetiva objetiva) {
		this.objetiva = objetiva;
	}

	public List<RespoastaAlternativa> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(List<RespoastaAlternativa> alternativas) {
		this.alternativas = alternativas;
	}
	
}
