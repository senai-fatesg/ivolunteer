package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIColumn;
import javax.faces.event.ActionEvent;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.tabview.Tab;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Alternativa;
import br.com.ambientinformatica.ivolunteer.entidade.Avaliacao;
import br.com.ambientinformatica.ivolunteer.entidade.Discursiva;
import br.com.ambientinformatica.ivolunteer.entidade.Objetiva;
import br.com.ambientinformatica.ivolunteer.entidade.Questao;
import br.com.ambientinformatica.ivolunteer.entidade.EnumQuestao;
import br.com.ambientinformatica.ivolunteer.persistencia.AvaliacaoDao;


@Controller("AvaliacaoControl")
@Scope("conversation")
public class AvaliacaoControl {

	private Avaliacao avaliacao = new Avaliacao();	
	private Discursiva discursiva = new Discursiva();	
	private Objetiva objetiva = new Objetiva();
	private EnumQuestao tipoQuestao = EnumQuestao.D;
	private Alternativa alternativa = new Alternativa();

//	@Autowired
//	private AvaliacaoDao avaliacaoDao;
	@Autowired
	private Alternativa alternativaDao;
	
	
	
	//Insere Alternativas em Questao do tipo Objetiva 
	public void addAlternativa(ActionEvent ev) {
		try {
			this.objetiva.addAlternativa(alternativa);			
			this.alternativa = new Alternativa();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}	
	//Remove Questao em Avaliacao
	public void remAlternativa(Alternativa alternativa) {
		try {
			this.objetiva.remAlternativa(alternativa);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	//Atualizar em qual tab o Usuário está
	public void alteraTab(TabChangeEvent event){
		Tab tabAtual = event.getTab();		
		if(tabAtual.getId().equals("tabDiscursiva")){
			this.tipoQuestao = EnumQuestao.D;
		} else {
			this.tipoQuestao = EnumQuestao.O;
		}
	}
	
	//Remove a Questão
	public void remQuestao(Object objeto){
		try {
			this.avaliacao.remQuestao(objeto);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	
	//Inclui questao a avaliacao
	public void addQuestao(ActionEvent event){
		if(this.tipoQuestao == EnumQuestao.D){			
			this.discursiva.setTipoQuestao(this.tipoQuestao);
			this.avaliacao.addQuestao(this.discursiva);
		} else {						
			this.objetiva.setTipoQuestao(this.tipoQuestao);
			this.avaliacao.addQuestao(this.objetiva);
		}
		this.objetiva = new Objetiva();
		this.discursiva = new Discursiva();
	}
	
	//Salvar Avaliacao
	public void salvar(ActionEvent event){
		try {
			this.alternativaDao.
			this.avaliacao = new Avaliacao();
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
	
	
	
	public Discursiva getDiscursiva() {
		return discursiva;
	}

	public void setDiscursiva(Discursiva discursiva) {
		this.discursiva = discursiva;

	}
}
