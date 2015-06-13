package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class RespostaAvaliacao {
	
	@Id
	@GeneratedValue(generator="seq_repsotaavaliacao", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="seq_repsotaavaliacao", sequenceName="seq_repsotaavaliacao", initialValue=1, allocationSize=1)
	private Integer id;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Pessoa pessoa;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Avaliacao avaliacao;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="respostaavaliacao_id")
	private List<RespostaQuestao> questoes;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date resposta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public List<RespostaQuestao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<RespostaQuestao> questoes) {
		this.questoes = questoes;
	}
	
	
}
