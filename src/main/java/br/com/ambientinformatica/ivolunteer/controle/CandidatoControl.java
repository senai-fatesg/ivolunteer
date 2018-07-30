package br.com.ambientinformatica.ivolunteer.controle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

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
import br.com.ambientinformatica.ivolunteer.entidade.EnumTemCelular;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTemInternet;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoCasa;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoPessoa;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoTelefone;
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.entidade.Responsavel;
import br.com.ambientinformatica.ivolunteer.entidade.Telefone;
import br.com.ambientinformatica.ivolunteer.persistencia.CandidatoDao;
import br.com.ambientinformatica.ivolunteer.persistencia.EnderecoDao;
import br.com.ambientinformatica.ivolunteer.persistencia.ResponsavelDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.util.UtilLog;

@Controller("CandidatoControl")
@Scope("conversation")
public class CandidatoControl {

	private Candidato candidato = new Candidato();
	private Responsavel responsavel = new Responsavel();
	private Candidato filtro = new Candidato();

	// instancia de candidato carregada para realização de consulta
	private String candidatoConsulta;

	// lista utilizada na consulta do candidato
	private List<Candidato> listaCandidato = new ArrayList<Candidato>();

	// listas e utilizada para a apresentação na grid
	private List<Responsavel> listaResponsavel = new ArrayList<Responsavel>();
	private List<Telefone> listaTelefone = new ArrayList<Telefone>();
	// objetos utilizados para tratamento das listas
	private Telefone telefone = new Telefone();
	private Endereco endereco = new Endereco();
	private Endereco enderecoResponsavel = new Endereco();
	private Cidade cidade = new Cidade();

	// Atributo utilziado para tratamento de renda total do responsavel
	private BigDecimal totalRenda = BigDecimal.ZERO;

	// Flags para indicar se existe alguma alteração em andamento no formulario
	private boolean editando;
	
	@Autowired
	private CandidatoDao candidatoDao;

	@Autowired
	private ResponsavelDao responsavelDao;

	@Autowired
	private EnderecoDao enderecoDao;

	@PostConstruct
	public void init() {
		listarTodosCandidatos();
		responsavel = new Responsavel();
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
			this.listaCandidato = candidatoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void confirmar(ActionEvent evt) {
		try {
			if (this.candidato.getId() == null) {
				this.candidato.setEnumTipoPessoa(EnumTipoPessoa.CANDIDATO);
				this.candidato.addResponsavel(this.responsavel);
				validarCandidato(this.candidato);
				candidatoDao.incluir(this.candidato);
			} else {
				this.candidato.setEnumTipoPessoa(EnumTipoPessoa.CANDIDATO);
				validarCandidato(this.candidato);
				candidatoDao.alterar(this.candidato);
			}
			this.candidato = new Candidato();
			this.endereco = new Endereco();
			this.enderecoResponsavel = new Endereco();
			this.responsavel = new Responsavel();
			UtilFaces.addMensagemFaces("Informações salvas com sucesso!");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e.getMessage());
			UtilFaces.addMensagemFaces("Ocorreu uma falha ao tentar gravar as informações de candidato");
		}
	}

	/**
	 * Preenche os dados do candidato automaticamente. Obs: As informações
	 * preenchidas não afetam a integridade dos dados
	 **/

	private void preenchaInformacoesDefaultCandidato(Pessoa objeto) {

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
				responsavel.addTelefone(telefone);
				telefone = new Telefone();
				listaTelefone = responsavel.getListaTelefone();
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
		adicionarEndereco(this.candidato);
	}

	// metodo apenas para chamar o adicionarEndereco passando o responsavel
	public void adicionarEnderecoResponsavel() {
		adicionarEndereco(this.responsavel);
	}

	// adicionar um endereco para o candidato ou responsavel
	public void adicionarEndereco(Pessoa candidatoOuResponsavel) {
		try {
			if (candidatoOuResponsavel.getClass().equals(Candidato.class)) {
				System.out.println("É UM CANDIDATO!!!!!!!");
				if (EhEnderecoConsistente(candidatoOuResponsavel)) {
					if (this.endereco.getId() == null) {
						//this.endereco.setIsAtivo(true);
						candidato.addEndereco(endereco);
						candidatoDao.alterar(this.candidato);
						this.candidato = candidatoDao.consultarCandidatoCompleto(this.candidato);
						endereco = new Endereco();
						UtilFaces.addMensagemFaces("Endereço de " + this.candidato.getNomePessoa() + " adicionado.");
					} else {
						//this.candidato.addEndereco(this.endereco);
						enderecoDao.alterar(this.endereco);
						this.candidato = candidatoDao.consultarCandidatoCompleto(this.candidato);
						this.endereco = new Endereco();
						UtilFaces.addMensagemFaces("Endereço de " + this.candidato.getNomePessoa() + " atualizado.");
					}
				} else {
					UtilFaces.addMensagemFaces("Preencha os campos de endereço de candidato corretamente");
				}

			} else if (candidatoOuResponsavel.getClass().equals(Responsavel.class)) {
				if(EhResponsavelConsistente()) {
					if(candidatoOuResponsavel.getId() == null) {
						this.candidato.addResponsavel((Responsavel) candidatoOuResponsavel);
						candidatoDao.alterar(this.candidato);
						//responsavelDao.incluir((Responsavel) candidatoOuResponsavel);
					} else {
						if (EhEnderecoConsistente(candidatoOuResponsavel)) {
							if (this.enderecoResponsavel.getId() == null) {
								//this.enderecoResponsavel.setIsAtivo(true);
								this.responsavel.addEndereco(this.enderecoResponsavel);
								this.responsavelDao.alterar(this.responsavel);
								this.responsavel = responsavelDao.consultaResponsavelCompleto(this.responsavel);
								this.enderecoResponsavel = new Endereco();
								UtilFaces.addMensagemFaces("Endereço de responsável " 
								+ this.responsavel.getNomePessoa() + " adicionado.");
							} else {
								//this.responsavel.addEndereco(this.enderecoResponsavel);
								enderecoDao.alterar(this.enderecoResponsavel);
								this.responsavel = responsavelDao.consultaResponsavelCompleto(this.responsavel);
								this.enderecoResponsavel = new Endereco();
								UtilFaces.addMensagemFaces("Endereço de responsável " 
								+ this.responsavel.getNomePessoa() + " atualizado.");
							}
						} else {
							UtilFaces.addMensagemFaces("Preencha os campos de endereço de responsável corretamente");
						}
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

	/**
	 * Calcula a renda do responsavel, a renda e calculada com base na renda +
	 * rendaExtra
	 **/
	public BigDecimal getTotalRenda() {
		return responsavel.calcularRenda();
	}

	// Adionar as responsaveis ao candidato
	public void adicionarResponsavel(ActionEvent evt) {
		try {
			if (EhResponsavelConsistente()) {
				if (this.responsavel.getId() == null) {
					preenchaInformcoesDefaultResponsavel(responsavel);
					responsavel.setEnumTipoPessoa(EnumTipoPessoa.RESPONSAVEL);
					this.candidato.addResponsavel(this.responsavel);
					this.candidatoDao.alterar(this.candidato);
					// listaResponsavel = candidato.getListaResponsavel();
					this.responsavel = new Responsavel();
					UtilFaces.addMensagemFaces("Responsável adicionado.");
				} else {
					this.responsavelDao.alterar(this.responsavel);
					//this.candidato.addResponsavel(responsavelDao.consultar(this.responsavel));
					//this.candidatoDao.alterar(this.candidato);
					this.candidato = this.candidatoDao.consultarCandidatoCompleto(this.candidato);
					this.responsavel = new Responsavel();
					UtilFaces.addMensagemFaces("Responsável atualizado.");
				}

			} else {
				UtilFaces.addMensagemFaces("Preencha os dados de responsável corretamente");
			}
		} catch (Exception erro) {
			UtilFaces.addMensagemFaces("Ocorreu uma falha ao tentar incluir o Responsável na lista.");
			UtilLog.getLog().error(erro);
		}
	}

	public void carregaResponsavelAlteracao(Responsavel resp) {
		if (this.candidato.getListaResponsavel().contains(resp)) {
			this.responsavel = responsavelDao.consultar(resp.getId());
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
	public void removerEndereco(Endereco endereco, String candidatoOuResponsavel) {
		try {
			if (candidatoOuResponsavel.equals("Responsavel")) {
				endereco.inativaEndereco();
				enderecoDao.alterar(endereco);
				this.responsavel = responsavelDao.consultaResponsavelCompleto(this.responsavel);
				UtilFaces.addMensagemFaces("Endereço de responsável demovido.");
				//enderecoDao.desativaEndereco(endereco);
				//this.responsavel.getListaEndereco().remove(endereco);
				// this.responsavel.getListaEndereco().add(endAtualizado);
				// responsavel.removerEndereco(endereco);
			} else {
				endereco.inativaEndereco();
				enderecoDao.alterar(endereco);
				this.candidato.getListaEndereco().clear();
				this.candidato.getListaEndereco().addAll((enderecoDao.buscaTodosEnderecosPorCandidato(this.candidato)));
				// this.candidato.getListaEndereco().add(endAtualizado);
				// candidato.removerEndereco(endereco);
				UtilFaces.addMensagemFaces("Endereço de candidato removido.");
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Ocorreu uma falha ao tentar excluir o endereço da lista");
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
	public void removerResponsavel(Responsavel responsavel) {
		try {
			responsavel.inativaResponsavel();
			responsavelDao.alterar(responsavel);
			candidatoDao.consultarCandidatoCompleto(this.candidato);
			UtilFaces.addMensagemFaces("Responsável de " + this.candidato.getNomePessoa() + " removido.");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Ocorreu uma falha ao tentar excluir o responsavel da lista");
			UtilLog.getLog().error(e);
		}
	}

	/**
	 * Remove o objeto candidato, onde a exclusão e realizada com o cascade e é
	 * utilizado o metodo de excluir por "id".
	 **/
	public void removerCandidato(Candidato candidato) {
		try {
			if (listaCandidato.contains(candidato)) {
				// pega todas as informações do candidato para poder excluir
				// todos os dados endereços,reponsáveis.
				// this.candidato =
				// candidatoDao.consultarCandidatoCompleto(candidato);
				this.candidatoDao.excluirPorId(candidato.getId());
				listarTodosCandidatos();
				UtilFaces.addMensagemFaces("Candidato excluído com sucesso!");
				this.candidato = new Candidato();
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Ocorreu uma falha ao tentar excluir o candidato da base de dados.");
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
	}

	public void carregaEnderecoAlteracao(Endereco end, String tipoPessoa) {
		if (tipoPessoa.equals("Candidato") && this.candidato.getListaEndereco().contains(end)) {
			this.endereco = enderecoDao.consultar(end.getId());
		} else if (tipoPessoa.equals("Responsavel") && this.responsavel.getListaEndereco().contains(end)) {
			this.enderecoResponsavel = enderecoDao.consultar(end.getId());
		}

	}

	// Aplica Filtro
	public void aplicarFiltro(ActionEvent evt) {
		try {
			if (candidatoConsulta == null || candidatoConsulta.isEmpty()) {
				listaCandidato = candidatoDao.listaCandidato();
			} else {
				listaCandidato = candidatoDao.listaCandidatoPorNome(this.candidatoConsulta);
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}

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

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
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
		if (telefone.getNomePessoaRecado() != null && !telefone.getNomePessoaRecado().isEmpty()
				&& telefone.getNumeroTelefone() != null && !telefone.getNumeroTelefone().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	private boolean EhEnderecoConsistente(Pessoa enderecoCandidatoOuResponsavel) {
		if (enderecoCandidatoOuResponsavel.getClass().equals(Candidato.class)) {
			if (!endereco.getRuaOuAvenida().isEmpty() && !endereco.getBairro().isEmpty()
					&& !endereco.getQuadra().isEmpty() && !endereco.getLote().isEmpty()) {
				return true;
			} else {
				return false;
			}
		} else if (enderecoCandidatoOuResponsavel.getClass().equals(Responsavel.class)) {
			if (!enderecoResponsavel.getRuaOuAvenida().isEmpty() && !enderecoResponsavel.getBairro().isEmpty()
					&& !enderecoResponsavel.getQuadra().isEmpty() && !enderecoResponsavel.getLote().isEmpty()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public void validarCandidato(Candidato candidato) throws Exception {

		if (candidato.getNomePessoa().isEmpty()) {
			throw new Exception("Por favor, informe o nome do candidato");
		}

		if (candidato.getNaturalidade().isEmpty()) {
			throw new Exception("Por favor, informe a naturalidade do candidato");
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
	}
}
