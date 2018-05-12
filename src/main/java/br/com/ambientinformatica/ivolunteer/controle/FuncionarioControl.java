package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.Date;
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
import br.com.ambientinformatica.ivolunteer.entidade.EnumCargo;
import br.com.ambientinformatica.ivolunteer.entidade.EnumDiaSemana;
import br.com.ambientinformatica.ivolunteer.entidade.EnumEstado;
import br.com.ambientinformatica.ivolunteer.entidade.EnumEstadoCivil;
import br.com.ambientinformatica.ivolunteer.entidade.EnumSexo;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoFuncionario;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoPessoa;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoTelefone;
import br.com.ambientinformatica.ivolunteer.entidade.Frequencia;
import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;
import br.com.ambientinformatica.ivolunteer.entidade.GradeHorario;
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.entidade.Telefone;
import br.com.ambientinformatica.ivolunteer.persistencia.FuncionarioDao;
import br.com.ambientinformatica.ivolunteer.persistencia.PessoaDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Controller("FuncionarioControl")
@Scope("conversation")
public class FuncionarioControl {

	private Funcionario funcionario = new Funcionario();
	private Endereco endereco = new Endereco();
	private Cidade cidade = new Cidade();
	private Telefone telefone = new Telefone();
	private Funcionario filtro = new Funcionario();
	private Frequencia frequencia = new Frequencia();
	

	private List<Frequencia> frequencias = new ArrayList<Frequencia>();

	private GradeHorario gradeHorario = new GradeHorario();

	@Autowired
	private FuncionarioDao funcionarioDao;
	
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();

	@PostConstruct
	public void init() {
		listarTodosFuncionarios(null);
	}

	public void confirmar(ActionEvent evt) {
		try {
			funcionario = new Funcionario();
			this.funcionario.addEndereco(endereco);
			this.endereco = new Endereco();
			funcionarioDao.alterar(funcionario);
			 
			UtilFaces.addMensagemFaces("Informações salvas com sucesso!");
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
	
	public void carregaFuncionarioAlteracao(Funcionario funcionario){
		try {
	      this.funcionario = funcionarioDao.consultar(funcionario.getId());
      } catch (PersistenciaException e) {
      	UtilFaces.addMensagemFaces(e);
      }		
	}

	public void incluir(ActionEvent evt) {
		try {
			funcionarioDao.alterar(funcionario);
			this.funcionario = new Funcionario();
			UtilFaces.addMensagemFaces("Funcionário salvo com sucesso!");
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}

	}

	public void excluir(Funcionario funcionario) {
		try {
			funcionarioDao.excluirPorId(funcionario.getId());
			this.funcionarios.remove(funcionario);
			UtilFaces.addMensagemFaces("Funcionário excluido com sucesso!");
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}

	}

	public void listarTodosFuncionarios(ActionEvent evt) {
		try {
			this.funcionarios = funcionarioDao.listar();
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
		try {
			funcionario.addFrequencia(frequencia);
			funcionarioDao.alterar(funcionario);
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces("Ocorreu um erro ao adicionar a Frequencia");
		}
	}
	

	public void adicionarGradeHorario(ActionEvent evt) {
		funcionario.addGradeHorario(gradeHorario);
		gradeHorario = new GradeHorario();
	}

	public List<SelectItem> getCompleteEnumEstado() {
		return UtilFaces.getListEnum(EnumEstado.values());
	}
	
	public List<SelectItem> getCompleteEnumCargo() {
		return UtilFaces.getListEnum(EnumCargo.values());
	}
	
	public List<SelectItem> getCompleteEnumTipoFuncionario() {
		return UtilFaces.getListEnum(EnumTipoFuncionario.values());
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
	
	public List<SelectItem> getCompleteEnumDiaSemana() {
		return UtilFaces.getListEnum(EnumDiaSemana.values());
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
	
	// Aplica Filtro
	public void aplicarFiltro(ActionEvent evt) {
		try {
			if (this.filtro.getNomePessoa() == null || this.filtro.getNomePessoa().isEmpty()) {
				this.funcionarios = this.funcionarioDao.listar();
			} else {				
				this.funcionarios = this.funcionarioDao.listarPorNome(this.filtro.getNomePessoa());
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}

	}

	public FuncionarioDao getFuncionarioDao() {
		return funcionarioDao;
	}

	public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
		this.funcionarioDao = funcionarioDao;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
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

	public Funcionario getFiltro() {
		return filtro;
	}

	public void setFiltro(Funcionario filtro) {
		this.filtro = filtro;
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
