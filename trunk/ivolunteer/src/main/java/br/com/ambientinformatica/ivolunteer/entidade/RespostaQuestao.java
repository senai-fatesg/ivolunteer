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
public class RespostaQuestao {
	
	@Id
	@GeneratedValue(generator="seq_respostaquestao", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="seq_respostaquestao", sequenceName="seq_respostaquestao", initialValue=1, allocationSize=1)
	private Integer id;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Questao questao;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="respostaquestao_id")
	private List<RespostaDiscursiva> discursivaRespostas;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="respostaquestao_id")
	private List<RespostaObjetiva> objetivaRespostas;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public List<RespostaDiscursiva> getDiscursivaRespostas() {
		return discursivaRespostas;
	}

	public void setDiscursivaRespostas(List<RespostaDiscursiva> discursivaRespostas) {
		this.discursivaRespostas = discursivaRespostas;
	}

	public List<RespostaObjetiva> getObjetivaRespostas() {
		return objetivaRespostas;
	}

	public void setObjetivaRespostas(List<RespostaObjetiva> objetivaRespostas) {
		this.objetivaRespostas = objetivaRespostas;
	}
	
	
	
	
}
