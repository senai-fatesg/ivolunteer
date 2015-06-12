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
import br.com.ambientinformatica.ivolunteer.entidade.Aluno;
import br.com.ambientinformatica.ivolunteer.entidade.Cidade;
import br.com.ambientinformatica.ivolunteer.entidade.Endereco;
import br.com.ambientinformatica.ivolunteer.entidade.EnumEstado;
import br.com.ambientinformatica.ivolunteer.entidade.EnumSexo;
import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;
import br.com.ambientinformatica.ivolunteer.persistencia.AlunoDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Controller("AlunoControl")
@Scope("conversation")
public class AlunoControl {

	private Aluno aluno = new Aluno();
	Cidade cidade = new Cidade();
	Endereco endereco = new Endereco();

	@Autowired
	private AlunoDao alunoDao;

	private List<Aluno> alunos = new ArrayList<Aluno>();

	@PostConstruct
	public void init() {
		listar(null);
	}

	public void confirmar(ActionEvent evt) {
		try {
			alunoDao.alterar(aluno);
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

	public Aluno consultar(ActionEvent evt) {
		try {

			alunoDao.consultar(aluno.getCertidaoNascimento());

		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
		return aluno;
	}

	public void excluir(Aluno aluno) {
		try {
			alunoDao.excluirPorId(aluno.getId());
			UtilFaces.addMensagemFaces("Aluno excluido com sucesso!");
		} catch (PersistenciaException e) {
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<SelectItem> getCompleteEnumSexo() {
		return UtilFaces.getListEnum(EnumSexo.values());
	}

	public List<SelectItem> getCompleteEnumEstado() {
		return UtilFaces.getListEnum(EnumEstado.values());
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
			this.aluno.removerEndereco(endereco);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	// Aplica Filtro
	public void aplicarFiltro(ActionEvent evt) {
		try {
			if (this.aluno.getCertidaoNascimento().isEmpty()) {
				this.alunos = this.alunoDao.listar();
			} else {
				this.alunos = this.alunoDao.listarPorCertidao(this.aluno
						.getCertidaoNascimento());
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void carregaAlunoAlteracao(Aluno aluno) {
		try {
			this.aluno = alunoDao.consultar(aluno.getId());
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
}
