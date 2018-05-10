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
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoPessoa;
import br.com.ambientinformatica.ivolunteer.entidade.Matricula;
import br.com.ambientinformatica.ivolunteer.persistencia.AlunoDao;
import br.com.ambientinformatica.ivolunteer.persistencia.MatriculaDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Controller("MatriculaControl")
@Scope("conversation")
public class MatriculaControl {

	private Aluno aluno = new Aluno();
	private Cidade cidade = new Cidade();
	private Endereco endereco = new Endereco();
	private String dataReal;
	private Matricula matricula = new Matricula();

	@Autowired
	private AlunoDao alunoDao;
	@Autowired
	private MatriculaDao matriculaDao;

	private List<Matricula> matriculas = new ArrayList<Matricula>();
	private List<Aluno> alunos = new ArrayList<Aluno>();

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
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

	public String getDataReal() {
		return dataReal;
	}

	public void setDataReal(String dataReal) {
		this.dataReal = dataReal;
	}

	public AlunoDao getAlunoDao() {
		return alunoDao;
	}

	public void setAlunoDao(AlunoDao alunoDao) {
		this.alunoDao = alunoDao;
	}

	public MatriculaDao getMatriculaDao() {
		return matriculaDao;
	}

	public void setMatriculaDao(MatriculaDao matriculaDao) {
		this.matriculaDao = matriculaDao;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}
	
	@PostConstruct
	public void init() {
		listarAlunos(null);
	}
	
	public List<Aluno> getAlunos() {
		listarAlunos(null);
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	public void incluir(ActionEvent evt) {
		try {
			if(this.matricula.getId() == null) {
				matriculaDao.incluir(this.matricula);
				aluno.setMatricula(this.matricula);
				alunoDao.alterar(this.aluno);
			} else {
				matriculaDao.alterar(this.matricula);
				this.aluno.setMatricula(this.matricula);
				alunoDao.alterar(this.aluno);
			}
			this.aluno = new Aluno();
			this.endereco = new Endereco();
			this.matricula = new Matricula();
			UtilFaces.addMensagemFaces("Aluno salvo com sucesso!");
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}

	}
	
	public void excluir(Aluno aluno) {
		try {
			alunoDao.excluirPorId(aluno.getId());
			UtilFaces.addMensagemFaces("Aluno excluido com sucesso!");
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}

	}
	
	public void carregaAlunoAlteracao(Aluno aluno) {
		try {
			this.aluno = alunoDao.consultar(aluno.getId());
			this.matricula = this.aluno.getMatricula();
		} catch (PersistenciaException e) {
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
	
	public void addEndereco(ActionEvent ev) {
		try {
			this.aluno.addEndereco(endereco);
			this.endereco = new Endereco();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void confirmar(ActionEvent evt) {
		try {
			matriculaDao.alterar(matricula);
			listar(evt);
			matricula = new Matricula();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt) {
		try {
			matriculas = matriculaDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void listarAlunos(ActionEvent evt) {
		try {
			alunos  = alunoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
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

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}
	
	public List<SelectItem> getCompleteEnumSexo() {
		return UtilFaces.getListEnum(EnumSexo.values());
	}
	
	public List<SelectItem> getCompleteEnumTipoPessoa() {
		return UtilFaces.getListEnum(EnumTipoPessoa.values());
	}

	public List<SelectItem> getCompleteEnumEstado() {
		return UtilFaces.getListEnum(EnumEstado.values());
	}

}
