package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Frequencia;
import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;
import br.com.ambientinformatica.ivolunteer.persistencia.FrequenciaDao;

@Controller("FrequenciaControl")
@Scope("conversation")
public class FrequenciaControl {

	private Funcionario funcionario = new Funcionario();
	private Frequencia frequencia = new Frequencia();

	@Autowired
	private FrequenciaDao frequenciaDao;

	private List<Frequencia> frequencias = new ArrayList<Frequencia>();

	@PostConstruct
	public void init() {
		listar(null);
	}

	public void confirmar(ActionEvent evt) {
		try {
			frequenciaDao.alterar(frequencia);
			listar(evt);
			frequencia = new Frequencia();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt) {
		try {
			frequencias = frequenciaDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public Frequencia getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(Frequencia frequencia) {
		this.frequencia = frequencia;
	}

	public List<Frequencia> getFrequencias() {
		return frequencias;
	}
}
