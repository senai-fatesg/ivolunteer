package br.com.ambientinformatica.ivolunteer.controle;

import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;









import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;

import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.panel.Panel;
import org.primefaces.component.selectmanycheckbox.SelectManyCheckbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ivolunteer.entidade.Alternativa;
import br.com.ambientinformatica.ivolunteer.entidade.Avaliacao;
import br.com.ambientinformatica.ivolunteer.entidade.EnumQuestao;
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.entidade.Questao;
import br.com.ambientinformatica.ivolunteer.persistencia.AvaliacaoDao;
import br.com.ambientinformatica.ivolunteer.persistencia.PessoaDao;



@Controller("RespondeAvaliacaoControl")
@Scope("conversation")
public class RespondeAvaliacaoControl {
	private Avaliacao avaliacaoSelecionada = new Avaliacao();
	private Pessoa pessoaSelecionada = new Pessoa();
	private Panel panelVisivel = new Panel();
	
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
	
	public void montaAvaliacaoView(){
		this.avaliacaoSelecionada = avaliacaoDao.consultarAvalicaoCompleta(this.avaliacaoSelecionada);
		this.panelVisivel.setHeader(this.avaliacaoSelecionada.getTitulo());
		OutputLabel descricao = new OutputLabel();
		descricao.setValue(this.avaliacaoSelecionada.getDescricao());
		this.panelVisivel.getChildren().add(descricao);
		for (Iterator iterator = this.avaliacaoSelecionada.getQuestoes().iterator(); iterator.hasNext();) {
			Questao questao = (Questao) iterator.next();
			OutputLabel outputLabel = new OutputLabel();
			outputLabel.setValue(questao.getOrdem()+" - "+questao.getPergunta());
			this.panelVisivel.getChildren().add(outputLabel);
			if(questao.getTipoQuestao()==EnumQuestao.D){
				InputTextarea textarea = new InputTextarea();
				textarea.setStyle("width:100%;");
				this.panelVisivel.getChildren().add(textarea);
			} else {				
				for (Iterator iterator2 = questao.getObjetiva().getAlternativas().iterator(); iterator2
						.hasNext();) {
					Alternativa alternativa = (Alternativa) iterator2.next();
					SelectManyCheckbox selectManyCheckbox = new SelectManyCheckbox();
					selectManyCheckbox.setValue(alternativa.getDescricao());
					this.panelVisivel.getChildren().add(selectManyCheckbox);
				}
			}
		}
		
		
		this.panelVisivel.setVisible(true);
	}

	public Panel getPanelVisivel() {		
		return panelVisivel;
	}

	public void setPanelVisivel(Panel panelVisivel) {
		this.panelVisivel = panelVisivel;
	}
	
		
}
