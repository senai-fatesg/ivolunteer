package br.com.ambientinformatica.ivolunteer.controle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Curso;
import br.com.ambientinformatica.ivolunteer.entidade.EnumCargo;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoCurso;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTurno;
import br.com.ambientinformatica.ivolunteer.entidade.Colaborador;
import br.com.ambientinformatica.ivolunteer.entidade.Turma;
import br.com.ambientinformatica.ivolunteer.persistencia.CursoDao;
import br.com.ambientinformatica.ivolunteer.persistencia.ColaboradorDao;
import br.com.ambientinformatica.ivolunteer.persistencia.TurmaDao;
import br.com.ambientinformatica.ivolunteer.service.TurmaService;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Controller("TurmaListControl")
@Scope("conversation")
public class TurmaListControl implements TurmaService {

	/*** ATRIBUTOS DA PÁGINA ***/
	private Turma turma = new Turma();
	private Curso curso = new Curso();
	private Curso cursoSelecionado;
	private Turma exibeTurmaInfo = new Turma();

	private Turma turmaConsulta = new Turma();
	private List<Turma> turmasFiltradas = new ArrayList<>();
	private List<Turma> turmasConsulta = new ArrayList<>();
	private Colaborador professorSelecionado;

	@Autowired
	private CursoDao cursoDao;
	@Autowired
	private TurmaDao turmaDao;
	@Autowired
	private ColaboradorDao funcionarioDao;

	private List<Curso> autocompleteCursos = new ArrayList<Curso>();
	private TurmaService turmaService;
	private List<Turma> turmas = new ArrayList<>();
	private List<Colaborador> professores = new ArrayList<>();
	private List<Curso> carregaTodosCursos;
	private List<Curso> listaCursos = new ArrayList<Curso>();
	private Curso exibeCursoInfo = new Curso();
	private boolean renderizaDatas = true;
	private List<Colaborador> autocompleteProfessores = new ArrayList<Colaborador>();

	/*** INICIALIZADOR ***/
	@PostConstruct
	public void init() {
		carregaCursos();
		listar();
		carregaProfessores();
		listaCursosAtivos();
	}

	/***
	 * AÇÕES DA PÁGINA
	 * 
	 * @throws Exception
	 ***/

	public List<Curso> buscaCursos(String nome) {
		//this.autocompleteCursos = cursoDao.buscaCursoPorNome(nome);
		return this.autocompleteCursos;
	}

	public List<Colaborador> buscaProfessores(String nome) {
		this.autocompleteProfessores = funcionarioDao.buscaEducadorPorNome(nome);
		return this.autocompleteProfessores;
	}

	public void inativarCurso(Curso curso) {
		Curso c = cursoDao.consultar(curso.getId());
		//c.inativarCurso();
		cursoDao.alterar(c);
		this.listaCursos = cursoDao.listarAtivos();
	}

	public void exibeInfoDoCurso(Curso curso) {
		this.exibeCursoInfo = curso;
	}

	public void selecionaCurso() {
		if (this.turma.getCurso().getDuracao().equals(EnumTipoCurso.DURACAO_INDETERMINADA)) {
			this.renderizaDatas = false;
		} else {
			this.renderizaDatas = true;
		}
	}

	public List<Curso> buscaCursosPorNome(String nome) {
		//return this.cursoDao.buscaCursoPorNome(nome);
		return null;
	}

	public List<Colaborador> buscaProfessoresPorNome(String nome) {
		return this.funcionarioDao.buscaEducadorPorNome(nome);
	}

	public void selecionaProfessor() {
		this.turma.setProfessor(professorSelecionado);
	}

	public void cadastrarTurma() {
		try {
			//this.curso.adicionarTurma(this.turma);
			this.cursoDao.alterar(this.curso);
			// validarTurma(this.turma);
			// turmaDao.incluir(this.turma);
			listar();
			this.curso = new Curso();
			this.turma = new Turma();
			this.professorSelecionado = new Colaborador();
			this.cursoSelecionado = new Curso();
			UtilFaces.addMensagemFaces("Turma cadastrada com sucesso!");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void salvarAlteracoesTurma() {
		try {
			// validarTurma(this.turma);
			turmaDao.alterar(this.turma);
			listar();
			this.curso = new Curso();
			this.turma = new Turma();
			this.professorSelecionado = new Colaborador();
			this.cursoSelecionado = new Curso();
			UtilFaces.addMensagemFaces("Turma alterada com sucesso!");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	private void validarTurma(Turma turma) throws Exception {
		if (turma.getCodigo().isEmpty()) {
			throw new Exception("É obrigatório informar o Código");
		} else if (turma.getTurno() == null) {
			throw new Exception("É obrigatório informar o Turno");
		} else if (turma.getHorarioInicio() == null) {
			throw new Exception("É obrigatório informar o Horário de Início");
		} else if (turma.getHorarioFinal() == null) {
			throw new Exception("É obrigatório informar o Horário de Fim");
		} else if (turma.getHorarioInicio().compareTo(turma.getHorarioFinal()) >= 0) {
			throw new Exception("O Horário de Fim deve ser maior que o Horário de Início");
		} else if (turma.getProfessor() == null) {
			throw new Exception("É obrigatório informar o Professor");
		}
	}

	public void listar() {
		try {
			this.turmasConsulta = turmaDao.listar();
			turmas = turmaDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void concluirTurma(Turma turma) {
		try {
			Turma tm = turmaDao.consultar(turma.getId());
			tm.concluirTurma();
			turmaDao.alterar(tm);
			listar();
			UtilFaces.addMensagemFaces("Turma inativada com sucesso!");
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void exibeInfoTurma(Turma turma) {
		this.exibeTurmaInfo = turmaDao.consultar(turma.getId());
	}

	public void carregaProfessores() {
		try {
			List<Colaborador> itens = funcionarioDao.listar();
			for (Colaborador funcionario : itens) {
				if (funcionario.getCargo() == EnumCargo.EDUCADOR && funcionario.getIsAtivo() == true) {
					professores.add(funcionario);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void carregaTurmaAlteracao(Turma turma) {
		this.turma = turmaDao.consultar(turma.getId());
	}

	private void carregaCursos() {
		this.carregaTodosCursos = cursoDao.listar();
	}

	/*** MÉTODOS DE APOIO ***/
	private SimpleDateFormat fmtHorario = new SimpleDateFormat("HH:mm");

	public String formatHora(Date horario) {
		return fmtHorario.format(horario);
	}

	public List<Date> getHorariosInicio() {
		SimpleDateFormat fmt = fmtHorario;
		List<Date> horariosInicio = new ArrayList<>();

		if (turma.getTurno() != null) {
			try {
				switch (turma.getTurno()) {
				case MATUTINO:
					horariosInicio.add(fmt.parse("07:00"));
					horariosInicio.add(fmt.parse("08:00"));
					horariosInicio.add(fmt.parse("09:00"));
					horariosInicio.add(fmt.parse("10:00"));
					horariosInicio.add(fmt.parse("11:00"));
					break;
				case VESPERTINO:
					horariosInicio.add(fmt.parse("13:00"));
					horariosInicio.add(fmt.parse("14:00"));
					horariosInicio.add(fmt.parse("15:00"));
					horariosInicio.add(fmt.parse("16:00"));
					horariosInicio.add(fmt.parse("17:00"));
					break;
				case NOTURNO:
					horariosInicio.add(fmt.parse("19:00"));
					horariosInicio.add(fmt.parse("20:00"));
					horariosInicio.add(fmt.parse("21:00"));
					horariosInicio.add(fmt.parse("22:00"));
					break;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return horariosInicio;
	}

	public List<Date> getHorariosFinal() {
		SimpleDateFormat fmt = fmtHorario;
		List<Date> horariosFinal = new ArrayList<>();

		if (turma.getTurno() != null) {
			try {
				switch (turma.getTurno()) {
				case MATUTINO:
					horariosFinal.add(fmt.parse("08:00"));
					horariosFinal.add(fmt.parse("09:00"));
					horariosFinal.add(fmt.parse("10:00"));
					horariosFinal.add(fmt.parse("11:00"));
					horariosFinal.add(fmt.parse("12:00"));
					break;
				case VESPERTINO:
					horariosFinal.add(fmt.parse("14:00"));
					horariosFinal.add(fmt.parse("15:00"));
					horariosFinal.add(fmt.parse("16:00"));
					horariosFinal.add(fmt.parse("17:00"));
					horariosFinal.add(fmt.parse("18:00"));
					break;
				case NOTURNO:
					horariosFinal.add(fmt.parse("20:00"));
					horariosFinal.add(fmt.parse("21:00"));
					horariosFinal.add(fmt.parse("22:00"));
					horariosFinal.add(fmt.parse("23:00"));
					break;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return horariosFinal;
	}

	/*** GETTERS E SETTERS ***/

	public void listaCursosAtivos() {
		this.listaCursos = cursoDao.listarAtivos();
	}

	public List<Turma> getTurmasFiltradas() {
		return turmasFiltradas;
	}

	public void setTurmasFiltradas(List<Turma> turmasFiltradas) {
		this.turmasFiltradas = turmasFiltradas;
	}

	public List<Colaborador> getAutocompleteProfessores() {
		return autocompleteProfessores;
	}

	public void setAutocompleteProfessores(List<Colaborador> autocompleteProfessores) {
		this.autocompleteProfessores = autocompleteProfessores;
	}

	public List<Curso> getAutocompleteCursos() {
		return autocompleteCursos;
	}

	public void setAutocompleteCursos(List<Curso> autocompleteCursos) {
		this.autocompleteCursos = autocompleteCursos;
	}

	public boolean isRenderizaDatas() {
		return renderizaDatas;
	}

	public void setRenderizaDatas(boolean renderizaDatas) {
		this.renderizaDatas = renderizaDatas;
	}

	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public List<Curso> getCarregaTodosCursos() {
		return carregaTodosCursos;
	}

	public void setCarregaTodosCursos(List<Curso> carregaTodosCursos) {
		this.carregaTodosCursos = carregaTodosCursos;
	}

	public List<SelectItem> getCompleteEnumTipoCurso() {
		return UtilFaces.getListEnum(EnumTipoCurso.values());
	}

	public Curso getCursoSelecionado() {
		return cursoSelecionado;
	}

	public void setCursoSelecionado(Curso cursoSelecionado) {
		this.cursoSelecionado = cursoSelecionado;
	}

	public Curso getExibeCursoInfo() {
		return exibeCursoInfo;
	}

	public void setExibeCursoInfo(Curso exibeCursoInfo) {
		this.exibeCursoInfo = exibeCursoInfo;
	}

	public Turma getTurma() {
		return turma;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Colaborador getProfessorSelecionado() {
		return professorSelecionado;
	}

	public void setProfessorSelecionado(Colaborador professorSelecionado) {
		this.professorSelecionado = professorSelecionado;
	}

	public Turma getExibeTurmaInfo() {
		return exibeTurmaInfo;
	}

	public void setExibeTurmaInfo(Turma exibeTurmaInfo) {
		this.exibeTurmaInfo = exibeTurmaInfo;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Turma getTurmaConsulta() {
		return turmaConsulta;
	}

	public void setTurmaConsulta(Turma turmaConsulta) {
		this.turmaConsulta = turmaConsulta;
	}

	public List<Turma> getTurmasConsulta() {
		return turmasConsulta;
	}

	public void setTurmasConsulta(List<Turma> turmasConsulta) {
		this.turmasConsulta = turmasConsulta;
	}

	public List<SelectItem> getTurnos() {
		return UtilFaces.getListEnum(EnumTurno.values());
	}

	public TurmaDao getTurmaDao() {
		return turmaDao;
	}

	public void setTurmaDao(TurmaDao turmaDao) {
		this.turmaDao = turmaDao;
	}

	public ColaboradorDao getFuncionarioDao() {
		return funcionarioDao;
	}

	public void setFuncionarioDao(ColaboradorDao funcionarioDao) {
		this.funcionarioDao = funcionarioDao;
	}

	public TurmaService getTurmaService() {
		return turmaService;
	}

	public void setTurmaService(TurmaService turmaService) {
		this.turmaService = turmaService;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public List<Colaborador> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Colaborador> professores) {
		this.professores = professores;
	}

}
