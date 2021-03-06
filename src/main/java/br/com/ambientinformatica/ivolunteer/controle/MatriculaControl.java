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
import br.com.ambientinformatica.ivolunteer.entidade.Curso;
import br.com.ambientinformatica.ivolunteer.entidade.Endereco;
import br.com.ambientinformatica.ivolunteer.entidade.EnumEstado;
import br.com.ambientinformatica.ivolunteer.entidade.EnumFiliacao;
import br.com.ambientinformatica.ivolunteer.entidade.EnumSexo;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoEtnia;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoPessoa;
import br.com.ambientinformatica.ivolunteer.entidade.Matricula;
import br.com.ambientinformatica.ivolunteer.entidade.Responsavel;
import br.com.ambientinformatica.ivolunteer.entidade.Turma;
import br.com.ambientinformatica.ivolunteer.persistencia.AlunoDao;
import br.com.ambientinformatica.ivolunteer.persistencia.CandidatoDao;
import br.com.ambientinformatica.ivolunteer.persistencia.CursoDao;
import br.com.ambientinformatica.ivolunteer.persistencia.MatriculaDao;
import br.com.ambientinformatica.ivolunteer.persistencia.TurmaDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Controller("MatriculaControl")
@Scope("conversation")
public class MatriculaControl {

	private Aluno aluno = new Aluno();
	private Aluno infoAluno = new Aluno();
	private Candidato candidato = new Candidato();
	private Candidato infoCandidato = new Candidato();
	private Cidade cidade = new Cidade();
	private Endereco endereco = new Endereco();
	private Responsavel responsavel = new Responsavel();
	private String dataReal;
	private Matricula matricula = new Matricula();
	private Curso curso;
	private Turma turma;
	
//	private String filtroNome;
//	private String filtroStatus;

	@Autowired
	private AlunoDao alunoDao;
	@Autowired
	private MatriculaDao matriculaDao;
	@Autowired
	private CandidatoDao candidatoDao;
	@Autowired
	private CursoDao cursoDao;
	@Autowired
	private TurmaDao turmaDao;

	private List<Matricula> matriculas = new ArrayList<Matricula>();
	private List<Aluno> alunos = new ArrayList<Aluno>();
	private List<Candidato> candidatos = new ArrayList<Candidato>();
	private List<Turma> turmas = new ArrayList<Turma>();

	private List<String> images;
	private List<String> tabelaFiltrada;

	public List<String> getTabelaFiltrada() {
		return tabelaFiltrada;
	}
	
	public List<Curso> autoCompleteCursos(String textFormulario) {
		List<Curso> cursos = new ArrayList<Curso>();
		//cursos = cursoDao.buscaCursoPorNome(textFormulario);
		return cursos;
	}
	
	public void carregaTurmasPorCurso() {
		System.out.println("ENTREI POHA! TAUKEI?");
		this.turmas = turmaDao.buscaTurmasAtivas(this.curso);
	}
	
	public void setTabelaFiltrada(List<String> tabelaFiltrada) {
        this.tabelaFiltrada = tabelaFiltrada;
    }

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Aluno getInfoAluno() {
		return infoAluno;
	}

	public void setInfoAluno(Aluno infoAluno) {
		this.infoAluno = infoAluno;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public Candidato getInfoCandidato() {
		return infoCandidato;
	}

	public void setInfoCandidato(Candidato infoCandidato) {
		this.infoCandidato = infoCandidato;
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

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
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
		// listarAlunos(null);
		// aplicarFiltroAluno(null);
		// aplicarFiltroCandidato(null);
		listarCandidatos();
		// addImage();
	}

	public List<Aluno> getAlunos() {
		this.alunos = alunoDao.listar();
		// listarAlunos(null);
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

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public List<String> getImages() {
		return images;
	}
	
	private boolean validarNome() {
		if (this.aluno.getNomePessoa().isEmpty()) {
			UtilFaces.addMensagemFaces("Nome do Aluno é obrigatório!");
			return false;
		} else {
			return true;			
		}
	}

	public void salvar(ActionEvent evt) {
		try {
			if (this.validarNome()) {				
				if (this.matricula.getId() == null) {
					this.aluno.addResponsavel(this.getResponsavel());
					matriculaDao.incluir(this.matricula);
					aluno.setMatricula(this.matricula);
					alunoDao.alterar(this.aluno);
				} 
				this.limparFormulario();
				UtilFaces.addMensagemFaces("Aluno salvo com sucesso!");
			}
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void alterar(ActionEvent evt) {
		try {
			if (this.validarNome()) {
				matriculaDao.alterar(this.matricula);
				this.aluno.setMatricula(this.matricula);
				alunoDao.alterar(this.aluno);	
				this.limparFormulario();
				UtilFaces.addMensagemFaces("Aluno alterado com sucesso!");
//				listar(evt);
//				matricula = new Matricula();
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void excluir(Aluno aluno) {
		try {
			Aluno alunoDesativado = this.alunoDao.consultar(aluno.getId());
			alunoDesativado.desativa();
			
			this.alunoDao.alterar(alunoDesativado);
			
			UtilFaces.addMensagemFaces("Aluno excluído com sucesso!");
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}

	}
	
	public void limparFormulario() {
		this.dataReal = "";
		this.aluno = new Aluno();
		this.responsavel = new Responsavel();
		this.endereco = new Endereco();
		this.matricula = new Matricula();
	}

	public void carregaAlunoAlteracao(Aluno aluno) {
		try {
			this.aluno = alunoDao.consultarAlunoComResponsavel(aluno);
			this.matricula = this.aluno.getMatricula();
			this.responsavel.setNomePessoa(this.aluno.getListaResponsavel().get(0).getNomePessoa());
			this.responsavel.setEnumFiliacao(this.aluno.getListaResponsavel().get(0).getEnumFiliacao());
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void carregaCandidatoParaAlunoMatriculado(Candidato candidato) {
		this.candidato = candidatoDao.consultarCandidatoCompleto(candidato);

		this.aluno.setNomePessoa(this.candidato.getNomePessoa());
		this.aluno.setDataNascimento(this.candidato.getDataNascimento());
		this.aluno.setEnumSexo(this.candidato.getEnumSexo());
		this.aluno.setEtnia(this.candidato.getEtnia());
		this.aluno.setNaturalidade(this.candidato.getNaturalidade());
		this.aluno.setCpf(this.candidato.getCpf());
		this.aluno.setRg(this.candidato.getRg());
		this.aluno.setOrgaoExpeditor(this.candidato.getOrgaoExpeditor());

		if (this.candidato.getListaResponsavel().isEmpty()) {
			return;
		} else {
			this.setResponsavel(this.candidato.getListaResponsavel().get(0));
		}

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

	public void listar(ActionEvent evt) {
		try {
			matriculas = matriculaDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void listarAlunos(ActionEvent evt) {
		try {
			alunos = alunoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

//	public void aplicarFiltroAluno(ActionEvent evt) {
//		try {
//			if (this.validarFiltroPorNome()) {
//				// this.matriculas
//			} else if (this.validarFiltroPorStatus()) {
//
//			} else {
//
//			}
//		} catch (Exception e) {
//			UtilFaces.addMensagemFaces(e);
//		}
//	}

//	public void aplicarFiltroCandidato(ActionEvent evt) {
//		try {
//			if (this.validarFiltroPorNome()) {
//				this.candidatos = this.candidatoDao.listaCandidatoPorNome(this.filtroNome);
//			} else if (this.validarFiltroPorStatus()) {
//				this.candidatos = this.candidatoDao.listaPorStatus(this.filtroStatus);
//			} else if (this.validarFiltroPorNomeEStatus()) {
//				this.candidatos = this.candidatoDao.listarPorNomeStatus(this.filtroNome, this.filtroStatus);
//			} else {
//				this.candidatos = this.candidatoDao.listar();
//			}
//		} catch (Exception e) {
//			UtilFaces.addMensagemFaces(e);
//		}
//	}

//	public boolean validarFiltroPorNome() {
//		if (!this.filtroNome.isEmpty() && this.filtroStatus.isEmpty()) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	public boolean validarFiltroPorStatus() {
//		if (!this.filtroStatus.isEmpty() && this.filtroNome.isEmpty()) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	public boolean validarFiltroPorNomeEStatus() {
//		if (!this.filtroNome.isEmpty() && !this.filtroStatus.isEmpty()) {
//			return true;
//		} else {
//			return false;
//		}
//	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
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

	public List<SelectItem> getCompleteEnumFiliacao() {
		return UtilFaces.getListEnum(EnumFiliacao.values());
	}

	public List<SelectItem> getCompleteEnumTipoEtnia() {
		return UtilFaces.getListEnum(EnumTipoEtnia.values());
	}

//	public String getFiltroNome() {
//		return filtroNome;
//	}
//
//	public void setFiltroNome(String nomeDoCandidato) {
//		this.filtroNome = nomeDoCandidato;
//	}
//
//	public String getFiltroStatus() {
//		return filtroStatus;
//	}
//
//	public void setFiltroStatus(String filtroStatus) {
//		this.filtroStatus = filtroStatus;
//	}

	public void calculaIdadeReal(SelectEvent event) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formato.format(aluno.getDataNascimento());
		this.dataReal = aluno.CalcularIdadeReal(dataFormatada);

	}

	public void listarCandidatos() {
		this.candidatos = candidatoDao.listaCandidatosComResponsavel();
	}

	public void exibiInformacoesCandidato(Candidato candidato) {
		this.infoCandidato = candidatoDao.consultarCandidatoCompleto(candidato);
	}
	
	public void exibiInformacoesAluno(Aluno aluno) {
		this.infoAluno = alunoDao.consultar(aluno.getId());
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formato.format(aluno.getDataNascimento());
		this.dataReal = this.aluno.CalcularIdadeReal(dataFormatada);
	}

//	public void addImage() {
//		this.images = new ArrayList<String>();
//		this.images.add("/resources/images/default-user.png");
//	}

}
