package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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
import br.com.ambientinformatica.ivolunteer.persistencia.AlternativaDao;
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

	private List<Avaliacao> listaAvaliacoesCompleto = new ArrayList<Avaliacao>();
	private List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();

	@Autowired
	private AlternativaDao alternativaDao;
	
	@Autowired
	private AvaliacaoDao avaliacaoDao;

	@Autowired
	private QuestaoDao questaoDao;
	
	private boolean desabilitaTipoQuestao;
	
	@PostConstruct
	public void init(){
		listarTodasAvaliacoes();
	}
	
	public void listarTodasAvaliacoesCompleto(){
		this.avaliacoes = avaliacaoDao.listar();
	}
	
	public void listarTodasAvaliacoes(){
		//this.avaliacoes = avaliacaoDao.listarAvaliacoesAtivas();
		this.avaliacoes = avaliacaoDao.listar();
	}

	// Insere Alternativas em Questao do tipo Objetiva
	public void addAlternativa(ActionEvent ev) {
		if(validaAlternativa()) {
			this.objetiva.addAlternativa(alternativa);
			this.alternativa = new Alternativa();
			UtilFaces.addMensagemFaces("Alternativa adicionada.");
		}
	}
	
	public boolean validaAlternativa() {
		if(!this.alternativa.getDescricao().isEmpty()) {
			return true;
		} else {
			UtilFaces.addMensagemFaces("Descrição da alternativa é obrigatória!");
			return false;
		}
	}

	public void desativar(Avaliacao avaliacao) {
		avaliacao.desativa();
		this.avaliacaoDao.alterar(avaliacao);
		listarTodasAvaliacoes();
	}

	// Obtem Registro para Alteração
	public void carregaAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = this.avaliacaoDao.consultarAvalicaoCompleta(avaliacao);
		if(this.avaliacao.getQuestoes().size() == 0) {
			this.questao.setOrdem(this.avaliacao.getQuestoes().size());
		} else {
			this.questao.setOrdem(this.avaliacao.getQuestoes().size() + 1);
		}
	}

	// Remove Questao em Avaliacao
	public void remAlternativa(Alternativa alternativa) {
		try {
			this.objetiva.remAlternativa(alternativa);
			alternativaDao.excluirPorId(alternativa.getId());
			UtilFaces.addMensagemFaces("Alternativa removida.");
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
			//Questao quest = questaoDao.consultar(questao.getId());
			questaoDao.excluirPorId(questao.getId());
			UtilFaces.addMensagemFaces("Questão removida.");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void ordenaNovasQuestoes() {
		//System.out.println("Alterou ordem da questão!!!");
		//System.out.println("ORDENAMENTO DE NÚMERO :" + this.questao.getOrdem());
		if((this.questao.getOrdem() <= this.avaliacao.getQuestoes().size()) && this.questao.getId() == null) {
			for (Questao questao : this.avaliacao.getQuestoes()) {
				if (this.questao.getOrdem() == questao.getOrdem()) {
					//this.questao.setOrdem(questao.getOrdem());
					questao.setOrdem(this.avaliacao.getQuestoes().size() + 1);
					questaoDao.alterar(this.avaliacao.getQuestoes());
				}
			}
		} 
	}
	
	public void ordenaAtualizacoesQuestoes() {
		if((this.questao.getOrdem() <= this.avaliacao.getQuestoes().size()) && this.questao.getId() != null) {
			for (Questao questao : this.avaliacao.getQuestoes()) {
				if (this.questao.getOrdem() == questao.getOrdem()) {
					Questao quest = questaoDao.consultar(this.questao.getId());
					Integer ordemOriginal = quest.getOrdem();
					//this.questao.setOrdem(questao.getOrdem());
					questao.setOrdem(ordemOriginal);
					questaoDao.alterar(this.avaliacao.getQuestoes());
				}
			}
		}
	}
	
	// Inclui questao a avaliacao
	public void addQuestao(ActionEvent event) {
		if(validaQuestao() && validaOrdemQuestao()) {
			this.questao.setTipoQuestao(tipoQuestao);
			if (this.tipoQuestao.equals(EnumQuestao.D)) {
				this.questao.setDiscursiva(this.discursiva);
				this.discursiva = new Discursiva();
			} else {
				this.questao.setObjetiva(objetiva);
				this.objetiva = new Objetiva();
			}
			ordenaNovasQuestoes();
			UtilFaces.addMensagemFaces("Questão adicionada.");
			this.avaliacao.addQuestao(this.questao);
			this.questao = new Questao();
		}
	}
	
	public boolean validaOrdemQuestao() {
		if((this.questao.getOrdem() > this.avaliacao.getQuestoes().size() + 1) && this.questao.getId() == null) {
			UtilFaces.addMensagemFaces("Número de ordenação da questão inválida. "
					+ "Questão atual pode receber número de ordenação máximo de "
					+ (this.avaliacao.getQuestoes().size() + 1) + "!");
			return false;
		} else if ((this.questao.getOrdem() > this.avaliacao.getQuestoes().size()) && this.questao.getId() != null) {
			UtilFaces.addMensagemFaces("Número de ordenação da questão inválida. "
					+ "Questão atual pode receber número de ordenação máximo de "
					+ this.avaliacao.getQuestoes().size() + "!");
			return false;
		} else return true;
	}
	
	public boolean validaQuestao() {
		if(!this.questao.getPergunta().isEmpty()) {
			return true;
		} else {
			UtilFaces.addMensagemFaces("Pergunta da questão é obrigatória!");
			return false;
		}
	}
	
	public void attQuestao() {
		if(validaQuestao() && validaOrdemQuestao()) {
			ordenaAtualizacoesQuestoes();
			questaoDao.alterar(this.questao);
			this.avaliacao = avaliacaoDao.consultarAvalicaoCompleta(this.avaliacao);
			this.objetiva = new Objetiva();
			this.questao = new Questao();
			habilitarEditarTipoQuestao();
			UtilFaces.addMensagemFaces("Questão atualizada com sucesso!");
		}
	}
	
	public void habilitarEditarTipoQuestao() {
		this.desabilitaTipoQuestao = false;
	}
	
	public void desabilitaEditarTipoQuestao() {
		this.desabilitaTipoQuestao = true;
		System.out.println("DESABILITADO? " + this.desabilitaTipoQuestao);
	}

	public void editarQuestao(Questao questao) {
		this.desabilitaEditarTipoQuestao();
		if(questao.getId() != null) {
			this.questao = questaoDao.consultar(questao.getId());
			this.tipoQuestao = this.questao.getTipoQuestao();
			if(tipoQuestao == EnumQuestao.O) {
				this.objetiva.setAlternativas(this.questao.getObjetiva().getAlternativas());
			}
		} else {
			this.questao = questao;
			this.tipoQuestao = this.questao.getTipoQuestao();
			if(tipoQuestao == EnumQuestao.O) {
				this.objetiva.setAlternativas(this.questao.getObjetiva().getAlternativas());
			}
			this.avaliacao.remQuestao(questao);
		}
	}
	
	public void cadastrarAvaliacao(){
		try {
			if(avaliacaoValida()) {
				System.out.println("ANTES DE CADASTRAR | QTD AVALIACAO: " + this.avaliacao.getQuestoes().size());
				avaliacaoDao.incluir(this.avaliacao);
				System.out.println("DEPOIS DE CADASTRAR | QTD AVALIACAO: " + this.avaliacao.getQuestoes().size());
				this.avaliacao = new Avaliacao();
				this.questao = new Questao();
				UtilFaces.addMensagemFaces("Avaliação cadastrada com sucesso!");
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public boolean avaliacaoValida(){
		if(this.avaliacao.getTitulo().isEmpty()) {
			UtilFaces.addMensagemFaces("Título de avaliação é obrigatório!");
			return false;
		} else if (this.avaliacao.getDescricao().isEmpty()){
			UtilFaces.addMensagemFaces("Descrição de avaliação é obrigatório!");
			return false;
		}
		return true;
	}
	// Salvar Avaliacao
	public void salvarAvaliacao() {
		try {
			if(avaliacaoValida()) {
				this.avaliacaoDao.alterar(this.avaliacao);	
				//this.questaoDao.alterar(this.avaliacao.getQuestoes());
				this.avaliacao = new Avaliacao();
				UtilFaces.addMensagemFaces("Avaliação atualizada com sucesso!");
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	// Aplica Filtro
	public void aplicarFiltro(ActionEvent evt) {
		try {
			if (this.filtro.getTitulo().isEmpty()) {
				this.avaliacoes = this.avaliacaoDao.listarAvaliacoesAtivas();
			} else {
				this.avaliacoes = this.avaliacaoDao.listarAvaliacoesPorTitulo(this.filtro);
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

	public List<Avaliacao> getListaAvaliacoesCompleto() {
		return listaAvaliacoesCompleto;
	}

	public void setListaAvaliacoesCompleto(List<Avaliacao> listaAvaliacoesCompleto) {
		this.listaAvaliacoesCompleto = listaAvaliacoesCompleto;
	}

	public boolean isDesabilitaTipoQuestao() {
		return desabilitaTipoQuestao;
	}

	public void setDesabilitaTipoQuestao(boolean desabilitaTipoQuestao) {
		this.desabilitaTipoQuestao = desabilitaTipoQuestao;
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
