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
import br.com.ambientinformatica.ivolunteer.entidade.Colaborador;
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.persistencia.AtividadeDiariaDao;
import br.com.ambientinformatica.ivolunteer.persistencia.ColaboradorDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Controller("AtividadeDiariaControl")
@Scope("conversation")
public class AtividadeDiariaControl {

	
	

	@Autowired
	private AtividadeDiariaDao atividadeDiariaDao;
	private AtividadeDiaria atividadeDiaria = new AtividadeDiaria();

	@Autowired
	private ColaboradorDao funcionarioDao;
	private Colaborador funcionario = new Colaborador();

	private List<AtividadeDiaria> atividadesDiarias = new ArrayList<AtividadeDiaria>();
	
	private List<Colaborador> listFuncionario = new ArrayList<Colaborador>();

	public Colaborador getFuncionario() {
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
			funcionario = new Colaborador();
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

	public List<Colaborador> consultarFuncionario(String query) {
		this.listFuncionario = funcionarioDao.listarPorNome(query);
		return listFuncionario;
	}
	
	
	public void inicializar() {
		this.funcionario = new Colaborador();
		this.atividadeDiaria = new AtividadeDiaria();
	}

	public void setFuncionario(Colaborador funcionario) {
		this.funcionario = funcionario;
	}

	public List<Colaborador> getListFuncionario() {
		return listFuncionario;
	}

	public void setListFuncionario(List<Colaborador> listFuncionario) {
		this.listFuncionario = listFuncionario;
	}
	public AtividadeDiariaDao getAtividadeDiariaDao() {
		return atividadeDiariaDao;
	}
	public void setAtividadeDiariaDao(AtividadeDiariaDao atividadeDiariaDao) {
		this.atividadeDiariaDao = atividadeDiariaDao;
	}
	public ColaboradorDao getFuncionarioDao() {
		return funcionarioDao;
	}
	public void setFuncionarioDao(ColaboradorDao funcionarioDao) {
		this.funcionarioDao = funcionarioDao;
	}
	
}
