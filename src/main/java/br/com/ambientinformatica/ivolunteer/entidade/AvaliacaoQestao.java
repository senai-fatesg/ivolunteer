package br.com.ambientinformatica.ivolunteer.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class AvaliacaoQestao {
	
	@Id
	@GeneratedValue(generator="avaliacaoquestao_seq", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="avaliacaoquestao_seq", sequenceName="avaliacaoquestao_seq", allocationSize=1,initialValue=1)
	private Integer id;

	private int ordem;
	
	@OneToOne
	private Questao questao;
	
	@OneToOne
	private Avaliacao avaliacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}	

}
