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
import br.com.ambientinformatica.ivolunteer.entidade.Parceiro;
import br.com.ambientinformatica.ivolunteer.persistencia.AgrupamentoTurmaDao;
import br.com.ambientinformatica.ivolunteer.persistencia.CursoDao;
import br.com.ambientinformatica.ivolunteer.persistencia.ParceiroDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Controller("CursoControl")
@Scope("conversation")
public class CursoControl {
	
	private String nomeFiltro;
	private String statusFiltro;
	private Parceiro parceiro = new Parceiro();
	private Curso exibeCursoInfo = new Curso();
	private Curso curso = new Curso();
	private List<Curso> listaCursos = new ArrayList<Curso>();

	@Autowired
	private CursoDao cursoDao;
	
	@Autowired
	private ParceiroDao parceiroDao;

	@PostConstruct
	public void init() {
		listar();
	}
	
	public List<Parceiro> buscaParceiros(String nomeParceiro) {
		return parceiroDao.buscaParceiroPorNome(nomeParceiro);
	}

	public void listar() {
		try {
			this.listaCursos = cursoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void aplicarFiltro() {

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
		if(cursoValido()) {
			cursoDao.alterar(this.curso);
			UtilFaces.addMensagemFaces("Curso cadastrado com sucesso!");
			this.curso = new Curso();
			this.parceiro = new Parceiro();
			listar();
		}	
	}
	
	private boolean cursoValido() {
		if(this.curso.getNome().isEmpty()) {
			UtilFaces.addMensagemFaces("Nome do curso é obrigatório");
			return false;
		} else if (this.curso.getDuracao().equals(null)) {
			UtilFaces.addMensagemFaces("Duração do curso é obrigatório");
			return false;
		} else if (this.curso.getCargaHoraria().isEmpty()) {
			UtilFaces.addMensagemFaces("Carga horária do curso é obrigatório");
			return false;
		}
		return true;
	}

	public void editaCurso(Curso curso) {
		this.curso = cursoDao.consultar(curso.getId());
	}
	
	public void salvarAlteracoesCurso() {
		if(cursoValido()) {
			cursoDao.alterar(this.curso);
			UtilFaces.addMensagemFaces("Curso atualizado com sucesso!");
			this.curso = new Curso();
			listar();
		}
	}
	
	public void exibeInfoDoCurso(Curso curso) {
		this.exibeCursoInfo = cursoDao.consultar(curso.getId());
	}
	
	public List<Parceiro> buscaParceiro(String nome) {
		return parceiroDao.buscaParceiroPorNome(nome);
	}
	
	public List<SelectItem> getCompleteEnumTipoCurso() {
		return UtilFaces.getListEnum(EnumTipoCurso.values());
	}
	
	public void escolheParceiro() {
		this.curso.setParceiro(parceiroDao.buscaParceiroPorID(this.parceiro.getId()));
		this.parceiro = new Parceiro();
	}
	
	public Parceiro getParceiro() {
		return parceiro;
	}

	public void setParceiro(Parceiro parceiro) {
		this.parceiro = parceiro;
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

	public Curso getExibeCursoInfo() {
		return exibeCursoInfo;
	}

	public void setExibeCursoInfo(Curso exibeCursoInfo) {
		this.exibeCursoInfo = exibeCursoInfo;
	}
	
}
