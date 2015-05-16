package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;
import br.com.ambientinformatica.ivolunteer.persistencia.FuncionarioDao;

@Controller("FuncionarioControl")
@Scope("conversation")
public class FuncionarioControl {

	Funcionario funcionario = new Funcionario();

	@Autowired
	private FuncionarioDao funcionarioDao;

	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();

	public void confirmar(ActionEvent evt) {
		try {
			funcionarioDao.alterar(funcionario);
			funcionario = new Funcionario();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt) {
		try {
			funcionarios = funcionarioDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

}
