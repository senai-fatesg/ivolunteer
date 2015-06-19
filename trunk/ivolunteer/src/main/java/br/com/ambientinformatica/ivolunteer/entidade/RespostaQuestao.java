package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	@GeneratedValue(generator = "seq_respostaquestao", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_respostaquestao", sequenceName = "seq_respostaquestao", initialValue = 1, allocationSize = 1)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	private Questao questao;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
	private RespostaDiscursiva respostaDiscursiva;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
	private RespostaObjetiva respostaObjetiva;

	private Integer ordem;
	private String pergunta;

	@Enumerated(EnumType.STRING)
	private EnumQuestao tipoQuestao;

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

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public EnumQuestao getTipoQuestao() {
		return tipoQuestao;
	}

	public void setTipoQuestao(EnumQuestao tipoQuestao) {
		this.tipoQuestao = tipoQuestao;
	}

	public RespostaDiscursiva getRespostaDiscursiva() {
		return respostaDiscursiva;
	}

	public void setRespostaDiscursiva(RespostaDiscursiva respostaDiscursiva) {
		this.respostaDiscursiva = respostaDiscursiva;
	}

	public RespostaObjetiva getRespostaObjetiva() {
		return respostaObjetiva;
	}

	public void setRespostaObjetiva(RespostaObjetiva respostaObjetiva) {
		this.respostaObjetiva = respostaObjetiva;
	}

	

}
