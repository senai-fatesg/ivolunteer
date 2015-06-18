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

	@Autowired
	private AtividadeDiariaDao atividadeDiariaDao;
	@Autowired
	private FuncionarioDao funcionarioDao;

	private List<AtividadeDiaria> atividadesDiarias = new ArrayList<AtividadeDiaria>();

	@PostConstruct
	public void init() {
		listar(null);
	}

	public void confirmar(ActionEvent evt) {
		try {
			atividadeDiariaDao.alterar(atividadeDiaria);
			listar(evt);
			atividadeDiaria = new AtividadeDiaria();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt) {
		try {
			atividadesDiarias = atividadeDiariaDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void excluir(AtividadeDiaria atividadeDiaria) {
		try {
			atividadeDiariaDao.excluirPorId(atividadeDiaria.getId());
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

	public void carregarFuncionario(SelectEvent evt) {
		try {
			this.funcionario = funcionarioDao.consultar(funcionario.getId());
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void carregaAtividade(AtividadeDiaria atividadeDiaria) {
		try {
			this.atividadeDiaria = atividadeDiariaDao
					.consultar(atividadeDiaria);
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public List<Funcionario> consultarFuncionario(String query) {
		return funcionarioDao.listarPorNome(query);
	}

	public void adicionarGrade() {
		try {
			funcionario.addAtividadeDiaria(atividadeDiaria);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void removerAtividade(AtividadeDiaria atividadeDiaria){
		try {
			this.funcionario.removerAtividade(atividadeDiaria);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void inicializar(){
		this.funcionario = new Funcionario();
		this.atividadeDiaria = new AtividadeDiaria();
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
}
