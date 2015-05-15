package br.com.ambientinformatica.ivolunteer.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Questao {
	
	@Id
	@GeneratedValue(generator = "questao_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "questao_seq", sequenceName = "questao_seq", allocationSize = 1, initialValue = 1)
	private Integer id;

	private String pergunta;

	private Avaliacao avaliacao;

	private Objetiva objetiva;

	private Discursiva discursiva;

	private Questao questao;

	private AvaliacaoQestao avaliacaoQestao;

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

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Objetiva getObjetiva() {
		return objetiva;
	}

	public void setObjetiva(Objetiva objetiva) {
		this.objetiva = objetiva;
	}

	public Discursiva getDiscursiva() {
		return discursiva;
	}

	public void setDiscursivas(Discursiva discursiva) {
		this.discursiva = discursiva;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public AvaliacaoQestao getAvaliacaoQestao() {
		return avaliacaoQestao;
	}

	public void setAvaliacaoQestao(AvaliacaoQestao avaliacaoQestao) {
		this.avaliacaoQestao = avaliacaoQestao;
	}
	
	

}
