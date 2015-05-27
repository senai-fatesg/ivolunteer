package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Endereco;
import br.com.ambientinformatica.ivolunteer.entidade.EnumEscolaridade;
import br.com.ambientinformatica.ivolunteer.entidade.EnumEstado;
import br.com.ambientinformatica.ivolunteer.entidade.EnumFiliacao;
import br.com.ambientinformatica.ivolunteer.entidade.EnumSexo;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoTelefone;
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.entidade.Telefone;
import br.com.ambientinformatica.ivolunteer.persistencia.PessoaDao;

@Controller("CandidatoControl")
@Scope("conversation")
public class CandidatoControl {
	//TODO corrigir os enumeradores, pegando por meio da pessoa pronto
	//TODO corrigir as grids e tira os nomes duplicados 
	//TODO termina a modelagem da tela
	//TODO realziar a implementação da grid
	//TODO realizar o primeiro insert na tabela de pessoa
	//TODO corrigir todos os atributos de pessoa que estão na classe controlador
	
	private Pessoa pessoa = new Pessoa();
	
	private List<Pessoa> listaPessoa = new ArrayList<Pessoa>();
	private Telefone telefone = new Telefone();
	private List<Telefone> listaTelefone = new ArrayList<Telefone>();
	private Endereco endereco = new Endereco();
	private List<Endereco> listaEndereco = new ArrayList<Endereco>();

	@Autowired
	private PessoaDao PessoaDao;

	@PostConstruct
	public void init() {
		listar(null);
	}

	public void confirmar(ActionEvent evt) {
		try {
			PessoaDao.alterar(pessoa);
			listar(evt);
			pessoa = new Pessoa();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	//Metodo responsavel por adicionar o telefone do responsavel
	public void adicioneTelefone(ActionEvent evt) {
		try {
			listaTelefone.add(telefone);
			this.telefone = new Telefone();
			pessoa.setListaTelefone(listaTelefone);;
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	//Metodo responsavel por adicionar o endereco do responsavel
	public void adicioneEndereco(ActionEvent evt) {
		try{
			listaEndereco.add(this.endereco);
			this.endereco = new Endereco();
			pessoa.setListaEndereco(listaEndereco);
		}catch(Exception erro){
			UtilFaces.addMensagemFaces(erro);
		}
	}
	
	//Metodo responsavel por adionar as pessoas relacionadas
	public void adicionePessoa(ActionEvent evt) {
		try{
			listaPessoa.add(this.pessoa);
			this.pessoa = new Pessoa();
			pessoa.setListaPessoaRelacionada(listaPessoa);
		}catch(Exception erro){
			UtilFaces.addMensagemFaces(erro);
		}
	}
	
	
	public void listar(ActionEvent evt) {
		try {
			listaPessoa = PessoaDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e.getMessage());
		}
	}
	
	public List<String> completeEnumFiliacao(String query) {
		List<String> retorno = new ArrayList<String>();
		EnumFiliacao[] enunsFiliacao = EnumFiliacao.values();
		for (int i = 0; i < enunsFiliacao.length; i++) {
			retorno.add(enunsFiliacao[i].getDescricao());
		}
		return retorno;
	}

	public List<String> completeEnumEstado(String query) {
		List<String> retorno = new ArrayList<String>();
		EnumEstado[] enunsEstado = EnumEstado.values();
		for (int i = 0; i < enunsEstado.length; i++) {
			retorno.add(enunsEstado[i].getDescricao());
		}
		return retorno;
	}

	public List<String> completeEnumSexo(String query) {
		List<String> retorno = new ArrayList<String>();
		EnumSexo[] enunsSexo = EnumSexo.values();
		for (int i = 0; i < enunsSexo.length; i++) {
			retorno.add(enunsSexo[i].getDescricao());
		}
		return retorno;
	}

	public List<String> completeEnumEscolaridade(String query) {
		List<String> retorno = new ArrayList<String>();
		EnumEscolaridade[] enunsEscolaridade = EnumEscolaridade.values();
		for (int i = 0; i < enunsEscolaridade.length; i++) {
			retorno.add(enunsEscolaridade[i].getDescricao());
		}
		return retorno;
	}

	public List<String> completeEnumTipoTelefone(String query) {
		List<String> retorno = new ArrayList<String>();
		EnumTipoTelefone[] enunsTipoTelefone = EnumTipoTelefone.values();
		for (int i = 0; i < enunsTipoTelefone.length; i++) {
			retorno.add(enunsTipoTelefone[i].getDescricao());
		}
		return retorno;
	}

	public void adicioneEndereco(Endereco endereco) {
		this.endereco = endereco;
		listaEndereco.add(this.endereco);
		pessoa.setListaEndereco(listaEndereco);
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

	public List<Telefone> getListaTelefone() {
		return listaTelefone;
	}

	public void setListaTelefone(List<Telefone> listaTelefone) {
		this.listaTelefone = listaTelefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Endereco> getListaEndereco() {
		return listaEndereco;
	}

	public void setListaEndereco(List<Endereco> listaEndereco) {
		this.listaEndereco = listaEndereco;
	}
}
