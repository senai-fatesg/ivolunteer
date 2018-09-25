package br.com.ambientinformatica.ivolunteer.controle;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Candidato;
import br.com.ambientinformatica.ivolunteer.entidade.Cidade;
import br.com.ambientinformatica.ivolunteer.entidade.Endereco;
import br.com.ambientinformatica.ivolunteer.entidade.EnumAcessoComputador;
import br.com.ambientinformatica.ivolunteer.entidade.EnumEscolaridade;
import br.com.ambientinformatica.ivolunteer.entidade.EnumEstado;
import br.com.ambientinformatica.ivolunteer.entidade.EnumEstadoCivil;
import br.com.ambientinformatica.ivolunteer.entidade.EnumFiliacao;
import br.com.ambientinformatica.ivolunteer.entidade.EnumLocalInternet;
import br.com.ambientinformatica.ivolunteer.entidade.EnumPNE;
import br.com.ambientinformatica.ivolunteer.entidade.EnumParticipacaoEconomicaFamilia;
import br.com.ambientinformatica.ivolunteer.entidade.EnumPrioridade;
import br.com.ambientinformatica.ivolunteer.entidade.EnumReside;
import br.com.ambientinformatica.ivolunteer.entidade.EnumResidencia;
import br.com.ambientinformatica.ivolunteer.entidade.EnumSexo;
import br.com.ambientinformatica.ivolunteer.entidade.EnumStatusConcluiu;
import br.com.ambientinformatica.ivolunteer.entidade.EnumStatusDesistiu;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTemCelular;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTemInternet;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoCasa;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoEtnia;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoPessoa;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoTelefone;
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.entidade.Responsavel;
import br.com.ambientinformatica.ivolunteer.entidade.Telefone;
import br.com.ambientinformatica.ivolunteer.persistencia.CandidatoDao;
import br.com.ambientinformatica.ivolunteer.persistencia.EnderecoDao;
import br.com.ambientinformatica.ivolunteer.persistencia.ResponsavelDao;
import br.com.ambientinformatica.ivolunteer.persistencia.TelefoneDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.util.UtilLog;

@Controller("CandidatoControl")
@Scope("conversation")
public class CandidatoControl {

	private Candidato candidato = new Candidato();
	private boolean maiorDeIdade;
	private Responsavel responsavel = new Responsavel();
	private Candidato filtro = new Candidato();

	// instancia de candidato carregada para realização de consulta
	private String candidatoConsulta;
	private String statusFiltro;

	// lista utilizada na consulta do candidato
	private List<Candidato> listaCandidato = new ArrayList<Candidato>();

	// listas e utilizada para a apresentação na grid
	private List<Responsavel> listaResponsavel = new ArrayList<Responsavel>();
	private List<Telefone> listaTelefone = new ArrayList<Telefone>();
	// objetos utilizados para tratamento das listas
	private Telefone telefoneCelularCandidato = new Telefone();
	private Telefone telefoneResidencialCandidato = new Telefone();
	private Telefone telefoneEmergencia = new Telefone();
	private Telefone telefoneResponsavel = new Telefone();
	private Endereco endereco = new Endereco();
	private Endereco enderecoResponsavel = new Endereco();
	private Cidade cidade = new Cidade();

	// Atributo utilziado para tratamento de renda total do responsavel
	private BigDecimal totalRenda = BigDecimal.ZERO;

	// Flags para indicar se existe alguma alteração em andamento no formulario
	private boolean editando;
	
	private boolean renderizaObservacao = false;
	private boolean renderizaPeriodoConclusao = false;

	@Autowired
	private CandidatoDao candidatoDao;

	@Autowired
	private ResponsavelDao responsavelDao;

	@Autowired
	private EnderecoDao enderecoDao;

	@Autowired
	private TelefoneDao telefoneDao;
	private Candidato exibeCandInfo;

	@PostConstruct
	public void init() {
		listarTodosCandidatos();
	}

	public void listarCandidatoPorNome(ActionEvent evt) {
		try {
			listaCandidato = candidatoDao.listaCandidatoPorNome(this.candidatoConsulta);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void listarTodosCandidatos() {
		try {
			this.listaCandidato = candidatoDao.listaCandidatosComResponsavel();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void cadastrar() {
		try {
			if(validaCandidatoNome()) {
				this.candidato.setEnumTipoPessoa(EnumTipoPessoa.CANDIDATO);
				validarCandidato(this.candidato);
				candidatoDao.incluir(this.candidato);
				this.candidato = new Candidato();
				this.endereco = new Endereco();
				this.enderecoResponsavel = new Endereco();
				this.responsavel = new Responsavel();
				UtilFaces.addMensagemFaces("Informações salvas com sucesso!");
				listarTodosCandidatos();
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e.getMessage());
			UtilFaces.addMensagemFaces("Ocorreu uma falha ao tentar gravar as informações de candidato");
		}
	}

	public void atualizar() {
		if (validarCandidato(this.candidato)) {
			// verificaNovosTelefonesCandidato();
			// atualizaTelefonesCandidato();

			candidatoDao.alterar(this.candidato);
			// this.telefoneCelularCandidato = new Telefone();
			// this.telefoneResidencialCandidato = new Telefone();
			// this.telefoneEmergencia = new Telefone();
			this.candidato = new Candidato();
			this.endereco = new Endereco();
			this.enderecoResponsavel = new Endereco();
			this.responsavel = new Responsavel();
			UtilFaces.addMensagemFaces("Candidato atualizado com sucesso!");
			listarTodosCandidatos();
		} else {
			UtilFaces.addMensagemFaces("Candidato inválido!");
		}
	}
	
	public void exibeInformacoesCandidato(Candidato cand) {
		this.exibeCandInfo = candidatoDao.consultarCandidatoCompleto(cand);
	}

	private void atualizaTelefonesCandidato() {
		if (this.telefoneCelularCandidato.getId() != null) {
			telefoneDao.alterar(this.telefoneCelularCandidato);
		}
		if (this.telefoneResidencialCandidato.getId() != null) {
			telefoneDao.alterar(this.telefoneResidencialCandidato);
		}
		if (this.telefoneEmergencia.getId() != null) {
			telefoneDao.alterar(telefoneEmergencia);
		}
	}

	private void verificaNovosTelefonesCandidato() {
		if (!this.telefoneCelularCandidato.getNumeroTelefone().isEmpty()
				&& this.telefoneCelularCandidato.getId() == null) {
			this.telefoneCelularCandidato.setEnumTipoTelefone(EnumTipoTelefone.CELULAR);
			this.telefoneCelularCandidato.setNomePessoaRecado(this.candidato.getNomePessoa());
			this.candidato.addTelefone(telefoneCelularCandidato);
		}
		if (!this.telefoneResidencialCandidato.getNumeroTelefone().isEmpty()
				&& this.telefoneResidencialCandidato.getId() == null) {
			this.telefoneResidencialCandidato.setEnumTipoTelefone(EnumTipoTelefone.RESIDENCIAL);
			this.telefoneResidencialCandidato.setNomePessoaRecado(this.candidato.getNomePessoa());
			this.candidato.addTelefone(telefoneResidencialCandidato);
		}
		if (!this.telefoneEmergencia.getNumeroTelefone().isEmpty() && this.telefoneEmergencia.getId() == null) {
			this.candidato.addTelefone(telefoneEmergencia);
		}
	}

	public void validaTelefoneEmergencia(FacesContext fc, UIComponent uc, Object ob) {
		String telEmergencia = (String) ob;
		if (telEmergencia.equals("") && !(this.candidato.getNomeTelefoneEmergencia().equals(""))) {
			((UIInput) uc).setValid(false);
			UtilFaces.addMensagemFaces("Por favor, informe um telefone de emergência.");
		}
	}

	public void validaTelefoneNomeEmergencia(FacesContext fc, UIComponent uc, Object ob) {
		String nomeEmergencia = (String) ob;
		if (nomeEmergencia.equals("") && !(this.candidato.getTelefoneEmergencia().equals(""))) {
			((UIInput) uc).setValid(false);
			UtilFaces.addMensagemFaces("Por favor, informe uma pessoa para caso de emergência.");
		}
	}

	/**
	 * Preenche os dados do candidato automaticamente. Obs: As informações
	 * preenchidas não afetam a integridade dos dados
	 **/

	public void preenchaInformacoesDefaultCandidato(Pessoa objeto) {

		objeto.setEnumEstadoCivil(EnumEstadoCivil.SOLTEIRO);
		objeto.setEnumPrioridade(EnumPrioridade.BAIXA);
		objeto.setProfissao("NULL");
		objeto.setRg("NULL");
		objeto.setCpf("NULL");
	}

	// Adicionar o telefone do responsavel

	public void adicionarTelefone(ActionEvent evt) {
		try {
			if (EhTelefoneConsistente()) {
				// responsavel.addTelefone(telefone);
				this.telefoneCelularCandidato = new Telefone();
				this.telefoneResidencialCandidato = new Telefone();
				this.candidato = candidatoDao.consultarCandidatoCompleto(this.candidato);
			} else {
				UtilFaces.addMensagemFaces("Preencha os campos corretamente");
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Ocorreu uma falha ao tentar incluir o contato na lista");
			UtilLog.getLog().error(e);
		}
	}

	// metodo apenas para chamar o adicionarEndereco passando o candidato
	public void adicionarEnderecoCandidato() {
		adicionarEndereco("Candidato");
	}

	// metodo apenas para chamar o adicionarEndereco passando o responsavel
	public void adicionarEnderecoResponsavel() {
		adicionarEndereco("Responsavel");
	}

	// adicionar um endereco para o candidato ou responsavel
	public void adicionarEndereco(String candidatoOuResponsavel) {
		try {
			if (candidatoOuResponsavel.equals("Candidato")) {
				if (EhEnderecoConsistente(candidatoOuResponsavel)) {
					candidato.addEndereco(endereco);
					this.endereco = new Endereco();
					UtilFaces.addMensagemFaces("Endereço de candidato adicionado.");
				}
			} else if (candidatoOuResponsavel.equals("Responsavel")) {
				if (EhResponsavelConsistente()) {
					if (EhEnderecoConsistente(candidatoOuResponsavel)) {
						this.responsavel.addEndereco(this.enderecoResponsavel);
						this.enderecoResponsavel = new Endereco();
						UtilFaces.addMensagemFaces("Endereço de responsável adicionado.");
					}
				} else {
					UtilFaces.addMensagemFaces("Preencha o nome e CPF de responsável antes de adicionar um endereço. ");
				}
			}
		} catch (Exception erro) {
			UtilFaces.addMensagemFaces("Ocorreu uma falha ao tentar incluir o endereço na lista");
			UtilLog.getLog().error(erro);
		}
	}

	public void atualizarEndereco(String candidatoOuResponsavel) {
		if (candidatoOuResponsavel.equals("Candidato")) {
			if (EhEnderecoConsistente(candidatoOuResponsavel)) {
				enderecoDao.alterar(this.endereco);
				this.candidato = candidatoDao.consultarCandidatoCompleto(this.candidato);
				this.endereco = new Endereco();
				UtilFaces.addMensagemFaces("Endereço de candidato atualizado.");
			}
		} else if (candidatoOuResponsavel.equals("Responsavel")) {
			if (EhEnderecoConsistente(candidatoOuResponsavel)) {
				enderecoDao.alterar(this.enderecoResponsavel);
				this.responsavel = responsavelDao.consultaResponsavelCompleto(this.responsavel);
				this.enderecoResponsavel = new Endereco();
				UtilFaces.addMensagemFaces("Endereço de responsável atualizado.");
			}
		}
	}

	public void verificaIdade(Candidato cand) {

		if (cand.getDataNascimento() != null) {
			Calendar dataDeHoje = Calendar.getInstance();
			dataDeHoje.setTime(new Date(System.currentTimeMillis()));
			Date dataNasc = cand.getDataNascimento();
			Calendar DataNascCand = Calendar.getInstance();
			DataNascCand.setTime(dataNasc);
			long datn = dataNasc.getTime();
			long diferencaEmDias = ((System.currentTimeMillis() - datn) / (1000 * 60 * 60 * 24));
			long anosDeIdade = diferencaEmDias / 365;
			if (anosDeIdade <= 17) {
				this.maiorDeIdade = false;
				return;
			}
			if (anosDeIdade >= 19) {
				this.maiorDeIdade = true;
				return;
			}
			if (anosDeIdade == 18) {
				DataNascCand.set(Calendar.YEAR, dataDeHoje.get(Calendar.YEAR));
				if (DataNascCand.before(dataDeHoje)) {
					this.maiorDeIdade = true;
					return;
				} else {
					this.maiorDeIdade = false;
				}
			}
		} else {
			this.maiorDeIdade = false;
		}
		
	}
	
	public void calculaIdadeCandidato(SelectEvent event) {

		Calendar dataDeHoje = Calendar.getInstance();
		dataDeHoje.setTime(new Date(System.currentTimeMillis()));

		Date dataNasc = (Date) event.getObject();

		Calendar DataNascCand = Calendar.getInstance();
		DataNascCand.setTime(dataNasc);

		long datn = dataNasc.getTime();

		long diferencaEmDias = ((System.currentTimeMillis() - datn) / (1000 * 60 * 60 * 24));

		long anosDeIdade = diferencaEmDias / 365;

		if (anosDeIdade <= 17) {
			this.maiorDeIdade = false;
			return;
		}
		if (anosDeIdade >= 19) {
			this.maiorDeIdade = true;
			return;
		}
		if (anosDeIdade == 18) {
			DataNascCand.set(Calendar.YEAR, dataDeHoje.get(Calendar.YEAR));
			if (DataNascCand.before(dataDeHoje)) {
				this.maiorDeIdade = true;
				return;
			} else {
				this.maiorDeIdade = false;
			}
		}
	}

	/**
	 * Calcula a renda do responsavel, a renda e calculada com base na renda +
	 * rendaExtra
	 **/
	public BigDecimal getTotalRenda() {
		return responsavel.calcularRenda();
	}

	public void atualizaTotalRenda() {
		this.responsavel.setTotalRenda(BigDecimal.valueOf(0));
		this.responsavel.calcularRenda();
	}

	public void zeraValorBeneficio() {
		if (this.responsavel.getRecebeBeneficio().equals(false)) {
			this.responsavel.setValorBeneficio(BigDecimal.valueOf(0));
		}
	}

	public void atualizarResponsavel() {
		try {
			responsavelDao.alterar(this.responsavel);
			this.responsavel = new Responsavel();
			UtilFaces.addMensagemFaces("Responsável atualizado!");
			this.candidato = candidatoDao.consultarCandidatoCompleto(this.candidato);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Ocorreu um erro ao atualizar responsável.");
		}

	}

	// Adionar as responsaveis ao candidato
	public void adicionarResponsavel(ActionEvent evt) {
		try {
			if (validaCandidatoNome()) {
				if (EhResponsavelConsistente()) {
					responsavel.setEnumTipoPessoa(EnumTipoPessoa.RESPONSAVEL);
					this.candidato.addResponsavel(this.responsavel);
					this.responsavel = new Responsavel();
					UtilFaces.addMensagemFaces("Responsável adicionado.");
				} else {
					UtilFaces.addMensagemFaces("Preencha os dados de responsável corretamente.");
					UtilFaces.addMensagemFaces("Nome e CPF de responsável são obrigatórios.");
				}
			}
		} catch (Exception erro) {
			UtilFaces.addMensagemFaces("Ocorreu uma falha ao tentar incluir o Responsável na lista.");
			UtilLog.getLog().error(erro);
		}
	}

	public void carregaResponsavelAlteracao(Responsavel resp) {
		if (this.candidato.getListaResponsavel().contains(resp)) {
			if (resp.getId() == null) {
				this.responsavel = resp;
				this.candidato.getListaResponsavel().remove(resp);
			} else {
				this.responsavel = responsavelDao.consultar(resp.getId());
			}
		}
	}

	/**
	 * Preenche as informções do responsavel, as informções preenchidas não
	 * afeta a integridade dos dados
	 **/
	private void preenchaInformcoesDefaultResponsavel(Responsavel objeto) {
		objeto.setCertidaoNascimento("NULL");
	}

	// desativa um endereço do responsavel ou candidato
	public void desativarEndereco(Endereco endereco, String candidatoOuResponsavel) {
		try {
			if (candidatoOuResponsavel.equals("Responsavel")) {
				if (endereco.getId() == null) {
					endereco.inativaEndereco();
					this.responsavel.addEndereco(endereco);
					responsavelDao.alterar(this.responsavel);
				} else {
					endereco.inativaEndereco();
					enderecoDao.alterar(endereco);
				}
				this.responsavel = responsavelDao.consultaResponsavelCompleto(this.responsavel);
				UtilFaces.addMensagemFaces("Endereço de responsável desativado.");
			} else if (candidatoOuResponsavel.equals("Candidato")) {
				if (endereco.getId() == null) {
					endereco.inativaEndereco();
					this.candidato.addEndereco(endereco);
					candidatoDao.alterar(this.candidato);
				} else {
					endereco.inativaEndereco();
					enderecoDao.alterar(endereco);
				}
				this.candidato = candidatoDao.consultarCandidatoCompleto(this.candidato);
				UtilFaces.addMensagemFaces("Endereço de candidato desativado.");
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Ocorreu uma falha ao tentar desativar o endereço da lista.");
			UtilLog.getLog().error(e);
		}
	}

	// remove o telefone do responsavel
	public void removerTelefone(Telefone telefone) {
		try {
			responsavel.removerTelefone(telefone);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Ocorreu uma falha ao tentar excluir o endereço da lista");
			UtilLog.getLog().error(e);
		}
	}

	// inativa o responsavel do candidato
	public void desativarResponsavel(Responsavel responsavel) {
		try {
			if (responsavel.getId() == null) {
				responsavel.inativaResponsavel();
				this.candidato.addResponsavel(responsavel);
				candidatoDao.alterar(this.candidato);
			} else {
				responsavel.inativaResponsavel();
				responsavelDao.alterar(responsavel);
			}
			this.candidato = candidatoDao.consultarCandidatoCompleto(this.candidato);
			UtilFaces.addMensagemFaces("Responsável de " + this.candidato.getNomePessoa() + " desativado.");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Ocorreu uma falha ao tentar desativar o responsável.");
			UtilLog.getLog().error(e);
		}
	}

	public void desativarCandidato(Candidato candidato) {
		try {
			if (listaCandidato.contains(candidato)) {
				Candidato cand = candidatoDao.consultarCandidatoCompleto(candidato);
				if (cand.getListaEndereco().size() > 0) {
					for (Endereco end : cand.getListaEndereco()) {
						end.inativaEndereco();
						enderecoDao.alterar(end);
					}
				}
				if (cand.getListaResponsavel().size() > 0) {
					for (Responsavel resp : cand.getListaResponsavel()) {
						resp.inativaResponsavel();
						responsavelDao.alterar(resp);
					}
				}
				candidato.desativa();
				candidatoDao.alterar(candidato);
				UtilFaces.addMensagemFaces("Candidato desativado com sucesso!");
				listarTodosCandidatos();
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Ocorreu uma falha ao tentar inativar o candidato.");
			UtilLog.getLog().error(e);
		}
	}

	public void alterarCandidato(Pessoa candidato) {
		try {
			listaCandidato.contains(candidato);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void excluir(Candidato candidato) {
		try {
			candidatoDao.excluirPorId(candidatoDao.consultarCandidatoCompleto(candidato).getId());
			UtilFaces.addMensagemFaces("Candidato excluido com sucesso!");
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	// carrega o objeto do candidato completo
	public void carregaCandidatoAlteracao(Candidato candidato) throws PersistenciaException {
		this.responsavel = new Responsavel();
		this.candidato = candidatoDao.consultarCandidatoCompleto(candidato);
		verificaIdade(this.candidato);
	}

	public void carregaEnderecoAlteracao(Endereco end, String tipoPessoa) {
		if (tipoPessoa.equals("Candidato") && this.candidato.getListaEndereco().contains(end)) {
			if (end.getId() == null) {
				this.endereco = end;
				this.candidato.getListaEndereco().remove(end);
			} else {
				this.endereco = enderecoDao.consultar(end.getId());
			}
		} else if (tipoPessoa.equals("Responsavel") && this.responsavel.getListaEndereco().contains(end)) {
			if (end.getId() == null) {
				this.enderecoResponsavel = end;
				this.responsavel.getListaEndereco().remove(end);
			} else {
				this.enderecoResponsavel = enderecoDao.consultar(end.getId());
			}
		}

	}

	// Aplica Filtro
	public void aplicarFiltro() {
		try {
			if (this.statusFiltro.isEmpty() && this.candidatoConsulta.isEmpty()) {
				listaCandidato = candidatoDao.listar();
			} else if(!this.candidatoConsulta.isEmpty() && this.statusFiltro.isEmpty()) {
				listaCandidato = candidatoDao.listaCandidatoPorNome(this.candidatoConsulta);
			} else if(this.candidatoConsulta.isEmpty() && !this.statusFiltro.isEmpty()) {
				listaCandidato = candidatoDao.listaPorStatus(this.statusFiltro);
			} else if(!this.candidatoConsulta.isEmpty() && !this.statusFiltro.isEmpty()) {
				listaCandidato = candidatoDao.listarPorNomeStatus(this.candidatoConsulta, this.statusFiltro);
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}

	}
	
	public void habilitaCampoObservacao() {
		if(this.candidato.getTemPrioridade() == true) {
			this.renderizaObservacao = true;
		} else {
			this.renderizaObservacao = false;
		}
		
	}

	public void renderizaCampoPeriodoConclusao() {
		System.out.println("STATUS: " + (this.candidato.getEnumStatusConcluiu()));
		if(this.candidato.getEnumStatusConcluiu().equals(EnumStatusConcluiu.CONCLUIU)) {
			this.renderizaPeriodoConclusao = true;
		} else {
			this.renderizaPeriodoConclusao = false;
		}
	}
	
	public boolean isRenderizaPeriodoConclusao() {
		return renderizaPeriodoConclusao;
	}

	public void setRenderizaPeriodoConclusao(boolean renderizaPeriodoConclusao) {
		this.renderizaPeriodoConclusao = renderizaPeriodoConclusao;
	}

	public boolean isRenderizaObservacao() {
		return renderizaObservacao;
	}

	public void setRenderizaObservacao(boolean renderizaObservacao) {
		this.renderizaObservacao = renderizaObservacao;
	}

	public String getStatusFiltro() {
		return statusFiltro;
	}

	public void setStatusFiltro(String statusFiltro) {
		this.statusFiltro = statusFiltro;
	}

	public Candidato getExibeCandInfo() {
		return exibeCandInfo;
	}

	public void setExibeCandInfo(Candidato exibeCandInfo) {
		this.exibeCandInfo = exibeCandInfo;
	}

	public Telefone getTelefoneResponsavel() {
		return telefoneResponsavel;
	}

	public void setTelefoneResponsavel(Telefone telefoneResponsavel) {
		this.telefoneResponsavel = telefoneResponsavel;
	}

	public Telefone getTelefoneEmergencia() {
		return telefoneEmergencia;
	}

	public void setTelefoneEmergencia(Telefone telefoneEmergencia) {
		this.telefoneEmergencia = telefoneEmergencia;
	}

	public Telefone getTelefoneCelularCandidato() {
		return telefoneCelularCandidato;
	}

	public void setTelefoneCelularCandidato(Telefone telefoneCelularCandidato) {
		this.telefoneCelularCandidato = telefoneCelularCandidato;
	}

	public Telefone getTelefoneResidencialCandidato() {
		return telefoneResidencialCandidato;
	}

	public void setTelefoneResidencialCandidato(Telefone telefoneResidencialCandidato) {
		this.telefoneResidencialCandidato = telefoneResidencialCandidato;
	}

	public List<SelectItem> getCompleteEnumStatusConcluiu() {
		return UtilFaces.getListEnum(EnumStatusConcluiu.values());
	}
	
	public List<SelectItem> getCompleteEnumStatusDesistiu() {
		return UtilFaces.getListEnum(EnumStatusDesistiu.values());
	}
	
	public List<SelectItem> getCompleteEnumTipoEtnia() {
		return UtilFaces.getListEnum(EnumTipoEtnia.values());
	}

	public List<SelectItem> getCompleteEnumFiliacao() {
		return UtilFaces.getListEnum(EnumFiliacao.values());
	}

	public List<SelectItem> getCompleteEnumTipoCasa() {
		return UtilFaces.getListEnum(EnumTipoCasa.values());
	}

	public List<SelectItem> getCompleteEnumEstado() {
		return UtilFaces.getListEnum(EnumEstado.values());
	}

	public List<SelectItem> getCompleteEnumSexo() {
		return UtilFaces.getListEnum(EnumSexo.values());
	}

	public List<SelectItem> getCompleteEnumEscolaridade() {
		return UtilFaces.getListEnum(EnumEscolaridade.values());
	}

	public List<SelectItem> getCompleteEnumTipoTelefone() {
		return UtilFaces.getListEnum(EnumTipoTelefone.values());
	}

	public List<SelectItem> getCompleteEnumTipoMoradia() {
		return UtilFaces.getListEnum(EnumTipoCasa.values());
	}

	public List<SelectItem> getCompleteEnumEstadoCivil() {
		return UtilFaces.getListEnum(EnumEstadoCivil.values());
	}

	public List<SelectItem> getCompleteEnumPrioridade() {
		return UtilFaces.getListEnum(EnumPrioridade.values());
	}

	public List<SelectItem> getCompleteEnumParticipacaoEconomicaFamilia() {
		return UtilFaces.getListEnum(EnumParticipacaoEconomicaFamilia.values());
	}

	public List<SelectItem> getCompleteEnumPNE() {
		return UtilFaces.getListEnum(EnumPNE.values());
	}

	public List<Telefone> getListaTelefone() {
		return responsavel.getListaTelefone();
	}

	public void gerarPdf() {
		return;
	}

	public boolean isMaiorDeIdade() {
		return maiorDeIdade;
	}

	public void setMaiorDeIdade(boolean maiorDeIdade) {
		this.maiorDeIdade = maiorDeIdade;
	}

	public Endereco getEnderecoResponsavel() {
		return enderecoResponsavel;
	}

	public void setEnderecoResponsavel(Endereco enderecoResponsavel) {
		this.enderecoResponsavel = enderecoResponsavel;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setTotalRenda(BigDecimal totalRenda) {
		this.totalRenda = totalRenda;
	}

	private boolean EhTelefoneConsistente() {
		if (this.telefoneResponsavel.getNomePessoaRecado() != null
				&& !this.telefoneResponsavel.getNomePessoaRecado().isEmpty()
				&& this.telefoneResponsavel.getNumeroTelefone() != null
				&& !this.telefoneResponsavel.getNumeroTelefone().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	private boolean EhEnderecoConsistente(String enderecoCandidatoOuResponsavel) {
		if (enderecoCandidatoOuResponsavel.equals("Candidato")) {
			if (endereco.getCidade().getNomeCidade().isEmpty() && endereco.getBairro().isEmpty()) {
				UtilFaces.addMensagemFaces("Cidade é obrigatório.");
				UtilFaces.addMensagemFaces("Bairro é obrigatório.");
				return false;
			} else if (endereco.getCidade().getNomeCidade().isEmpty() && !(endereco.getBairro().isEmpty())) {
				UtilFaces.addMensagemFaces("Cidade é obrigatório.");
				return false;
			} else if (endereco.getBairro().isEmpty() && !(endereco.getCidade().getNomeCidade().isEmpty())) {
				UtilFaces.addMensagemFaces("Bairro é obrigatório.");
				return false;
			} else {
				return true;
			}
		} else if (enderecoCandidatoOuResponsavel.equals("Responsavel")) {
			if (enderecoResponsavel.getCidade().getNomeCidade().isEmpty()
					&& enderecoResponsavel.getBairro().isEmpty()) {
				UtilFaces.addMensagemFaces("Cidade é obrigatório.");
				UtilFaces.addMensagemFaces("Bairro é obrigatório.");
				return false;
			} else if (enderecoResponsavel.getCidade().getNomeCidade().isEmpty()
					&& !(enderecoResponsavel.getBairro().isEmpty())) {
				UtilFaces.addMensagemFaces("Cidade é obrigatório.");
				return false;
			} else if (enderecoResponsavel.getBairro().isEmpty()
					&& !(enderecoResponsavel.getCidade().getNomeCidade().isEmpty())) {
				UtilFaces.addMensagemFaces("Bairro é obrigatório.");
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	public void validaDesistiuConcluiu(){
		if(this.candidato.getEnumEscolaridade().equals(EnumEscolaridade.FUNDAMENTAL) || this.candidato.getEnumEscolaridade().equals(EnumEscolaridade.MEDIO) || this.candidato.getEnumEscolaridade().equals(EnumEscolaridade.SUPERIOR)) {
			this.candidato.setEnumStatusDesistiu(EnumStatusDesistiu.EM_BRANCO);
			this.candidato.setAnoDeDesistencia(null);
		} else {
			this.renderizaPeriodoConclusao = false;
			this.candidato.setEnumStatusConcluiu(EnumStatusConcluiu.EM_BRANCO);
			this.candidato.setAnoDeConclusao(null);
		}
	}

	public boolean validarCandidato(Candidato candidato) {
		if (candidato.getNomePessoa().isEmpty() && candidato.getNaturalidade().isEmpty()) {
			UtilFaces.addMensagemFaces("Por favor, informe o nome do candidato e a naturalidade");
			return false;
		} else {
			return true;
		}
	}

	public boolean validaCandidatoNome() {
		if (this.candidato.getNomePessoa().isEmpty()) {
			UtilFaces.addMensagemFaces("Nome de candidato é obrigatório.");
			return false;
		} else {
			return true;
		}
	}

	public void validaCandidatoCPF(FacesContext fc, UIComponent uc, Object ob) {
		String cpf = (String) ob;
		if (cpf.isEmpty()) {
			((UIInput) uc).setValid(false);
			UtilFaces.addMensagemFaces("CPF de candidato é obrigatório.");
		}
	}

	private boolean EhResponsavelConsistente() {
		if (this.responsavel.getNomePessoa() != null && !this.responsavel.getNomePessoa().isEmpty()
				&& this.responsavel.getCpf() != null && !this.responsavel.getCpf().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public Pessoa getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public List<Candidato> getListaCandidato() {
		return listaCandidato;
	}

	public void setListaCandidato(List<Candidato> listaCandidato) {
		this.listaCandidato = listaCandidato;
	}

	public List<Responsavel> getListaResponsavel() {
		return listaResponsavel;
	}

	public String getCandidatoConsulta() {
		return candidatoConsulta;
	}

	public void setCandidatoConsulta(String candidatoConsulta) {
		this.candidatoConsulta = candidatoConsulta;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<SelectItem> getCompleteEnumReside() {
		return UtilFaces.getListEnum(EnumReside.values());
	}

	public List<SelectItem> getCompleteEnumResidencia() {
		return UtilFaces.getListEnum(EnumResidencia.values());
	}

	public List<SelectItem> getCompleteEnumAcessoComputador() {
		return UtilFaces.getListEnum(EnumAcessoComputador.values());
	}

	public List<SelectItem> getCompleteEnumTemCelular() {
		return UtilFaces.getListEnum(EnumTemCelular.values());
	}

	public List<SelectItem> getCompleteEnumTemInternet() {
		return UtilFaces.getListEnum(EnumTemInternet.values());
	}

	public List<SelectItem> getCompleteEnumLocalInternet() {
		return UtilFaces.getListEnum(EnumLocalInternet.values());
	}

	public void aplicaValorEmBranco() {
		if (this.candidato.getEnumTemInternet() == EnumTemInternet.NAO) {
			this.candidato.setEnumLocalInternet(EnumLocalInternet.EM_BRANCO);
		}
		if (this.candidato.getEnumPNE() == EnumPNE.NAO) {
			this.candidato.setParentescoPNE("");
			this.candidato.setNecessidadePNE("");
		}
		if (this.candidato.getEnumResidencia() != EnumResidencia.OUTROS) {
			this.candidato.setResidenciaOutro("");
		}
	}
}
