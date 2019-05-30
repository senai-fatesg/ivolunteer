package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Colaborador;
import br.com.ambientinformatica.ivolunteer.entidade.Curso;
import br.com.ambientinformatica.ivolunteer.entidade.EnumSituacao;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTurno;
import br.com.ambientinformatica.ivolunteer.entidade.Turma;
import br.com.ambientinformatica.ivolunteer.persistencia.ColaboradorDao;
import br.com.ambientinformatica.ivolunteer.persistencia.CursoDao;
import br.com.ambientinformatica.ivolunteer.persistencia.TurmaDao;

@Controller("TurmaListControl")
@Scope("conversation")
public class TurmaListControl {

	@Autowired
	private ColaboradorDao colaboradorDao;
	@Autowired
	private TurmaDao turmaDao;
	@Autowired
	private CursoDao cursoDao;
	
	private EnumSituacao situacaoFiltro;
	private String codigoFiltro;
	private Curso cursoFiltro;
	private Colaborador professorFiltro;
	private Turma turma = new Turma();
	private Turma exibeTurmaInfo = new Turma();
	private List<Turma> turmas = new ArrayList<>();
	
	public void aplicarFiltro() {
		try {
			this.turmas = turmaDao.listarPorCursoProfessor(codigoFiltro, cursoFiltro, professorFiltro, situacaoFiltro);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void listarTurmas() {
		try {
			this.turmas= turmaDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void exibeInfoTurma(Turma turma) {
		this.exibeTurmaInfo = turmaDao.consultar(turma.getId());
	}

	public List<SelectItem> getSituacaoStatus() {
		return UtilFaces.getListEnum(EnumSituacao.values());
	}
	
	public List<Colaborador> getProfessores() {
		try {
			return colaboradorDao.listarEducadoresAtivos();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
		return new ArrayList<>();
	}
	
	public List<Curso> getCursos() {
		try {
			return cursoDao.listarAtivos();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
		return new ArrayList<>();
	}

	public Turma getTurma() {
		return turma;
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

	public List<SelectItem> getTurnos() {
		return UtilFaces.getListEnum(EnumTurno.values());
	}

	public TurmaDao getTurmaDao() {
		return turmaDao;
	}

	public void setTurmaDao(TurmaDao turmaDao) {
		this.turmaDao = turmaDao;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public Curso getCursoFiltro() {
		return cursoFiltro;
	}

	public void setCursoFiltro(Curso cursoFiltro) {
		this.cursoFiltro = cursoFiltro;
	}

	public Colaborador getProfessorFiltro() {
		return professorFiltro;
	}

	public void setProfessorFiltro(Colaborador professorFiltro) {
		this.professorFiltro = professorFiltro;
	}

	public String getCodigoFiltro() {
		return codigoFiltro;
	}

	public void setCodigoFiltro(String codigoFiltro) {
		this.codigoFiltro = codigoFiltro;
	}

	public EnumSituacao getSituacaoFiltro() {
		return situacaoFiltro;
	}

	public void setSituacaoFiltro(EnumSituacao situacaoFiltro) {
		this.situacaoFiltro = situacaoFiltro;
	}

}
