package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;

import br.com.ambientinformatica.ivolunteer.entidade.EnumPrioridade;

import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.persistencia.PessoaDao;

@Controller("PesquisaSelecaoControl")
@Scope("conversation")
public class PesquisaSelecaoControl {

	private Pessoa pessoa = new Pessoa();

	private List<Pessoa> pessoas = new ArrayList<Pessoa>();

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	@Autowired
	private PessoaDao PessoaDao;

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	private static final String disable = "disable";
	private static final String enable = "enable";
	private String checkStatus = "disable";

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public boolean isCheckStatus() {
		return this.checkStatus.equalsIgnoreCase(enable) ? true : false;
	}

	public void confirmar(ActionEvent evt) {
		try {
			PessoaDao.alterar(pessoa);
			listar(evt);
			pessoa = new Pessoa();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void consultar(ActionEvent evt) {
		try {
			PessoaDao.consultar(pessoa.getId());
			pessoa = new Pessoa();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt) {
		try {
			pessoas = PessoaDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e.getMessage());
		}
	}

	public List<String> completeEnumPrioridade(String query) {
		List<String> retornoPrioridade = new ArrayList<String>();
		EnumPrioridade[] enumPrioridade = EnumPrioridade.values();
		for (int i = 0; i < enumPrioridade.length; i++) {
			retornoPrioridade.add(enumPrioridade[i].getDescricao());
		}
		return retornoPrioridade;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
