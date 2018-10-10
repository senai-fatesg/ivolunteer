package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ibm.icu.text.SimpleDateFormat;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Aluno;
import br.com.ambientinformatica.ivolunteer.entidade.Candidato;
import br.com.ambientinformatica.ivolunteer.entidade.Cidade;
import br.com.ambientinformatica.ivolunteer.entidade.Endereco;
import br.com.ambientinformatica.ivolunteer.entidade.EnumEstado;
import br.com.ambientinformatica.ivolunteer.entidade.EnumSexo;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoPessoa;
import br.com.ambientinformatica.ivolunteer.entidade.Matricula;
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.persistencia.AlunoDao;
import br.com.ambientinformatica.ivolunteer.persistencia.CandidatoDao;
import br.com.ambientinformatica.ivolunteer.persistencia.MatriculaDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import net.sf.jasperreports.data.mondrian.SimpleSQLDataSource;

@Controller("MatriculaControl")
@Scope("conversation")
public class MatriculaControl {

	private Aluno aluno = new Aluno();
	private Candidato candidato = new Candidato();
	private Cidade cidade = new Cidade();
	private Endereco endereco = new Endereco();
	private String dataReal;
	private Matricula matricula = new Matricula();
	private String certidaoNascimento; 
	private String pesquisaNomeCandidato;

	@Autowired
	private AlunoDao alunoDao;
	@Autowired
	private MatriculaDao matriculaDao;
	@Autowired
	private CandidatoDao candidatoDao;

	private List<Matricula> matriculas = new ArrayList<Matricula>();
	private List<Aluno> alunos = new ArrayList<Aluno>();
	private List<Candidato> candidatos = new ArrayList<Candidato>();

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
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

	public CandidatoDao getCandidatoDao() {
		return candidatoDao;
	}

	public void setCandidatoDao(CandidatoDao candidatoDao) {
		this.candidatoDao = candidatoDao;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}
	
	@PostConstruct
	public void init() {
//		listarAlunos(null);
		aplicarFiltro(null);
		listarCandidatos();
	}
	
	public List<Aluno> getAlunos() {
		this.certidaoNascimento = "";
		this.alunos = alunoDao.listar();
//		listarAlunos(null);
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	public List<Candidato> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<Candidato> candidatos) {
		this.candidatos = candidatos;
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
	
	public void carregaCandidatoParaAlunoMatriculado(Candidato candidato) {
		this.candidato = candidatoDao.consultarCandidatoCompleto(candidato);
		
		this.aluno.setNomePessoa(this.candidato.getNomePessoa());
		this.aluno.setDataNascimento(this.candidato.getDataNascimento());
		this.aluno.setEnumSexo(this.candidato.getEnumSexo());
		this.aluno.setNaturalidade(this.candidato.getNaturalidade());
		this.aluno.setCpf(this.candidato.getCpf());
		this.aluno.setRg(this.candidato.getRg());
		this.aluno.setOrgaoExpeditor(this.candidato.getOrgaoExpeditor());
		this.aluno.getListaEndereco().addAll(this.candidato.getListaEndereco());
		
		this.calculaIdadeReal(null);
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
			if(certidaoNascimento.equals("")){
				this.alunos = this.alunoDao.listar();
			} else {
				this.alunos = this.alunoDao.listarPorCertidao(certidaoNascimento);					
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

	public String getCertidaoNascimento() {
		return certidaoNascimento;
	}

	public void setCertidaoNascimento(String certidaoNascimento) {
		this.certidaoNascimento = certidaoNascimento;
	}
	public String getPesquisaNomeCandidato() {
		return pesquisaNomeCandidato;
	}

	public void setPesquisaNomeCandidato(String nomeDoCandidato) {
		this.pesquisaNomeCandidato = nomeDoCandidato;
	}

	public void calculaIdadeReal(SelectEvent event){
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formato.format(aluno.getDataNascimento());
		this.dataReal = aluno.CalcularIdadeReal(dataFormatada);
		
	}
	
	public void listarCandidatos() {
		this.candidatos = candidatoDao.listaCandidatosComResponsavel();
	}
	
}
	

