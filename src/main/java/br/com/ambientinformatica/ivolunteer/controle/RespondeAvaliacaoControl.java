package br.com.ambientinformatica.ivolunteer.controle;

import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.event.ActionEvent;

import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.panel.Panel;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Alternativa;
import br.com.ambientinformatica.ivolunteer.entidade.Avaliacao;
import br.com.ambientinformatica.ivolunteer.entidade.EnumQuestao;
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.entidade.Questao;
import br.com.ambientinformatica.ivolunteer.entidade.RespostaAvaliacao;
import br.com.ambientinformatica.ivolunteer.entidade.RespostaDiscursiva;
import br.com.ambientinformatica.ivolunteer.entidade.RespostaQuestao;
import br.com.ambientinformatica.ivolunteer.persistencia.AvaliacaoDao;
import br.com.ambientinformatica.ivolunteer.persistencia.PessoaDao;



@Controller("RespondeAvaliacaoControl")
@Scope("conversation")
public class RespondeAvaliacaoControl {
	private Avaliacao avaliacaoSelecionada = new Avaliacao();
	private Pessoa pessoaSelecionada = new Pessoa();
	private RespostaAvaliacao respostaAvaliacao = new RespostaAvaliacao();
	private Panel panelVisivel = new Panel();
	private String auxDiscursiva = new String();
	private static ExpressionFactory expressionFactory;
	
	@Autowired
	AvaliacaoDao avaliacaoDao;
	@Autowired
	PessoaDao pessoaDao;
	
	@PostConstruct
	public void init(){
		//this.panelVisivel.setVisible(false);
	}
	
	public List<Avaliacao> completaAvaliacoes (String query){
		return this.avaliacaoDao.listarTitulo(query);
	}
	
	public List<Pessoa> completaPessoas (String query){
		return this.pessoaDao.listarPorNome(query); 
	}

	public Avaliacao getAvaliacaoSelecionada() {
		return avaliacaoSelecionada;
	}

	public void setAvaliacaoSelecionada(Avaliacao avaliacaoSelecionada) {
		this.avaliacaoSelecionada = avaliacaoSelecionada;
	}

	public Pessoa getPessoaSelecionada() {
		return pessoaSelecionada;
	}

	public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
		this.pessoaSelecionada = pessoaSelecionada;
	}
	
	public void montaAvaliacaoView(ActionEvent evt){		
		this.avaliacaoSelecionada = avaliacaoDao.consultarAvalicaoCompleta(this.avaliacaoSelecionada);
		Application app = UtilFaces.getFacesContext().getApplication();
		ExpressionFactory expressionFactory = app.getExpressionFactory();
		ValueExpression ve = expressionFactory.createValueExpression("#{RespondeAvaliacaoControl.auxDiscursiva}", String.class);
		this.panelVisivel = new Panel();		
		this.panelVisivel.setHeader(this.avaliacaoSelecionada.getTitulo());
		this.respostaAvaliacao.setAvaliacao(this.avaliacaoSelecionada);
		this.respostaAvaliacao.setPessoa(pessoaSelecionada);
		this.respostaAvaliacao.setTitulo(this.avaliacaoSelecionada.getTitulo());
		this.respostaAvaliacao.setDescricao(this.avaliacaoSelecionada.getDescricao());
		OutputLabel descricao = new OutputLabel();
		descricao.setValue(this.avaliacaoSelecionada.getDescricao());
		this.panelVisivel.getChildren().add(descricao);
		for (Iterator iterator = this.avaliacaoSelecionada.getQuestoes().iterator(); iterator.hasNext();) {
			Questao questao = (Questao) iterator.next();
			RespostaQuestao respostaQuestao = new RespostaQuestao();
			respostaQuestao.setQuestao(questao);
			respostaQuestao.setOrdem(questao.getId());
			respostaQuestao.setPergunta(questao.getPergunta());
			respostaQuestao.setTipoQuestao(questao.getTipoQuestao());			
			OutputLabel outputLabel = new OutputLabel();			
			outputLabel.setValue(questao.getOrdem()+" - "+questao.getPergunta());
			this.panelVisivel.getChildren().add(outputLabel);
			if(questao.getTipoQuestao()==EnumQuestao.D){
				RespostaDiscursiva respostaDiscursiva = new RespostaDiscursiva();
				respostaDiscursiva.setDiscursiva(questao.getDiscursiva());
				InputTextarea textarea = new InputTextarea();				
				textarea.setStyle("width:100%;");				
				textarea.setValueExpression("value",ve);
				textarea.setId("auxDiscursiva1");
				respostaDiscursiva.setResposta(auxDiscursiva);				
				respostaQuestao.setRespostaDiscursiva(respostaDiscursiva);
				this.panelVisivel.getChildren().add(textarea);
				this.panelVisivel.getChildren().add(new OutputLabel());
				this.panelVisivel.getChildren().add(new OutputLabel());				
			} else {
				PanelGrid panelGrid = new PanelGrid();
				panelGrid.setColumns(6);				
				for (Iterator iterator2 = questao.getObjetiva().getAlternativas().iterator(); iterator2
						.hasNext();) {
					Alternativa alternativa = (Alternativa) iterator2.next();
					SelectBooleanCheckbox selectBooleanCheckbox = new SelectBooleanCheckbox();					
					panelGrid.getChildren().add(selectBooleanCheckbox);
					OutputLabel outLabel = new OutputLabel();					
					outLabel.setValue(alternativa.getDescricao());
					panelGrid.getChildren().add(outLabel);					
				}			
				this.panelVisivel.getChildren().add(panelGrid);
			}
			this.respostaAvaliacao.getQuestoes().add(respostaQuestao);			
		}		
		this.panelVisivel.setVisible(true);
	}

	public Panel getPanelVisivel() {		
		return panelVisivel;
	}

	public void setPanelVisivel(Panel panelVisivel) {
		this.panelVisivel = panelVisivel;
	}

	public RespostaAvaliacao getRespostaAvaliacao() {
		return respostaAvaliacao;
	}

	public void setRespostaAvaliacao(RespostaAvaliacao respostaAvaliacao) {
		this.respostaAvaliacao = respostaAvaliacao;
	}
	
	public void salvar (ActionEvent evnt){
		this.respostaAvaliacao.getDescricao();
	}

	public String getAux() {
		return auxDiscursiva;
	}

	public void setAux(String auxDiscursiva) {
		this.auxDiscursiva = auxDiscursiva;
	}
	
	
}
