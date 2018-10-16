package br.com.ambientinformatica.ivolunteer.controle;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Aluno;
import br.com.ambientinformatica.ivolunteer.entidade.Curso;
import br.com.ambientinformatica.ivolunteer.entidade.EnumCargo;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoFuncionario;
import br.com.ambientinformatica.ivolunteer.entidade.Frequencia;
import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;
import br.com.ambientinformatica.ivolunteer.entidade.Turma;
import br.com.ambientinformatica.ivolunteer.persistencia.AlunoDao;
import br.com.ambientinformatica.ivolunteer.persistencia.CursoDao;
import br.com.ambientinformatica.ivolunteer.persistencia.FrequenciaDao;
import br.com.ambientinformatica.ivolunteer.persistencia.FuncionarioDao;
import br.com.ambientinformatica.ivolunteer.persistencia.TurmaDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.util.UtilLog;

@Controller("FrequenciaControl")
@Scope("conversation")
public class FrequenciaControl {


	private Funcionario funcionario ;
	private Turma turma = new Turma();
    private Aluno aluno ;
	private String data = new String();
	private String dataAluno = new String();
	
	@Autowired
	private CursoDao cursoDao;
	@Autowired
	private TurmaDao turmaDao;
	@Autowired
	private FuncionarioDao funcionarioDao;
	@Autowired
	private FrequenciaDao frequenciaDao;
	
	@Autowired
	private AlunoDao alunoDao;

	private Date dataFiltro = new Date();
	private Frequencia dataFrequenciaEducador = new Frequencia();
	private List<Curso> listaCursos = new ArrayList<Curso>();
	private List<Frequencia> frequenciasF = new ArrayList<Frequencia>();
	private List<Frequencia> frequenciasA = new ArrayList<Frequencia>();
	private List<Funcionario> educadores = new ArrayList<Funcionario>();
	private List<Aluno> alunos = new ArrayList<Aluno>();
	private Curso exibeCursoInfo;
	private List<Turma> turmasAtivasDoCurso = new ArrayList<Turma>();
	
	@PostConstruct
	public void init() {
		listarCursos();
		listarEducadores();
	}
	
	public void exibeInfoDoCurso(Curso curso) {
		this.exibeCursoInfo = cursoDao.buscaCursoComListaDeTurmasAtivas(curso);
		this.turmasAtivasDoCurso  = turmaDao.buscaTurmasAtivas(curso);
	}
	
	public void listarEducadores() {
		this.educadores = funcionarioDao.listarEducadoresAtivos(EnumCargo.EDUCADOR);
	}
	
	public void listarCursos() {
		listaCursos = cursoDao.listar();
	}
	
	public void exibeInfoFrequenciaEducador(Funcionario educador) {
		this.funcionario = funcionarioDao.carregarFuncionarioComEnderecoTelefone(educador);
	}
	
	public void exibeDiasDoMes() {
		//Calendar filtro = this.frequenciaFiltro.getData();
		Calendar auxiliar = Calendar.getInstance();
		auxiliar.setTime(this.dataFiltro);
		
		YearMonth AnoMes = YearMonth.of(auxiliar.get(Calendar.YEAR), (auxiliar.get(Calendar.MONTH) + 1));
		
		for (int i = 1; i < AnoMes.lengthOfMonth(); i++) {
			Frequencia nova = new Frequencia();
			Calendar cal = Calendar.getInstance();
			cal.setTime(dataFiltro);
			cal.set(Calendar.DAY_OF_MONTH, i);
			if(cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				nova.setData(cal.getTime());
				this.frequenciasF.add(nova);
			}		
		}
	}
	
	@SuppressWarnings("deprecation")
	public void carregarFrequenciaMesFuncionario() {		
		
		educadores = new ArrayList<Funcionario>();
		educadores.add(funcionario);
		frequenciasF = new ArrayList<Frequencia>();

		GregorianCalendar calendar = new GregorianCalendar();
		String[] auxiliar = data.split("/");
		if(!data.equalsIgnoreCase("") && funcionario != null) {
			calendar.set(Integer.parseInt(auxiliar[1]),
					(Integer.parseInt(auxiliar[0]) - 1), 1);
			
		
		

		int mes = calendar.get(Calendar.MONTH);
		int dia = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int ano = calendar.get(Calendar.YEAR);

		Frequencia[] array = new Frequencia[dia];

		Frequencia frequenciaAuxiliar;
		Date dataAuxiliar;

		// Carrega somente as datas do mes
		for (Frequencia frequenciaMes : funcionario.getFrequencias()) {
			if (mes == frequenciaMes.getData().getMonth()
					&& ano == (frequenciaMes.getData().getYear() + 1900)) {
				array[frequenciaMes.getData().getDate() - 1] = frequenciaMes;
			}
		}

		for (int i = 0; i < dia; i++) {
			if (array[i] == null) {
				frequenciaAuxiliar = new Frequencia();
				dataAuxiliar = new Date();

				dataAuxiliar.setDate(i + 1);
				dataAuxiliar.setMonth(mes);
				dataAuxiliar.setYear(ano - 1900);
				frequenciaAuxiliar.setData(dataAuxiliar);
				frequenciaAuxiliar.setFuncionario(funcionario);
				frequenciasF.add(frequenciaAuxiliar);
			} else {
				frequenciasF.add(array[i]);
			}
		}
		
		}else {
			String msg = "Preencha o campos  Corretamente !";		
			UtilFaces.addMensagemFaces(msg);
		}
	}
	
	
	@SuppressWarnings("deprecation")
	public void carregarFrequenciaMesAluno() {		
		
		alunos = new ArrayList<Aluno>();
		alunos.add(aluno);
		frequenciasA = new ArrayList<Frequencia>();

		GregorianCalendar calendar = new GregorianCalendar();
		String[] auxiliar = dataAluno.split("/");
		if(!dataAluno.equalsIgnoreCase("") && aluno != null) {
			calendar.set(Integer.parseInt(auxiliar[1]),
					(Integer.parseInt(auxiliar[0]) - 1), 1);
			
		
		

		int mes = calendar.get(Calendar.MONTH);
		int dia = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int ano = calendar.get(Calendar.YEAR);

		Frequencia[] array = new Frequencia[dia];

		Frequencia frequenciaAuxiliar;
		Date dataAuxiliar;

		// Carrega somente as datas do mes
		for (Frequencia frequenciaMes : aluno.getFrequencias()) {
			if (mes == frequenciaMes.getData().getMonth()
					&& ano == (frequenciaMes.getData().getYear() + 1900)) {
				array[frequenciaMes.getData().getDate() - 1] = frequenciaMes;
			}
		}

		for (int i = 0; i < dia; i++) {
			if (array[i] == null) {
				frequenciaAuxiliar = new Frequencia();
				dataAuxiliar = new Date();

				dataAuxiliar.setDate(i + 1);
				dataAuxiliar.setMonth(mes);
				dataAuxiliar.setYear(ano - 1900);
				frequenciaAuxiliar.setData(dataAuxiliar);
				frequenciaAuxiliar.setAluno(aluno);
				frequenciasA.add(frequenciaAuxiliar);
			} else {
				frequenciasA.add(array[i]);
			}
		}
		
		}else {
			String msg = "Preencha o campos  Corretamente !";		
			UtilFaces.addMensagemFaces(msg);
		}
	}
	
	public void manterFrequenciaFuncionario(ActionEvent event){
		try {
			String msg ="";
			if(!frequenciasF.isEmpty()) {
				frequenciaDao.alterar(frequenciasF);
			    msg = "Salvo Com Sucesso !";			   
				UtilFaces.addMensagemFaces(msg);
				 InicializeObjects();
			}else {
				   msg = "E necessario  Consultar Frequencia  !";			   
					UtilFaces.addMensagemFaces(msg);
			}
			
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}
		
		
	}
	
	public void manterFrequenciaAluno(ActionEvent event){
		try {
			String msg ="";
			if(!frequenciasA.isEmpty()) {
				frequenciaDao.alterar(frequenciasA);
			    msg = "Salvo Com Sucesso !";			   
				UtilFaces.addMensagemFaces(msg);
				 InicializeObjects();
			}else {
				   msg = "E necessario  Consultar Frequencia  !";			   
					UtilFaces.addMensagemFaces(msg);
			}
			
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}
		
		
	}
	
	
	
	
	public List<Funcionario> consultarFuncionario(String query) {		
		 this.educadores = funcionarioDao.listarPorNome(query);
	
		return educadores;
	}

	
	public List<Aluno> consultarAluno(String query) {		
		 this.alunos = alunoDao.listarPorNome(query);
	
		return alunos;
	}
	// public Turma carregarTurmar(SelectEvent evt) {
	// return turmaDao.carregarTurma(turma);
	// }
	//
	// public List<Turma> consultarTurma(String query) {
	// return turmaDao.listarPorNome(query);
	// }
	
	private void InicializeObjects(){
		
		frequenciasF = new ArrayList<Frequencia>();
		frequenciasA = new ArrayList<Frequencia>();
		data = new String();
		funcionario = new Funcionario();
		educadores = new ArrayList<Funcionario>();
		aluno = new Aluno();
		alunos = new ArrayList<Aluno>();
		dataAluno = new String();
	}
	
	public Frequencia getDataFrequenciaEducador() {
		return dataFrequenciaEducador;
	}

	public void setDataFrequenciaEducador(Frequencia dataFrequenciaEducador) {
		this.dataFrequenciaEducador = dataFrequenciaEducador;
	}

	public Date getDataFiltro() {
		return dataFiltro;
	}

	public void setDataFiltro(Date dataFiltro) {
		this.dataFiltro = dataFiltro;
	}

	public List<Turma> getTurmasAtivasDoCurso() {
		return turmasAtivasDoCurso;
	}

	public void setTurmasAtivasDoCurso(List<Turma> turmasAtivasDoCurso) {
		this.turmasAtivasDoCurso = turmasAtivasDoCurso;
	}

	public Curso getExibeCursoInfo() {
		return exibeCursoInfo;
	}

	public void setExibeCursoInfo(Curso exibeCursoInfo) {
		this.exibeCursoInfo = exibeCursoInfo;
	}

	public CursoDao getCursoDao() {
		return cursoDao;
	}
	public void setCursoDao(CursoDao cursoDao) {
		this.cursoDao = cursoDao;
	}
	public TurmaDao getTurmaDao() {
		return turmaDao;
	}
	public void setTurmaDao(TurmaDao turmaDao) {
		this.turmaDao = turmaDao;
	}
	public List<Curso> getListaCursos() {
		return listaCursos;
	}
	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDataAluno() {
		return dataAluno;
	}
	public void setDataAluno(String dataAluno) {
		this.dataAluno = dataAluno;
	}
	public List<Frequencia> getFrequenciasF() {
		return frequenciasF;
	}
	public void setFrequenciasF(List<Frequencia> frequenciasF) {
		this.frequenciasF = frequenciasF;
	}
	public List<Frequencia> getFrequenciasA() {
		return frequenciasA;
	}
	public void setFrequenciasA(List<Frequencia> frequenciasA) {
		this.frequenciasA = frequenciasA;
	}
	public List<Funcionario> getEducadores() {
		return educadores;
	}
	public void setEducadores(List<Funcionario> funcionarios) {
		this.educadores = funcionarios;
	}
	public List<Aluno> getAlunos() {
		return alunos;
	}
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
}