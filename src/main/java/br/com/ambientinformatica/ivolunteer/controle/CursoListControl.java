package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Curso;
import br.com.ambientinformatica.ivolunteer.entidade.EnumStatus;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoCurso;
import br.com.ambientinformatica.ivolunteer.persistencia.CursoDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Controller("CursoListControl")
@Scope("conversation")
public class CursoListControl {

	@Autowired
	private CursoDao cursoDao;
	
	private String nomeFiltro;
	private EnumStatus statusFiltro;
	private Curso cursoInfo = new Curso();
	private Curso curso = new Curso();
	private List<Curso> cursos = new ArrayList<Curso>();

	public void listarCursos() {
		try {
			this.cursos = cursoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void aplicarFiltro() {
		try {
			this.cursos = cursoDao.listarPorNomeStatus(this.nomeFiltro, this.statusFiltro);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void inativar(Curso curso) {
		try {
			curso.inativar();
			cursoDao.alterar(curso);
			UtilFaces.addMensagemFaces("Status do curso alterado com sucesso!");
			listarCursos();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void ativar(Curso curso) {
		try {
			curso.ativar();
			cursoDao.alterar(curso);
			UtilFaces.addMensagemFaces("Status do curso alterado com sucesso!");
			listarCursos();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void exibeInfo(Curso curso) {
		try {
			this.cursoInfo = cursoDao.consultar(curso.getId());
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public List<SelectItem> getStatus() {
		return UtilFaces.getListEnum(EnumStatus.values());
	}

	public List<SelectItem> getCompleteEnumTipoCurso() {
		return UtilFaces.getListEnum(EnumTipoCurso.values());
	}

	public String getNomeFiltro() {
		return nomeFiltro;
	}

	public void setNomeFiltro(String nomeFiltro) {
		this.nomeFiltro = nomeFiltro;
	}

	public EnumStatus getStatusFiltro() {
		return statusFiltro;
	}

	public void setStatusFiltro(EnumStatus statusFiltro) {
		this.statusFiltro = statusFiltro;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public CursoDao getCursoDao() {
		return cursoDao;
	}

	public void setCursoDao(CursoDao cursoDao) {
		this.cursoDao = cursoDao;
	}

	public Curso getCursoInfo() {
		return cursoInfo;
	}

	public void setCursoInfo(Curso exibeCursoInfo) {
		this.cursoInfo = exibeCursoInfo;
	}

}
