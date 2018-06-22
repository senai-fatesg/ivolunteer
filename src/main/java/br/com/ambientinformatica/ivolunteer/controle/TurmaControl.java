package br.com.ambientinformatica.ivolunteer.controle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.EnumCargo;
import br.com.ambientinformatica.ivolunteer.entidade.EnumFiliacao;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTurno;
import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.entidade.Turma;
import br.com.ambientinformatica.ivolunteer.persistencia.FuncionarioDao;
import br.com.ambientinformatica.ivolunteer.persistencia.TurmaDao;
import br.com.ambientinformatica.ivolunteer.service.TurmaService;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Controller("TurmaControl")
@Scope("conversation")
public class TurmaControl implements TurmaService {

	/*** ATRIBUTOS DA PÁGINA ***/
	private Turma turma = new Turma();

	private Turma turmaConsulta = new Turma();
	private List<Turma> turmasConsulta = new ArrayList<>();

	@Autowired
	private TurmaDao turmaDao;
	@Autowired
	private FuncionarioDao funcionarioDao;

	private TurmaService turmaService;
	private List<Turma> turmas = new ArrayList<>();
	private List<Funcionario> professores = new ArrayList<>();

	/*** INICIALIZADOR ***/
	@PostConstruct
	public void init() {
		listar(null);
		carregaProfessores();
	}

	/*** GETTERS E SETTERS ***/
	public Turma getTurma() {
		return turma;
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

	public FuncionarioDao getFuncionarioDao() {
		return funcionarioDao;
	}

	public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
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

	public List<Funcionario> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Funcionario> professores) {
		this.professores = professores;
	}

	/*** AÇÕES DA PÁGINA ***/
	public void confirmar(ActionEvent evt) {
		try {
			validarTurma(turma);
			turmaDao.alterar(turma);
			listar(evt);
			turma = new Turma();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	private void validarTurma(Turma turma) throws Exception {
		if (turma.getNome().isEmpty()) {
			throw new Exception("É obrigatório informar o Nome");
		} else if (turma.getCodigo().isEmpty()) {
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

	public void listar(ActionEvent evt) {
		try {
			turmas = turmaDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void excluir(Turma turma) {
		try {
			turmaDao.excluirPorId(turma.getId());
			UtilFaces.addMensagemFaces("Turma excluída com sucesso!");
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void carregaProfessores() {
		try {
			List<Funcionario> itens = funcionarioDao.listar();
			for (Funcionario funcionario : itens) {
				if (funcionario.getCargo() == EnumCargo.EDUCADOR)
					professores.add(funcionario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void carregaTurmaAlteracao(Turma turma) {
		this.turma = turma;
	}

	public void aplicarFiltro(ActionEvent evt) {
		try {
			if (turmaConsulta != null && !turmaConsulta.getNome().isEmpty()) {
				turmasConsulta = turmaDao.listarPorNome(turmaConsulta.getNome());
			} else {
				turmasConsulta = turmaDao.listar();
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
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

}
