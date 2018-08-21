package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.component.tabview.Tab;
import org.primefaces.event.TabChangeEvent;
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
import br.com.ambientinformatica.ivolunteer.persistencia.QuestaoDao;

@Controller("AvaliacaoControl")
@Scope("conversation")
public class AvaliacaoControl {

	private Avaliacao avaliacao = new Avaliacao();
	private Questao questao = new Questao();
	private Objetiva objetiva = new Objetiva();
	private Discursiva discursiva = new Discursiva();
	private EnumQuestao tipoQuestao = EnumQuestao.D;
	private Alternativa alternativa = new Alternativa();
	private Avaliacao filtro = new Avaliacao();

	private List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();

	@Autowired
	private AvaliacaoDao avaliacaoDao;

	@Autowired
	private QuestaoDao questaoDao;

	// Insere Alternativas em Questao do tipo Objetiva
	public void addAlternativa(ActionEvent ev) {
		try {
			this.objetiva.addAlternativa(alternativa);
			this.alternativa = new Alternativa();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void excluir(Avaliacao avaliacao) {
		this.avaliacaoDao.removerAvaliacaoCompleta(avaliacao);
		this.avaliacao = new Avaliacao();
	}

	// Obtem Registro para Alteração
	public void carregaAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = this.avaliacaoDao.consultarAvalicaoCompleta(avaliacao);
	}

	// Remove Questao em Avaliacao
	public void remAlternativa(Alternativa alternativa) {
		try {
			this.objetiva.remAlternativa(alternativa);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void validaTitulo() {
		if (this.avaliacao.getTitulo().length() < 2) {
			UtilFaces.addMensagemFaces("Campo Título está Inválido",
					FacesMessage.SEVERITY_WARN);
		}
	}

	// Atualizar em qual tab o Usuário está
	public void alteraTab(TabChangeEvent event) {
		Tab tabAtual = event.getTab();
		if (tabAtual.getId().equals("tabDiscursiva")) {
			this.tipoQuestao = EnumQuestao.D;
		} else {
			this.tipoQuestao = EnumQuestao.O;
		}
	}

	// Remove a Questão
	public void remQuestao(Questao questao) {
		try {
			this.avaliacao.remQuestao(questao);
			questaoDao.excluirPorId(questao.getId());
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	// Inclui questao a avaliacao
	public void addQuestao(ActionEvent event) {
		//this.questao.setTipoQuestao(tipoQuestao);
		System.out.println("ANTES DO IF | QTD AVALIACAO: " + this.avaliacao.getQuestoes().size());
		if (this.questao.getTipoQuestao().equals(EnumQuestao.D)) {
			this.questao.setDiscursiva(this.discursiva);
			this.discursiva = new Discursiva();
			System.out.println("DEPOIS DO IF | QTD AVALIACAO: " + this.avaliacao.getQuestoes().size());
		} else {
			this.questao.setObjetiva(objetiva);
			this.objetiva = new Objetiva();
			System.out.println("FIM DO ELSE | QTD AVALIACAO: " + this.avaliacao.getQuestoes().size());
		}
		this.avaliacao.addQuestao(this.questao);
		this.questao = new Questao();
		System.out.println("FIM ADD QUESTAO | QTD AVALIACAO: " + this.avaliacao.getQuestoes().size());
	}

	public void cadastrarAvaliacao(){
		try {
			System.out.println("ANTES DE CADASTRAR | QTD AVALIACAO: " + this.avaliacao.getQuestoes().size());
			avaliacaoDao.incluir(this.avaliacao);
			System.out.println("DEPOIS DE CADASTRAR | QTD AVALIACAO: " + this.avaliacao.getQuestoes().size());
			this.avaliacao = new Avaliacao();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	// Salvar Avaliacao
	public void salvarAvaliacao() {
		try {
			this.avaliacaoDao.alterar(this.avaliacao);	
			
			this.questaoDao.alterar(this.avaliacao.getQuestoes());
			this.avaliacao = new Avaliacao();
			UtilFaces.addMensagemFaces("Avaliação salva com sucesso");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	// Aplica Filtro
	public void aplicarFiltro(ActionEvent evt) {
		try {
			if (this.filtro.getTitulo().isEmpty()) {
				this.avaliacoes = this.avaliacaoDao.listar();
			} else {
				this.avaliacoes = this.avaliacaoDao.listarTitulo(this.filtro);
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}

	}

	public void verificaTitulo() {
		if (this.avaliacao.getTitulo().length() < 2) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(
							"Titulo deve conter no minimo 2 caracteres"));
		}
	}

	public List<SelectItem> getCompleteEnumQuestao(){
		return UtilFaces.getListEnum(EnumQuestao.values());
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

	public List<Avaliacao> getAvaliacoes() {
		this.avaliacoes = avaliacaoDao.listar();
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public Avaliacao getFiltro() {
		return filtro;
	}

	public void setFiltro(Avaliacao filtro) {
		this.filtro = filtro;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
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

	public void setDiscursiva(Discursiva discursiva) {
		this.discursiva = discursiva;
	}

	public EnumQuestao getTipoQuestao() {
		return tipoQuestao;
	}

	public void setTipoQuestao(EnumQuestao tipoQuestao) {
		this.tipoQuestao = tipoQuestao;
	}

}
