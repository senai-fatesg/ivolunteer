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
import br.com.ambientinformatica.ivolunteer.entidade.AgrupamentoTurma;
import br.com.ambientinformatica.ivolunteer.entidade.Curso;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoCurso;
import br.com.ambientinformatica.ivolunteer.persistencia.AgrupamentoTurmaDao;
import br.com.ambientinformatica.ivolunteer.persistencia.CursoDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Controller("CursoControl")
@Scope("conversation")
public class CursoControl {
	
	private String nomeFiltro;
	private String statusFiltro;
	private Curso exibeCursoInfo = new Curso();
	private Curso curso = new Curso();
	private List<Curso> listaCursos = new ArrayList<Curso>();

	@Autowired
	private CursoDao cursoDao;

	private List<AgrupamentoTurma> agrupamentoTurmas = new ArrayList<AgrupamentoTurma>();

	@PostConstruct
	public void init() {
		listar();
	}

	public void listar() {
		try {
			this.listaCursos = cursoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void aplicarFiltro() {
		System.out.println("NOME ISEMPTY? " + this.nomeFiltro);
		System.out.println("NOME ISEMPTY? " + this.statusFiltro);
		
		if(!this.nomeFiltro.isEmpty() && this.statusFiltro.isEmpty()) {
			this.listaCursos = cursoDao.buscaCursoPorNome(nomeFiltro);
		} else if(this.nomeFiltro.isEmpty() && !this.statusFiltro.isEmpty()) {
			this.listaCursos = cursoDao.buscaCursoPorStatus(statusFiltro);
		} else if(!this.nomeFiltro.isEmpty() && !this.statusFiltro.isEmpty()) {
			this.listaCursos = cursoDao.buscaCursoPorStatusNome(statusFiltro , nomeFiltro);
		} else {
			listar();
		}
		
	}
	
	public void cadastrarCurso() {
		cursoDao.incluir(this.curso);
		UtilFaces.addMensagemFaces("Curso cadastrado com sucesso!");
		this.curso = new Curso();
		listar();
	}
	
	public void editaCurso(Curso curso) {
		this.curso = cursoDao.consultar(curso.getId());
	}
	
	public void salvarAlteracoesCurso() {
		cursoDao.alterar(this.curso);
		UtilFaces.addMensagemFaces("Curso atualizado com sucesso!");
		this.curso = new Curso();
		listar();
	}
	
	public void exibeInfoDoCurso(Curso curso) {
		this.exibeCursoInfo = cursoDao.consultar(curso.getId());
	}

	// Aplica Filtro por identificador
	/*
		public void aplicarFiltro(ActionEvent evt) {
			try {
				if (this.agrupamentoTurma.getIdentificador().isEmpty()) {
					this.agrupamentoTurmas = this.agrupamentoTurmaDao.listar();
				} else {
					this.agrupamentoTurmas = this.agrupamentoTurmaDao.listarIdentificador(this.agrupamentoTurma);
				}
			} catch (Exception e) {
				UtilFaces.addMensagemFaces(e);
			}

		}
		*/
	
	public List<SelectItem> getCompleteEnumTipoCurso() {
		return UtilFaces.getListEnum(EnumTipoCurso.values());
	}
	
	public String getNomeFiltro() {
		return nomeFiltro;
	}

	public void setNomeFiltro(String nomeFiltro) {
		this.nomeFiltro = nomeFiltro;
	}

	public String getStatusFiltro() {
		return statusFiltro;
	}

	public void setStatusFiltro(String statusFiltro) {
		this.statusFiltro = statusFiltro;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public CursoDao getCursoDao() {
		return cursoDao;
	}

	public void setCursoDao(CursoDao cursoDao) {
		this.cursoDao = cursoDao;
	}

	public List<AgrupamentoTurma> getAgrupamentoTurmas() {
		return agrupamentoTurmas;
	}

	public void setAgrupamentoTurmas(List<AgrupamentoTurma> agrupamentoTurmas) {
		this.agrupamentoTurmas = agrupamentoTurmas;
	}

	public Curso getExibeCursoInfo() {
		return exibeCursoInfo;
	}

	public void setExibeCursoInfo(Curso exibeCursoInfo) {
		this.exibeCursoInfo = exibeCursoInfo;
	}
	
}
