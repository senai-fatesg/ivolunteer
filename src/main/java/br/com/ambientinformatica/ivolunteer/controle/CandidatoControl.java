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
import br.com.ambientinformatica.ivolunteer.entidade.EnumFiliacao;
import br.com.ambientinformatica.ivolunteer.entidade.EnumSexo;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoCasa;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoPessoa;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoTelefone;
import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.entidade.Telefone;
import br.com.ambientinformatica.ivolunteer.persistencia.PessoaDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.util.UtilLog;

@Controller("CandidatoControl")
@Scope("conversation")
public class CandidatoControl {
	
	private Pessoa pessoa = new Pessoa();
	private Pessoa pessoaCandidato = new Pessoa();
	private Pessoa candidato = new Pessoa();
	private Pessoa filtro = new Pessoa();
	
	private Telefone telefone = new Telefone();
	private Endereco endereco = new Endereco();
	private Cidade cidade = new Cidade();
	private List<Endereco> listaEndereco = new ArrayList<Endereco>();
	private List<Telefone> listaTelefone = new ArrayList<Telefone>();
	private List<Pessoa> listaPessoa = new ArrayList<Pessoa>();
	private List<Pessoa> listaCandidato = new ArrayList<Pessoa>();

	private BigDecimal totalRenda = BigDecimal.ZERO;
	
	@Autowired
	private PessoaDao pessoaDao;

	@PostConstruct
	public void init() {
		listarCandidato(null);
	}

	public void listarCandidato(ActionEvent evt){
		try {
			listaCandidato = pessoaDao.listar();
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void confirmar(ActionEvent evt) {
		try {
			pessoaCandidato.calcularRenda();
			pessoaCandidato.setFiliacao(EnumFiliacao.FILHO);
			pessoaCandidato.setTipoPessoa(EnumTipoPessoa.CANDIDATO);
			pessoaDao.alterar(pessoaCandidato);
			pessoaCandidato = new Pessoa();
			UtilFaces.addMensagemFaces("Informações salvas com sucesso!");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Ocorreu uma falha ao tentar gravar as informações de candidato");
		}
	}

	//Adicionar o telefone do responsavel
	public void adicionarTelefone(ActionEvent evt) {
		try {
			pessoa.addTelefone(telefone);
			telefone = new Telefone();
			listaTelefone = pessoa.getListaTelefone();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	//Metodo responsavel por adicionar o endereco do responsavel
	public void adicionarEndereco(ActionEvent evt) {
		try{
			pessoa.addEndereco(endereco);
			endereco = new Endereco();
			listaEndereco = pessoa.getListaEndereco();
		}catch(Exception erro){
			UtilFaces.addMensagemFaces(erro);
		}
	}
	
	public BigDecimal getTotalRenda(){
		return pessoaCandidato.calcularRenda();
	}
	
	//Adionar as pessoas relacionadas ao candidato
	public void adicionarPessoa(ActionEvent evt) {
		try{
			pessoa.setTipoPessoa(EnumTipoPessoa.RESPONSAVEL);
			pessoaCandidato.addPessoa(pessoa);
			listaPessoa = pessoaCandidato.getListaPessoaRelacionada();
			pessoa = new Pessoa();
		}catch(Exception erro){
			UtilFaces.addMensagemFaces("Houve um erro ao inserir o Responsável.");
			UtilLog.getLog().error(erro);
		}
	}
	
	public void removerEndereco(Endereco endereco) {
		try {
			this.pessoa.removerEndereco(endereco);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void removerTelefone(Telefone telefone) {
		try {
			this.pessoa.removerTelefone(telefone);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void removerPessoa(Pessoa pessoa) {
		try {
			this.pessoa.removerPessoa(pessoa);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void removerCandidato(Pessoa candidato){
		try{
			if(listaCandidato.contains(candidato)){
				this.pessoaDao.excluirPorId(candidato.getId());
				listaCandidato = pessoaDao.listar();
			}
		}catch(Exception e){
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void alterarCandidato(Pessoa candidato){
		try{
			listaCandidato.contains(candidato);
		}catch(Exception e){
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void alterarTelefone(Telefone telefone){
		try{
			this.pessoa.alterarTelefone(telefone);
		}catch(Exception e){
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void alterarEndereco(Endereco endereco){
		try{
			this.pessoa.alterarEndereco(endereco);
		}catch(Exception e){
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void alterarPessoa(Pessoa pessoa){
		try{
			this.pessoa.alterarPessoa(pessoa);
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
	
	public void carregaPessoaAlteracao(Pessoa pessoa){
		try {
	      this.pessoaCandidato = pessoaDao.consultar(pessoa.getId());
      } catch (PersistenciaException e) {
      	UtilFaces.addMensagemFaces(e);
      }		
	}
	
	// Aplica Filtro
	public void aplicarFiltro(ActionEvent evt) {
		try {
			if (this.filtro.getNomePessoa() == null) {
				this.listaPessoa = this.pessoaDao.listar();
			} else {				
				this.listaPessoa = this.pessoaDao.listarPorNome(this.filtro.getNomePessoa());
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

		
	public void adicioneEndereco(Endereco endereco) {
		pessoa.addEndereco(endereco);
		endereco = new Endereco();
	}
	
	public List<Telefone> getListaTelefone() {
		return pessoa.getListaTelefone();
	}
	
	public List<Endereco> getListaEndereco() {
		return pessoa.getListaEndereco();
	}

	public List<Pessoa> getListaPessoa() {
		return this.listaPessoa;
	}
	
	public void gerarPdf(){
		return;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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

	public Pessoa getPessoaCandidato() {
		return pessoaCandidato;
	}

	public void setPessoaCandidato(Pessoa pessoaCandidato) {
		this.pessoaCandidato = pessoaCandidato;
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

	public void setTotalRenda(BigDecimal totalRenda) {
		this.totalRenda = totalRenda;
	}
	
}
