package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
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
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.persistencia.AtividadeDiariaDao;
import br.com.ambientinformatica.ivolunteer.persistencia.FuncionarioDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Controller("AtividadeDiariaControl")
@Scope("conversation")
public class AtividadeDiariaControl {

	
	

	@Autowired
	private AtividadeDiariaDao atividadeDiariaDao;
	private AtividadeDiaria atividadeDiaria = new AtividadeDiaria();

	@Autowired
	private FuncionarioDao funcionarioDao;
	private Funcionario funcionario = new Funcionario();

	private List<AtividadeDiaria> atividadesDiarias = new ArrayList<AtividadeDiaria>();
	
	private List<Funcionario> listFuncionario = new ArrayList<Funcionario>();

	public Funcionario getFuncionario() {
		return funcionario;
	}
	@PostConstruct
	public void init() {
		listar(null);
	}

	public void confirmar(ActionEvent evt) {
		try {
			atividadeDiaria.setFuncionario(funcionario);
			atividadeDiariaDao.alterar(atividadeDiaria);
			listar(evt);
			atividadeDiaria = new AtividadeDiaria();
			funcionario = new Funcionario();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void removerAtividade(AtividadeDiaria atividadeDiaria) {
		try {
			atividadeDiariaDao.excluirPorId(atividadeDiaria.getId());
			listar(null);			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void listar(ActionEvent evt) {
		try {
			atividadesDiarias = atividadeDiariaDao.listar();
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
	
	public void carregaAtividade(AtividadeDiaria atividadeDiaria) {
		try {
			
			this.atividadeDiaria = atividadeDiariaDao.consultar(atividadeDiaria.getId());
			
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public List<Funcionario> consultarFuncionario(String query) {
		this.listFuncionario = funcionarioDao.listarPorNome(query);
		return listFuncionario;
	}
	
	
	public void inicializar() {
		this.funcionario = new Funcionario();
		this.atividadeDiaria = new AtividadeDiaria();
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getListFuncionario() {
		return listFuncionario;
	}

	public void setListFuncionario(List<Funcionario> listFuncionario) {
		this.listFuncionario = listFuncionario;
	}
	public AtividadeDiariaDao getAtividadeDiariaDao() {
		return atividadeDiariaDao;
	}
	public void setAtividadeDiariaDao(AtividadeDiariaDao atividadeDiariaDao) {
		this.atividadeDiariaDao = atividadeDiariaDao;
	}
	public FuncionarioDao getFuncionarioDao() {
		return funcionarioDao;
	}
	public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
		this.funcionarioDao = funcionarioDao;
	}
	
}
