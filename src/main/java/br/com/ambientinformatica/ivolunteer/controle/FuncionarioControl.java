package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.jsf.FacesContextUtils;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Cidade;
import br.com.ambientinformatica.ivolunteer.entidade.Endereco;
import br.com.ambientinformatica.ivolunteer.entidade.EnumCargo;
import br.com.ambientinformatica.ivolunteer.entidade.EnumDiaSemana;
import br.com.ambientinformatica.ivolunteer.entidade.EnumEstado;
import br.com.ambientinformatica.ivolunteer.entidade.EnumEstadoCivil;
import br.com.ambientinformatica.ivolunteer.entidade.EnumSexo;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoEtnia;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoFuncionario;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoPessoa;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoTelefone;
import br.com.ambientinformatica.ivolunteer.entidade.Frequencia;
import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;
import br.com.ambientinformatica.ivolunteer.entidade.GradeHorario;
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.entidade.Telefone;
import br.com.ambientinformatica.ivolunteer.persistencia.EnderecoDao;
import br.com.ambientinformatica.ivolunteer.persistencia.FuncionarioDao;
import br.com.ambientinformatica.ivolunteer.persistencia.PessoaDao;
import br.com.ambientinformatica.ivolunteer.persistencia.TelefoneDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.util.UtilLog;

@Controller("FuncionarioControl")
@Scope("conversation")
public class FuncionarioControl {

	private Funcionario funcionario = new Funcionario();
	private Funcionario infoFuncionario = new Funcionario();
	private Endereco endereco = new Endereco();
	private Cidade cidade = new Cidade();
	private Telefone telefoneEmpresa = new Telefone();
	private String nomeFuncionarioPesquisa;
	private String statusFiltro;
	private EnumTipoFuncionario tipoFuncionarioPesquisa;
	private Frequencia frequencia = new Frequencia();

	private List<Frequencia> frequencias = new ArrayList<Frequencia>();

	private GradeHorario gradeHorario = new GradeHorario();

	@Autowired
	private EnderecoDao enderecoDao;
	@Autowired
	private TelefoneDao telefoneDao;
	@Autowired
	private FuncionarioDao funcionarioDao;

	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	private Telefone telefoneFuncionario = new Telefone();

	@PostConstruct
	public void init() {
		listarTodosFuncionarios(null);
	}

	public void confirmar(ActionEvent evt) {
		try {
			if (this.validarNome()) {				
				if (this.funcionario.getId() == null) {
					this.funcionario.setEnumTipoPessoa(EnumTipoPessoa.COLABORADOR);
					this.funcionario.addEndereco(endereco);
					funcionarioDao.incluir(this.funcionario);
				} else {
					enderecoDao.alterar(this.endereco);
					funcionarioDao.alterar(this.funcionario);
				}
				listarTodosFuncionarios(null);
				this.endereco = new Endereco();
				this.funcionario = new Funcionario();
				this.telefoneEmpresa = new Telefone();
				this.telefoneFuncionario = new Telefone();
				UtilFaces.addMensagemFaces("Informações salvas com sucesso!");
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
			UtilLog.getLog().error(e);
		}
	}

	public void consultar(ActionEvent evt) {
		try {
			funcionarioDao.consultar(funcionario.getId());
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void carregaFuncionarioAlteracao(Funcionario funcionario) {
		try {
			this.funcionario = funcionarioDao.carregarFuncionarioComEnderecoTelefone(funcionario);
			this.endereco = this.funcionario.getListaEndereco().get(0);
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void incluir(ActionEvent evt) {
		try {
			funcionarioDao.alterar(funcionario);
			this.funcionario = new Funcionario();
			UtilFaces.addMensagemFaces("Colaborador salvo com sucesso!");
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}

	}

	public void excluir(Funcionario funcionario) {
		try {
			funcionario = this.desativarFuncionarioCompleto(funcionario);
			funcionario.desativa();
			funcionarioDao.alterar(funcionario);
			listarTodosFuncionarios(null);
			UtilFaces.addMensagemFaces("Colaborador excluido com sucesso!");
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}

	}
	
	public Funcionario desativarFuncionarioCompleto(Funcionario funcionario) {
		Funcionario func = funcionarioDao.carregarFuncionarioComEnderecoTelefone(funcionario);
		if (func.getListaEndereco().size() > 0) {
			for (Endereco endereco : func.getListaEndereco()) {
				endereco.inativaEndereco();
				enderecoDao.alterar(endereco);
			}
		}
		if (func.getListaTelefone().size() > 0) {
			for (Telefone telefone : func.getListaTelefone()) {
				telefone.desativa();
				telefoneDao.alterar(telefone);
			}
		}
		if (func.getTelefonesEmpresa().size() > 0 && 
				func.getTipoFuncionario().equals(EnumTipoFuncionario.TERCEIRIZADO)) {
			for (Telefone telefone : func.getTelefonesEmpresa()) {
				telefone.desativa();
				telefoneDao.alterar(telefone);
			}
		}
		return func;
	}
	
	private boolean validarNome() {
		if (this.funcionario.getNomePessoa().isEmpty()) {
			UtilFaces.addMensagemFaces("Nome do Colaborador é obrigatório!");
			return false;
		} else {
			return true;			
		}
	}

	public void validaEmail(FacesContext fc, UIComponent uc, Object ob) {
		String email = (String) ob;
		if (!email.isEmpty() && !email.contains("@")) {
			((UIInput) uc).setValid(false);
			UtilFaces.addMensagemFaces("Email inválido. O email deve conter '@' em seu endereço.");
		}
	}

	public void validaSite(FacesContext fc, UIComponent uc, Object ob) {
		String site = (String) ob;
		if (!site.isEmpty() && !site.contains("www.")) {
			((UIInput) uc).setValid(false);
			UtilFaces.addMensagemFaces("Site inválido. O site deve conter 'www.' no início.");
		}
	}

	public void limpaCamposFuncionario() {
		this.funcionario.setSegmento(null);
		this.funcionario.setSite(null);
		this.funcionario.setEmailDoFuncionario(null);
		this.funcionario.setEmailDaEmpresa(null);
		this.funcionario.setNomeEmpresa(null);
		this.funcionario.setCnpj(null);
	}

	public void validaNome(FacesContext fc, UIComponent uc, Object ob) {
		String nome = (String) ob;
		if (nome.isEmpty()) {
			((UIInput) uc).setValid(false);
			UtilFaces.addMensagemFaces("Nome é obrigatório!");
		}
	}

	public void validaCPF(FacesContext fc, UIComponent uc, Object ob) {
		String cpf = (String) ob;
		if (cpf.isEmpty()) {
			((UIInput) uc).setValid(false);
			UtilFaces.addMensagemFaces("CPF é obrigatório!");
		}
	}
	
	public void exibiInformacoesFuncionario(Funcionario funcionario) {
		this.infoFuncionario = funcionarioDao.carregarFuncionarioComEnderecoTelefone(funcionario);
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
	
	public List<SelectItem> getCompleteEnumTipoEtnia() {
		return UtilFaces.getListEnum(EnumTipoEtnia.values());
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

	public void atualizarTelefone(String empresaOuFuncionario) {
		if (empresaOuFuncionario.equals("Empresa")) {
			this.telefoneDao.alterar(this.telefoneEmpresa);
			this.telefoneEmpresa = new Telefone();
			UtilFaces.addMensagemFaces("Telefone da empresa atualizado.");
			this.funcionario = funcionarioDao.carregarFuncionarioComEnderecoTelefone(this.funcionario);
		} else if (empresaOuFuncionario.equals("Funcionario")) {
			this.telefoneDao.alterar(this.telefoneFuncionario);
			this.telefoneFuncionario = new Telefone();
			UtilFaces.addMensagemFaces("Telefone do colaborador atualizado.");
			this.funcionario = funcionarioDao.consultar(this.funcionario.getId());
		}
	}
	
	public boolean campoVazio(String empresaOuFuncionario) {
		if (empresaOuFuncionario.equals("Empresa") && this.telefoneEmpresa.getNumeroTelefone().isEmpty()) {
			UtilFaces.addMensagemFaces("Informe um numero de telefone da empresa!");
			return true;
		} else if (empresaOuFuncionario.equals("Funcionario") && this.telefoneFuncionario.getNumeroTelefone().isEmpty()) {
			UtilFaces.addMensagemFaces("Informe um numero de telefone do colaborador!");
			return true;
		}
		return false; 
	}

	public void adicionarTelefone(String empresaOuFuncionario) {
		try {
			if (this.campoVazio(empresaOuFuncionario)) {
				return;
			} else if (empresaOuFuncionario.equals("Empresa")) {
				this.funcionario.addTelefoneEmpresa(this.telefoneEmpresa);
				this.telefoneEmpresa = new Telefone();
				UtilFaces.addMensagemFaces("Telefone da empresa adicionado.");					
			} else if (empresaOuFuncionario.equals("Funcionario")) {
				 this.funcionario.addTelefone(this.telefoneFuncionario);
				 this.telefoneFuncionario = new Telefone();
				 UtilFaces.addMensagemFaces("Telefone do colaborador adicionado.");					
			}				
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
			UtilLog.getLog().error(e);
		}
	}

	public void editarTelefone(Telefone telefone, String empresaOuFuncionario) {
		if (empresaOuFuncionario.equals("Funcionario")) {
			if (telefone.getId() == null) {
				this.telefoneFuncionario = telefone;
				this.funcionario.getListaTelefone().remove(telefone);
			} else {
				this.telefoneFuncionario = telefoneDao.consultar(telefone.getId());
			}
		} else if (empresaOuFuncionario.equals("Empresa")) {
			this.telefoneEmpresa = telefoneDao.consultar(telefone.getId());
		}
	}

	public void desativarTelefone(Telefone telefone, String empresaOuFuncionario) {
		try {
			if (empresaOuFuncionario.equals("Empresa")) {
				if (telefone.getId() == null) {
					this.funcionario.getTelefonesEmpresa().remove(telefone);
				} else {
					telefone.desativa();
					telefoneDao.alterar(telefone);
				}
				UtilFaces.addMensagemFaces("Telefone da empresa removido!");
			} else if (empresaOuFuncionario.equals("Funcionario")) {
				if (telefone.getId() == null) {
					this.funcionario.getListaTelefone().remove(telefone);
				} else {
					telefone.desativa();
					telefoneDao.desativarTelefone(telefone);					
				}
				UtilFaces.addMensagemFaces("Telefone do colaborador removido!");
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	// Aplica Filtro
	public void aplicarFiltro(ActionEvent evt) {
		try {
			if (this.validarFiltroPorNome()) {
				this.funcionarios = this.funcionarioDao.listarPorNome(this.nomeFuncionarioPesquisa);
			} else if (this.validarFiltroPorTipo()) {
				this.funcionarios = this.funcionarioDao.listarPorTipo(this.tipoFuncionarioPesquisa);	
			} else if (this.validarFiltroPorStatus()) {
				this.funcionarios = this.funcionarioDao.listarPorStatus(this.statusFiltro);
			} else if (this.validarFiltroPorNomeETipo()) {
				this.funcionarios = this.funcionarioDao.listarPorNomeETipo(this.nomeFuncionarioPesquisa, 
						this.tipoFuncionarioPesquisa);
			} else if (this.validarFiltroPorNomeEStatus()) {
				this.funcionarios = this.funcionarioDao.listarPorNomeEStatus(this.nomeFuncionarioPesquisa,
						this.statusFiltro);
			} else if (this.validarFiltroPorTipoEStatus()) {
				this.funcionarios = this.funcionarioDao.listarPorTipoEStatus(this.tipoFuncionarioPesquisa, 
						this.statusFiltro);
			} else {
				this.funcionarios = this.funcionarioDao.listar();
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
			UtilLog.getLog().error(e);
		}

	}
	
	public boolean validarFiltroPorNome() {
		if ((!this.nomeFuncionarioPesquisa.isEmpty()) && this.tipoFuncionarioPesquisa == null 
				&& this.statusFiltro.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean validarFiltroPorTipo() {
		if (this.tipoFuncionarioPesquisa != null && this.nomeFuncionarioPesquisa.isEmpty() 
				&& this.statusFiltro.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean validarFiltroPorStatus() {
		if (!this.statusFiltro.isEmpty() && 
				(this.nomeFuncionarioPesquisa.isEmpty() && this.tipoFuncionarioPesquisa == null)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean validarFiltroPorNomeETipo() {
		if ((!this.nomeFuncionarioPesquisa.isEmpty() && this.tipoFuncionarioPesquisa != null) 
				&& this.statusFiltro.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean validarFiltroPorNomeEStatus() {
		if ((!this.nomeFuncionarioPesquisa.isEmpty() && !this.statusFiltro.isEmpty()) 
				&& this.tipoFuncionarioPesquisa == null) {
			return true;
		} else {			
			return false;
		}
	}
	
	public boolean validarFiltroPorTipoEStatus() {
		if ((this.tipoFuncionarioPesquisa != null && !this.statusFiltro.isEmpty()) 
				&& this.nomeFuncionarioPesquisa.isEmpty()) {
			return true;
		} else {
			return false;
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

	public Funcionario getInfoFuncionario() {
		return infoFuncionario;
	}

	public void setInfoFuncionario(Funcionario infoFuncionario) {
		this.infoFuncionario = infoFuncionario;
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

	public Telefone getTelefoneEmpresa() {
		return telefoneEmpresa;
	}

	public void setTelefoneEmpresa(Telefone telefoneEmpresa) {
		this.telefoneEmpresa = telefoneEmpresa;
	}

	public Telefone getTelefoneFuncionario() {
		return telefoneFuncionario;
	}

	public void setTelefoneFuncionario(Telefone telefoneFuncionario) {
		this.telefoneFuncionario = telefoneFuncionario;
	}

	// Frequencia - Carregar lista para registar presença
	public void setFrequencias(List<Frequencia> selectedCars) {
		frequencias = selectedCars;
	}

	public List<Frequencia> getFrequencias() {
		return frequencias;
	}

	public String getNomeFuncionarioPesquisa() {
		return nomeFuncionarioPesquisa;
	}

	public void setNomeFuncionarioPesquisa(String nomeFuncionarioPesquisa) {
		this.nomeFuncionarioPesquisa = nomeFuncionarioPesquisa;
	}

	public String getStatusFiltro() {
		return statusFiltro;
	}

	public void setStatusFiltro(String statusFiltro) {
		this.statusFiltro = statusFiltro;
	}

	public EnumTipoFuncionario getTipoFuncionarioPesquisa() {
		return tipoFuncionarioPesquisa;
	}

	public void setTipoFuncionarioPesquisa(EnumTipoFuncionario tipoFuncionarioPesquisa) {
		this.tipoFuncionarioPesquisa = tipoFuncionarioPesquisa;
	}

	public Funcionario carregarFuncionario(SelectEvent evt) {
		return funcionarioDao.carregarFuncionario(funcionario);
	}

	public List<Funcionario> consultarFuncionario(String query) {
		List<Funcionario> func = funcionarioDao.listarPorNome(query);
		return func;
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
