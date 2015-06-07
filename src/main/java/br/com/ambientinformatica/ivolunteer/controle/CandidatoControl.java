package br.com.ambientinformatica.ivolunteer.controle;

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
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoTelefone;
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.entidade.Telefone;
import br.com.ambientinformatica.ivolunteer.persistencia.PessoaDao;

@Controller("CandidatoControl")
@Scope("conversation")
public class CandidatoControl {
	
	private Pessoa pessoa = new Pessoa();
	private Pessoa pessoaCandidato = new Pessoa();
	
	private Telefone telefone = new Telefone();
	private Endereco endereco = new Endereco();
	private Cidade cidade = new Cidade();
	private List<Endereco> listaEndereco = new ArrayList<Endereco>();
	private List<Telefone> listaTelefone = new ArrayList<Telefone>();
	private List<Pessoa> listaPessoa = new ArrayList<Pessoa>();

	@Autowired
	private PessoaDao PessoaDao;

	@PostConstruct
	public void init() {

	}

	public void confirmar(ActionEvent evt) {
		try {
			//alterando o candidato
			this.PessoaDao.alterar(this.pessoaCandidato);
			pessoaCandidato = new Pessoa();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	//Metodo responsavel por adicionar o telefone do responsavel
	public void adicioneTelefone(ActionEvent evt) {
		try {
			pessoa.addTelefone(telefone);
			telefone = new Telefone();
			listaTelefone = pessoa.getListaTelefone();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	//Metodo responsavel por adicionar o endereco do responsavel
	public void adicioneEndereco(ActionEvent evt) {
		try{
			pessoa.addEndereco(endereco);
			endereco = new Endereco();
			listaEndereco = pessoa.getListaEndereco();
		}catch(Exception erro){
			UtilFaces.addMensagemFaces(erro);
		}
	}
	
	//Metodo responsavel por adionar as pessoas relacionadas ao candidato
	public void adicionePessoa(ActionEvent evt) {
		try{
			pessoaCandidato.addPessoa(pessoa);
			pessoa = new Pessoa();
			listaPessoa = pessoa.getListaPessoaRelacionada();
		}catch(Exception erro){
			UtilFaces.addMensagemFaces(erro);
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
		return pessoa.getListaPessoaRelacionada();
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
}