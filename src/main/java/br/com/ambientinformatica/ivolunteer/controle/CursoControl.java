package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Curso;
import br.com.ambientinformatica.ivolunteer.entidade.EnumStatus;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoCurso;
import br.com.ambientinformatica.ivolunteer.entidade.Parceiro;
import br.com.ambientinformatica.ivolunteer.persistencia.CursoDao;
import br.com.ambientinformatica.ivolunteer.persistencia.ParceiroDao;

@Controller("CursoControl")
@Scope("conversation")
public class CursoControl {

	@Autowired
	private CursoDao cursoDao;
	
	@Autowired
	private ParceiroDao parceiroDao;
	
	private String nomeFiltro;
	private EnumStatus statusFiltro;
	private Parceiro parceiro = new Parceiro();
	private Curso exibeCursoInfo = new Curso();
	private Curso curso = new Curso();
	private List<Curso> listaCursos = new ArrayList<Curso>();

	@PostConstruct
	public void init() {
		listarCursos();
	}

	public List<Parceiro> buscaParceiros(String nomeParceiro) {
		return parceiroDao.buscaParceiroPorNome(nomeParceiro);
	}

	public void listarCursos() {
		try {
			this.listaCursos = cursoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void aplicarFiltro() {
		try {
			this.listaCursos = cursoDao.listarPorNomeStatus(this.nomeFiltro, this.statusFiltro);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void editaCurso(Curso curso) {
		this.curso = cursoDao.consultar(curso.getId());
	}

	public void salvar() {
		cursoDao.alterar(this.curso);
		this.curso = new Curso();
		listarCursos();
		UtilFaces.addMensagemFaces("Curso atualizado com sucesso!");
	}

	public void exibeInfoDoCurso(Curso curso) {
		this.exibeCursoInfo = cursoDao.consultar(curso.getId());
	}

	public List<Parceiro> buscaParceiro(String nome) {
		return parceiroDao.buscaParceiroPorNome(nome);
	}

	public List<SelectItem> getStatus() {
		return UtilFaces.getListEnum(EnumStatus.values());
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
