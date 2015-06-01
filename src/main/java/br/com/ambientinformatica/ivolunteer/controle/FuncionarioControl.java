package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Cidade;
import br.com.ambientinformatica.ivolunteer.entidade.Endereco;
import br.com.ambientinformatica.ivolunteer.entidade.EnumEstado;
import br.com.ambientinformatica.ivolunteer.entidade.EnumEstadoCivil;
import br.com.ambientinformatica.ivolunteer.entidade.EnumSexo;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoTelefone;
import br.com.ambientinformatica.ivolunteer.entidade.Frequencia;
import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;
import br.com.ambientinformatica.ivolunteer.entidade.GradeHorario;
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.entidade.Telefone;
import br.com.ambientinformatica.ivolunteer.persistencia.FuncionarioDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Controller("FuncionarioControl")
@Scope("conversation")
public class FuncionarioControl {

	private Funcionario funcionario = new Funcionario();
	private Endereco endereco = new Endereco();
	private Cidade cidade = new Cidade();
	private Telefone telefone = new Telefone();
	private Pessoa pessoa = new Pessoa();
	private Frequencia frequencia = new Frequencia();

	private List<Frequencia> frequencias = new ArrayList<Frequencia>();

	private GradeHorario gradeHorario = new GradeHorario();

	@Autowired
	private FuncionarioDao funcionarioDao;

	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();

	@PostConstruct
	public void init() {
		// listar(null);
	}

	public void confirmar(ActionEvent evt) {
		try {
			funcionarioDao.alterar(funcionario);
			funcionario = new Funcionario();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void consultar(ActionEvent evt) {
		try {
			funcionarioDao.consultar(funcionario.getId());
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void incluir(ActionEvent evt) {
		try {
			funcionarioDao.incluir(funcionario);
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}

	}

	public void excluir(ActionEvent evt) {
		try {
			funcionarioDao.excluirPorId(funcionario);
		} catch (PersistenciaException e) {
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

	public void alterarLista(ActionEvent evt) {
		try {
			funcionarioDao.alterar(funcionarios);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void adicionarFrequencia(ActionEvent evt) {
		funcionario.addFrequencia(frequencia);
	}

	public void adicionarGradeHorario(ActionEvent evt) {
		funcionario.addGradeHorario(gradeHorario);
		gradeHorario = new GradeHorario();
	}

	public List<SelectItem> getCompleteEnumEstado() {
		return UtilFaces.getListEnum(EnumEstado.values());
	}

	public List<SelectItem> getCompleteEnumSexo() {
		return UtilFaces.getListEnum(EnumSexo.values());
	}

	public List<SelectItem> getCompleteEnumTipoTelefone() {
		return UtilFaces.getListEnum(EnumTipoTelefone.values());
	}

	public List<SelectItem> getCompleteEnumEstadoCivil() {
		return UtilFaces.getListEnum(EnumEstadoCivil.values());
	}

	public void addEndereco(ActionEvent ev) {
		try {
			this.funcionario.addEndereco(endereco);
			this.endereco = new Endereco();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void removerEndereco(Endereco endereco) {
		try {
			this.funcionario.removerEndereco(endereco);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void addTelefone(ActionEvent ev) {
		try {
			this.funcionario.addTelefone(telefone);
			this.telefone = new Telefone();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void removerTelefone(Telefone telefone) {
		try {
			this.funcionario.removerTelefone(telefone);
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	// Frequencia - Carregar lista para registar presença
	public void setFrequencias(List<Frequencia> selectedCars) {
		frequencias = selectedCars;
	}

	public List<Frequencia> getFrequencias() {
		return frequencias;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Funcionario carregarFuncionario(SelectEvent evt) {
		return funcionarioDao.carregarFuncionario(funcionario);
	}

	public List<Funcionario> consultarFuncionario(String query) {
		List<Funcionario> func = funcionarioDao.listarPorNome(query);
		return func;
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	public void carregarFrequenciaMes(ActionEvent evt) {
		frequencias = funcionario.getFrequencias();

		// int mes = frequencia.getData().getMonth();
		//
		// for (Frequencia frequenciaMes : auxiliar) {
		// if (mes == frequenciaMes.getData().getMonth()) {
		// frequencias.add(frequencia);
		// }
		// }

	}

	// metodo para preencher a data
	public void preencheData(SelectEvent event) {
		frequencia.setData((Date) event.getObject());
	}

	public GradeHorario getGradeHorario() {
		return gradeHorario;
	}

	public void setGradeHorario(GradeHorario gradeHorario) {
		this.gradeHorario = gradeHorario;
	}

	public Frequencia getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(Frequencia frequencia) {
		this.frequencia = frequencia;
	}

}
