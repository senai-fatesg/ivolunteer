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
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoCurso;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTurno;
import br.com.ambientinformatica.ivolunteer.entidade.Turma;
import br.com.ambientinformatica.ivolunteer.persistencia.ColaboradorDao;
import br.com.ambientinformatica.ivolunteer.persistencia.CursoDao;
import br.com.ambientinformatica.ivolunteer.persistencia.TurmaDao;

@Controller("TurmaControl")
@Scope("conversation")
public class TurmaControl {

	@Autowired
	private CursoDao cursoDao;
	@Autowired
	private TurmaDao turmaDao;
	@Autowired
	private ColaboradorDao funcionarioDao;

	private Turma turma = new Turma();

	public List<Curso> getCursos() {
		try {
			return cursoDao.listarAtivos();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
		return new ArrayList<>();
	}

	public List<Colaborador> getProfessores() {
		try {
			List<Colaborador> educadores = funcionarioDao.listarEducadoresAtivos();
			return educadores;
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
		return new ArrayList<>();
	}

	public void salvar() {
		try {
			turmaDao.alterar(this.turma);
			this.turma = new Turma();
			UtilFaces.addMensagemFaces("Turma salva com sucesso!");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public List<SelectItem> getCompleteEnumTipoCurso() {
		return UtilFaces.getListEnum(EnumTipoCurso.values());
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<SelectItem> getTurnos() {
		return UtilFaces.getListEnum(EnumTurno.values());
	}

}
