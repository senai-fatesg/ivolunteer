package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.AtividadeDiaria;
import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;
import br.com.ambientinformatica.ivolunteer.persistencia.AtividadeDiariaDao;
import br.com.ambientinformatica.ivolunteer.persistencia.FuncionarioDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Controller("AtividadeDiariaControl")
@Scope("conversation")
public class AtividadeDiariaControl {
	private AtividadeDiaria atividadeDiaria = new AtividadeDiaria();
	private Funcionario funcionario = new Funcionario();
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	@Autowired
	private AtividadeDiariaDao atividaDiariaDao;
	@Autowired
	private FuncionarioDao funcionarioDao;

	private List<AtividadeDiaria> atividadesDiarias = new ArrayList<AtividadeDiaria>();
	
	private Date data;

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

	public List<AtividadeDiaria> getAtividadesDiarias() {
		return atividadesDiarias;
	}

	public void setAtividadesDiarias(List<AtividadeDiaria> atividadesDiarias) {
		this.atividadesDiarias = atividadesDiarias;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	public void carregarFuncionario(SelectEvent evt) {
		try {
			this.funcionario = funcionarioDao.consultar(funcionario.getId());
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	public List<Funcionario> consultarFuncionario(String query) {
		return funcionarioDao.listarPorNome(query);
	}
	public void adicionarGrade(){
		funcionario.addAtividadeDiaria(atividadeDiaria);
	}
}
