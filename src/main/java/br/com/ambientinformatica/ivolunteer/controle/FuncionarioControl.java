package br.com.ambientinformatica.ivolunteer.controle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

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
	private List<Funcionario> frequencias = new ArrayList<Funcionario>();

	private GradeHorario gradeHorario = new GradeHorario();
	
	@Autowired
	private FuncionarioDao funcionarioDao;

	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();

//	@PostConstruct
//	public void init() {
//		listar(null);
//	}

	
	public void adicionarGradeHorario(ActionEvent evt){
		funcionario.addGradeHorario(gradeHorario);
		gradeHorario = new GradeHorario();
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
			funcionario = new Funcionario();
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

	public void adicionarListaEndereco(ActionEvent evt) {
		List<Endereco> retornoListaEndereco = new ArrayList<Endereco>();
		retornoListaEndereco.add(endereco);
		funcionario.setListaEndereco(retornoListaEndereco);

	}

	public void adicionarListaTelefone(ActionEvent evt) {
		List<Telefone> retornoListaTelefone = new ArrayList<Telefone>();
		retornoListaTelefone.add(telefone);
		funcionario.setListaTelefone(retornoListaTelefone);

	}

	public List<String> completeEnumSexo(String query) {
		List<String> retorno = new ArrayList<String>();
		EnumSexo[] enunSexo = EnumSexo.values();
		for (int i = 0; i < enunSexo.length; i++) {
			retorno.add(enunSexo[i].getDescricao());
		}
		return retorno;
	}

	public List<EnumEstado> completeEnumUf(String query) {
		List<EnumEstado> retornoUf = new ArrayList<EnumEstado>();
		EnumEstado[] enunUf = EnumEstado.values();
		for (int i = 0; i < enunUf.length; i++) {
			retornoUf.add(enunUf[i]);
		}
		return retornoUf;
	}

	public List<EnumTipoTelefone> completeEnumTipoTelefone(String query) {
		List<EnumTipoTelefone> retornoTipoTelefone = new ArrayList<EnumTipoTelefone>();
		EnumTipoTelefone[] enunTelefone = EnumTipoTelefone.values();
		for (int i = 0; i < enunTelefone.length; i++) {
			retornoTipoTelefone.add(enunTelefone[i]);
		}
		return retornoTipoTelefone;
	}

	public List<String> completeEnumEstadoCivil(String query) {
		List<String> retornoEstadoCivil = new ArrayList<String>();
		EnumEstadoCivil[] enunEstadoCivil = EnumEstadoCivil.values();
		for (int i = 0; i < enunEstadoCivil.length; i++) {
			retornoEstadoCivil.add(enunEstadoCivil[i].getDescricao());
		}
		return retornoEstadoCivil;
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
			this.funcionario.removerEdereco(endereco);
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
	public void setSelectedCars(List<Funcionario> selectedCars) {
		frequencias = selectedCars;
	}

	public List<Funcionario> getSelectedCars() {
		return frequencias;
	}

	public void addPresenca(SelectEvent evt) {

	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Funcionario carregarFuncionario(ActionEvent evt) {
		return funcionarioDao.carregarFuncionario(funcionario);
	}

	public List<Funcionario> consultarFuncionario(String query) {
		List<Funcionario> func = funcionarioDao.listarPorNome(query);
		return func;
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null,
		      new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected",
		            format.format(event.getObject())));
	}

	public GradeHorario getGradeHorario() {
		return gradeHorario;
	}

	public void setGradeHorario(GradeHorario gradeHorario) {
		this.gradeHorario = gradeHorario;
	}

	
}
