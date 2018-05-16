package br.com.ambientinformatica.ivolunteer.controle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Cidade;
import br.com.ambientinformatica.ivolunteer.entidade.Endereco;
import br.com.ambientinformatica.ivolunteer.entidade.EnumEscolaridade;
import br.com.ambientinformatica.ivolunteer.entidade.EnumEstado;
import br.com.ambientinformatica.ivolunteer.entidade.EnumEstadoCivil;
import br.com.ambientinformatica.ivolunteer.entidade.EnumFiliacao;
import br.com.ambientinformatica.ivolunteer.entidade.EnumPrioridade;
import br.com.ambientinformatica.ivolunteer.entidade.EnumSexo;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoCasa;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoPessoa;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoTelefone;
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.entidade.Responsavel;
import br.com.ambientinformatica.ivolunteer.entidade.Telefone;
import br.com.ambientinformatica.ivolunteer.persistencia.PessoaDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.util.UtilLog;

@Controller("CandidatoControl")
@Scope("conversation")
public class CandidatoControl {
	
	private Pessoa candidato = new Pessoa();
	private Responsavel responsavel;
	private Pessoa filtro = new Pessoa();
	
	//instancia de candidato carregada para realização de consulta
	private Pessoa candidatoConsulta = new Pessoa();
	
	//lista utilizada na consulta do candidato
	private List<Pessoa> listaCandidato = new ArrayList<Pessoa>();
	
	//listas e utilizada para a apresentação na grid
	private List<Responsavel> listaResponsavel = new ArrayList<Responsavel>();
	private List<Endereco> listaEndereco = new ArrayList<Endereco>();
	private List<Telefone> listaTelefone = new ArrayList<Telefone>();
	
	//objetos utilizados para tratamento das listas
	private Telefone telefone = new Telefone();
	private Endereco endereco = new Endereco();
	private Cidade cidade = new Cidade();
	//Atributo utilziado para tratamento de renda total do responsavel
	private BigDecimal totalRenda = BigDecimal.ZERO;
	
	@Autowired
	private PessoaDao pessoaDao;
	
	@PostConstruct
	public void init() {
		listarCandidatoPorNome(null);

		responsavel = new Responsavel();
	}

	public void listarCandidatoPorNome(ActionEvent evt){
		try {
			//listaCandidato = pessoaDao.listaCandidatoPorNome(candidatoConsulta.getNomePessoa());
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void confirmar(ActionEvent evt) {
		try {
		//	preenchaInformacoesDefaultCandidato(candidato);
			candidato.setEnumTipoPessoa(EnumTipoPessoa.CANDIDATO);
			validarCandidato(candidato);
			pessoaDao.alterar(candidato);
			candidato = new Pessoa();
			
			UtilFaces.addMensagemFaces("Informações salvas com sucesso!");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e.getMessage());
			UtilFaces.addMensagemFaces("Ocorreu uma falha ao tentar gravar as informações de candidato");
		}
	}

	/**Preenche os dados do candidato automaticamente.
	 * Obs: As informações preenchidas não afetam a integridade dos dados **/
	private void preenchaInformacoesDefaultCandidato(Pessoa objeto) {
	   objeto.setEnumEstadoCivil(EnumEstadoCivil.SOLTEIRO);
	   objeto.setEnumPrioridade(EnumPrioridade.BAIXA);
	   objeto.setProfissao("NULL");
	   objeto.setRg("NULL");
	   objeto.setCpf("NULL");
   }

	//Adicionar o telefone do responsavel
	public void adicionarTelefone(ActionEvent evt) {
		try {
			if(EhTelefoneConsistente()){
				responsavel.addTelefone(telefone);
				telefone = new Telefone();
				listaTelefone = responsavel.getListaTelefone();
			}else{
				UtilFaces.addMensagemFaces("Preencha os campos corretamente");
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Ocorreu uma falha ao tentar incluir o contato na lista");
			UtilLog.getLog().error(e);
		}
	}
	
	//adicionar o endereco do responsavel
	public void adicionarEndereco(ActionEvent evt) {
		try{
			if(EhEnderecoConsistente()){
				responsavel.addEndereco(endereco);
				endereco = new Endereco();
				listaEndereco = responsavel.getListaEndereco();
			}else{
				UtilFaces.addMensagemFaces("Preencha os campos corretamente");
			}
		}catch(Exception erro){
			UtilFaces.addMensagemFaces("Ocorreu uma falha ao tentar incluir o endereço na lista");
			UtilLog.getLog().error(erro);
		}
	}
	
	/**Calcula a renda do responsavel, a renda e calculada com base na renda + rendaExtra**/
	public BigDecimal getTotalRenda(){
		return responsavel.calcularRenda();
	}
	
	//Adionar as responsaveis ao candidato
	public void adicionarResponsavel(ActionEvent evt) {
		try{
			if(EhResponsavelConsistente()){
				preenchaInformcoesDefaultResponsavel(responsavel);
				responsavel.setEnumTipoPessoa(EnumTipoPessoa.RESPONSAVEL);
				candidato.addResponsavel(responsavel);
				listaResponsavel = candidato.getListaResponsavel();
				//responsavel = new Responsavel();
			}else{
				UtilFaces.addMensagemFaces("Preencha os campos corretamente");
			}
		}catch(Exception erro){
			UtilFaces.addMensagemFaces("Ocorreu uma falha ao tentar incluir o Responsável na lista.");
			UtilLog.getLog().error(erro);
		}
	}
	
	/**Preenche as informções do responsavel, as informções preenchidas não afeta a integridade dos dados**/
	private void preenchaInformcoesDefaultResponsavel(Responsavel objeto) {
	   objeto.setCertidaoNascimento("NULL");
   }

	//remove o endereço do responsavel
	public void removerEndereco(Endereco endereco) {
		try {
			responsavel.removerEndereco(endereco);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Ocorreu uma falha ao tentar excluir o endereço da lista");
			UtilLog.getLog().error(e);
		}
	}
	
	//remove o telefone do responsavel
	public void removerTelefone(Telefone telefone) {
		try {
			responsavel.removerTelefone(telefone);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Ocorreu uma falha ao tentar excluir o endereço da lista");
			UtilLog.getLog().error(e);
		}
	}
	
	//remove o responsavel do candidato
	public void removerResponsavel(Responsavel responsavel) {
		try {
			candidato.removerResponsavel(responsavel);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Ocorreu uma falha ao tentar excluir o responsavel da lista");
			UtilLog.getLog().error(e);
		}
	}
	
	/**Remove o objeto candidato, onde a exclusão e realizada com o cascade e 
	 * é utilizado o metodo de excluir por "id". **/
	public void removerCandidato(Pessoa candidato){
		try{
			if(listaCandidato.contains(candidato)){
				this.pessoaDao.excluirPorId(candidato.getId());
				listaCandidato = pessoaDao.listar();
				UtilFaces.addMensagemFaces("Candidato excluido com sucesso!");
			}
		}catch(Exception e){
			UtilFaces.addMensagemFaces("Ocorreu uma falha ao tentar excluir o candidato da base de dados.");
			UtilLog.getLog().error(e);
		}
	}
	
	public void alterarCandidato(Pessoa candidato){
		try{
			listaCandidato.contains(candidato);
		}catch(Exception e){
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void excluir(Pessoa pessoa) {
		try {
			pessoaDao.excluirPorId(pessoa.getId());
			UtilFaces.addMensagemFaces("Candidato excluido com sucesso!");
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	//carrega o objeto do candidato completo
	public void carregaPessoaAlteracao(Pessoa pessoa) throws PersistenciaException{
		this.candidato = pessoaDao.consultarPessoaCompleta(pessoa);
		listaResponsavel = candidato.getListaResponsavel();
	}
	
	// Aplica Filtro
	public void aplicarFiltro(ActionEvent evt) {
		try {
			if (candidatoConsulta.getNomePessoa() == null || candidatoConsulta.getNomePessoa().isEmpty()) {
				listaCandidato = pessoaDao.listaCandidato();
			} else {				
				listaCandidato = pessoaDao.listaCandidatoPorNome(this.candidato.getNomePessoa());
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
	
	public List<SelectItem> getCompleteEnumTipoMoradia(){
		return UtilFaces.getListEnum(EnumTipoCasa.values());
	}
	
	public List<SelectItem> getCompleteEnumEstadoCivil(){
		return UtilFaces.getListEnum(EnumEstadoCivil.values());
	}
	
	public List<SelectItem> getCompleteEnumPrioridade() {
		return UtilFaces.getListEnum(EnumPrioridade.values());
	}
	
	public List<Telefone> getListaTelefone() {
		return responsavel.getListaTelefone();
	}
	
	public List<Endereco> getListaEndereco() {
		return responsavel.getListaEndereco();
	}
	
	public void gerarPdf(){
		return;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
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
	
	private boolean EhTelefoneConsistente(){
		if(telefone.getNomePessoaRecado() != null && !telefone.getNomePessoaRecado().isEmpty() &&
				telefone.getNumeroTelefone() != null && !telefone.getNumeroTelefone().isEmpty()){
		    return true;
		}else{
		    return false;
		}
	}
	
	private boolean EhEnderecoConsistente(){
		if(!endereco.getRuaOuAvenida().isEmpty() && !endereco.getBairro().isEmpty() &&
				!endereco.getQuadra().isEmpty() && !endereco.getLote().isEmpty()){
		    return true;
		}else{
		    return false;
		}
	}
	
	
	
public void validarCandidato(Pessoa candidato) throws Exception{
//		
//		if (candidato.getNomePessoa().isEmpty()){
//			throw new Exception("Por favor, informe o nome do candidato");
//		}
//		
//		if (candidato.getNaturalidade().isEmpty()){
//			throw new Exception("Por favor, informe a naturalidade do candidato");
//		}
		
	}
	
	private boolean EhResponsavelConsistente(){
		if(responsavel.getNomePessoa()!= null && !responsavel.getNomePessoa().isEmpty() &&
				responsavel.getCpf()!=null && !responsavel.getCpf().isEmpty()){
		    return true;
		}else{
		    return false;
		}
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public Pessoa getCandidato() {
		return candidato;
	}

	public void setCandidato(Pessoa candidato) {
		this.candidato = candidato;
	}

	public List<Pessoa> getListaCandidato() {
		return listaCandidato;
	}

	public void setListaCandidato(List<Pessoa> listaCandidato) {
		this.listaCandidato = listaCandidato;
	}

	public List<Responsavel> getListaResponsavel() {
		return listaResponsavel;
	}
	
	public Pessoa getCandidatoConsulta() {
		return candidatoConsulta;
	}

	public void setCandidatoConsulta(Pessoa candidatoConsulta) {
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
	
}
