package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.AgrupamentoTurma;
import br.com.ambientinformatica.ivolunteer.persistencia.AgrupamentoTurmaDao;

@Controller("AgrupamentoTurmaControl")
@Scope("conversation")
public class AgrupamentoTurmaControl {

	private AgrupamentoTurma agrupamentoTurma = new AgrupamentoTurma();

	@Autowired
	private AgrupamentoTurmaDao agrupamentoTurmaDao;

	private List<AgrupamentoTurma> agrupamentoTurmas = new ArrayList<AgrupamentoTurma>();

	@PostConstruct
	public void init() {
		listar(null);
	}

	public void confirmar(ActionEvent evt) {
		try {
			agrupamentoTurmaDao.alterar(agrupamentoTurma);
			listar(evt);
			agrupamentoTurma = new AgrupamentoTurma();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt) {
		try {
			agrupamentoTurmas = agrupamentoTurmaDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public AgrupamentoTurma getAgrupamentoTurma() {
		return agrupamentoTurma;
	}

	public void setAgrupamentoTurma(AgrupamentoTurma agrupamentoTurma) {
		this.agrupamentoTurma = agrupamentoTurma;
	}

	public List<AgrupamentoTurma> getAgrupamentoTurmas() {
		return agrupamentoTurmas;
	}
	
	// Aplica Filtro por identificador
		public void aplicarFiltro(ActionEvent evt) {
			try {
				if (this.agrupamentoTurma.getIdentificador().isEmpty()) {
					this.agrupamentoTurmas = this.agrupamentoTurmaDao.listar();
				} else {
					this.agrupamentoTurmas = this.agrupamentoTurmaDao.listarIdentificador(this.agrupamentoTurma);
				}
			} catch (Exception e) {
				UtilFaces.addMensagemFaces(e);
			}

		}
	
}
