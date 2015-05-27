package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Alternativa;
import br.com.ambientinformatica.ivolunteer.entidade.Cidade;
import br.com.ambientinformatica.ivolunteer.entidade.Endereco;
import br.com.ambientinformatica.ivolunteer.entidade.EnumEstado;
import br.com.ambientinformatica.ivolunteer.entidade.EnumEstadoCivil;
import br.com.ambientinformatica.ivolunteer.entidade.EnumSexo;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoTelefone;
import br.com.ambientinformatica.ivolunteer.entidade.Frequencia;
import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;
import br.com.ambientinformatica.ivolunteer.entidade.Telefone;
import br.com.ambientinformatica.ivolunteer.persistencia.FuncionarioDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Controller("FuncionarioControl")
@Scope("conversation")
public class FuncionarioControl {

	Funcionario funcionario = new Funcionario();
	Endereco endereco = new Endereco();
	Cidade cidade = new Cidade();
	Telefone telefone = new Telefone();

	@Autowired
	private FuncionarioDao funcionarioDao;

	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();

	@PostConstruct
	public void init() {
		listar(null);
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

	public List<String> completeEnumUf(String query) {
		List<String> retornoUf = new ArrayList<String>();
		EnumEstado[] enunUf = EnumEstado.values();
		for (int i = 0; i < enunUf.length; i++) {
			retornoUf.add(enunUf[i].getDescricao());
		}
		return retornoUf;
	}

	public List<String> completeEnumTipoTelefone(String query) {
		List<String> retornoTipoTelefone = new ArrayList<String>();
		EnumTipoTelefone[] enunTelefone = EnumTipoTelefone.values();
		for (int i = 0; i < enunTelefone.length; i++) {
			retornoTipoTelefone.add(enunTelefone[i].getDescricao());
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

	public void setPresenca() {
		Frequencia f = new Frequencia();
		f.setPresenca();
		this.funcionario.getFrequencia().add(f);
	}

	public List<Funcionario> consultarFuncionario(String query) {
		List<Funcionario> func = funcionarioDao.listarPorNome(query);
		return func;
	}

}
