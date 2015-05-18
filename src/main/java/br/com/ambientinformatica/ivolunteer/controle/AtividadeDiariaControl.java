package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.AtividadeDiaria;
import br.com.ambientinformatica.ivolunteer.persistencia.AtividadeDiariaDao;

@Controller("AtividadeDiariaControl")
@Scope("conversation")
public class AtividadeDiariaControl {
	private AtividadeDiaria atividadeDiaria = new AtividadeDiaria();

	@Autowired
	private AtividadeDiariaDao atividaDiariaDao;

	private List<AtividadeDiaria> atividadesDiarias = new ArrayList<AtividadeDiaria>();

	@PostConstruct
	public void init() {
		listar(null);
	}

	public void confirmar(ActionEvent evt) {
		try {
			atividaDiariaDao.alterar(atividadeDiaria);
			listar(evt);
			atividadeDiaria = new AtividadeDiaria();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt) {
		try {
			atividadesDiarias = atividaDiariaDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public AtividadeDiaria getAtividadeDiaria() {
		return atividadeDiaria;
	}

	public void setAtividadeDiaria(AtividadeDiaria atividadeDiaria) {
		this.atividadeDiaria = atividadeDiaria;
	}

	public List<AtividadeDiaria> getatividadesDiarias() {
		return atividadesDiarias;
	}
}
