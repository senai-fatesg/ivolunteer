package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.persistencia.PessoaDao;

@Controller("PesquisaSelecaoControl")
@Scope("conversation")
public class PesquisaSelecaoControl {
	private Pessoa pessoa = new Pessoa();
   
	@Autowired
	private PessoaDao PessoaDao;

	private List<Pessoa> candidatos = new ArrayList<Pessoa>();

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
			candidatos = PessoaDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e.getMessage());
		}
	}
	
}
