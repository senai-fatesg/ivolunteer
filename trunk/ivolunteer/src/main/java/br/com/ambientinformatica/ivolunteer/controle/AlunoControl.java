package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Aluno;
import br.com.ambientinformatica.ivolunteer.entidade.Cidade;
import br.com.ambientinformatica.ivolunteer.entidade.Endereco;
import br.com.ambientinformatica.ivolunteer.entidade.EnumEstado;
import br.com.ambientinformatica.ivolunteer.entidade.EnumSexo;
import br.com.ambientinformatica.ivolunteer.entidade.Telefone;
import br.com.ambientinformatica.ivolunteer.persistencia.AlunoDao;

@Controller("AlunoControl")
@Scope("conversation")
public class AlunoControl {

	private Aluno aluno = new Aluno();
	Cidade cidade = new Cidade();

	@Autowired
	private AlunoDao alunoDao;
	Endereco endereco = new Endereco();

	private List<Aluno> alunos = new ArrayList<Aluno>();

	@PostConstruct
	public void init() {
		listar(null);
	}

	public void confirmar(ActionEvent evt) {
		try {
			alunoDao.alterar(aluno);
			listar(evt);
			aluno = new Aluno();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt) {
		try {
			alunos = alunoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<String> completeEnumUf(String query) {
		List<String> retornoUf = new ArrayList<String>();
		EnumEstado[] enunUf = EnumEstado.values();
		for (int i = 0; i < enunUf.length; i++) {
			retornoUf.add(enunUf[i].getDescricao());
		}
		return retornoUf;
	}

	public List<String> completeEnumSexo(String query) {
		List<String> retorno = new ArrayList<String>();
		EnumSexo[] enunSexo = EnumSexo.values();
		for (int i = 0; i < enunSexo.length; i++) {
			retorno.add(enunSexo[i].getDescricao());
		}
		return retorno;
	}

	public void adicionarListaEndereco(ActionEvent evt) {
		List<Endereco> retornoListaEndereco = new ArrayList<Endereco>();
		retornoListaEndereco.add(endereco);
		aluno.setListaEndereco(retornoListaEndereco);
	}

	public void addEndereco(ActionEvent ev) {
		try {
			this.aluno.addEndereco(endereco);
			this.endereco = new Endereco();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void removerEndereco(Endereco endereco) {
		try {
			this.aluno.removerEdereco(endereco);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

}
