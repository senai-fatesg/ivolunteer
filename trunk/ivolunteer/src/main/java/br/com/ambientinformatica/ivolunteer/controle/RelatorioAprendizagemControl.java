package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.RelatorioAprendizagem;
import br.com.ambientinformatica.ivolunteer.persistencia.RealatorioAprendizagemDao;

@Controller("TurmaControl")
@Scope("conversation")
public class RelatorioAprendizagemControl {
	
	private RelatorioAprendizagem relatorioaprendizagem = new RelatorioAprendizagem();

	@Autowired
	private RealatorioAprendizagemDao relatorioaprendizagemDao;

	private List<RelatorioAprendizagem> relatoriosaprendizagems = new ArrayList<RelatorioAprendizagem>();

	@PostConstruct
	public void init() {
		listar(null);
	}

	public void confirmar(ActionEvent evt) {
		try {
			relatorioaprendizagemDao.alterar(relatorioaprendizagem);
			listar(evt);
			relatorioaprendizagem = new RelatorioAprendizagem();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt) {
		try {
			relatoriosaprendizagems = relatorioaprendizagemDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public RelatorioAprendizagem getRelatorioAprendizagem() {
		return relatorioaprendizagem;
	}

	public void setContato(RelatorioAprendizagem relatorioaprendizagem) {
		this.relatorioaprendizagem = relatorioaprendizagem;
	}

	public List<RelatorioAprendizagem> getRelatoriosAprendizagem() {
		return relatoriosaprendizagems;
	}
}
