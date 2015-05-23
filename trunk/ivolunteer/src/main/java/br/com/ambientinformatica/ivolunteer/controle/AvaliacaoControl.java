package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Avaliacao;
import br.com.ambientinformatica.ivolunteer.entidade.Discursiva;
import br.com.ambientinformatica.ivolunteer.entidade.EnumQuestao;
import br.com.ambientinformatica.ivolunteer.entidade.Questao;
import br.com.ambientinformatica.ivolunteer.persistencia.AvaliacaoDao;


@Controller("AvaliacaoControl")
//@ManagedBean
@Scope("conversation")
public class AvaliacaoControl {

	private Avaliacao avaliacao = new Avaliacao();
	private Questao questao = new Questao();
	private Discursiva discursiva = new Discursiva();
	
	private EnumQuestao tipoQuestaoSelecionada;	

	@Autowired
	private AvaliacaoDao avaliacaoDao;
	

	public Avaliacao getAvaliacao() {
		return this.avaliacao;		
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
		
	}	

	public void confirmar(ActionEvent evt) {
		try {
			if (tipoQuestaoSelecionada == null) {
				throw new Exception("Parametro Invalido");
			}
			avaliacaoDao.alterar(avaliacao);
			avaliacao = new Avaliacao();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public List<String> completeEnumTipoQuestao(String query){
		List<String> retorno = new ArrayList<String>();
		EnumQuestao[] enunsQuestao = EnumQuestao.values();
		for (int i = 0; i < enunsQuestao.length; i++) {
			retorno.add(enunsQuestao[i].getDescricao());			
		}
		return retorno;
	}

	public EnumQuestao getTipoQuestaoSelecionada() {
		return tipoQuestaoSelecionada;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public void setTipoQuestaoSelecionada(EnumQuestao tipoQuestaoSelecionada) {
		this.tipoQuestaoSelecionada = tipoQuestaoSelecionada;
	}

	public Discursiva getDiscursiva() {
		return discursiva;
	}

	public void setDiscursiva(Discursiva discursiva) {
		this.discursiva = discursiva;
				
	}	

	
}
