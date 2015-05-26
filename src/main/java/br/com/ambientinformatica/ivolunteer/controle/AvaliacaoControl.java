package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Alternativa;
import br.com.ambientinformatica.ivolunteer.entidade.Avaliacao;
import br.com.ambientinformatica.ivolunteer.entidade.Discursiva;
import br.com.ambientinformatica.ivolunteer.entidade.EnumQuestao;
import br.com.ambientinformatica.ivolunteer.entidade.Objetiva;
import br.com.ambientinformatica.ivolunteer.entidade.Questao;
import br.com.ambientinformatica.ivolunteer.persistencia.AvaliacaoDao;

@Controller("AvaliacaoControl")
@Scope("conversation")
public class AvaliacaoControl {

	private Avaliacao avaliacao = new Avaliacao();
	private Questao questao = new Questao();
	private Discursiva discursiva = new Discursiva();
	private List<Alternativa> alternativas = new ArrayList<Alternativa>();
	private Objetiva objetiva = new Objetiva();
	private Alternativa alternativa = new Alternativa();	

	@Autowired
	private AvaliacaoDao avaliacaoDao;	

	public void adicionarQuestaoDiscursiva(ActionEvent evt) {
		try {
			this.questao.setDiscursiva(this.discursiva);
			this.avaliacao.addQuestao(this.questao);
			this.questao = null;
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void addAlternativaQuestao(ActionEvent ev) {
		try {
			this.objetiva.addAlternativa(alternativa);
			this.alternativa = new Alternativa();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void removerAlterntaivaQuetao(Alternativa alternativa){
		try {
			this.objetiva.removerAlternativa(alternativa);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public Objetiva getObjetiva() {
		return objetiva;
	}

	public void setObjetiva(Objetiva objetiva) {
		this.objetiva = objetiva;
	}

	public Alternativa getAlternativa() {
		return alternativa;
	}

	public void setAlternativa(Alternativa alternativa) {
		this.alternativa = alternativa;
	}

	public Avaliacao getAvaliacao() {
		return this.avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;

	}	
	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}	

	public Discursiva getDiscursiva() {
		return discursiva;
	}

	public void setDiscursiva(Discursiva discursiva) {
		this.discursiva = discursiva;

	}
}
