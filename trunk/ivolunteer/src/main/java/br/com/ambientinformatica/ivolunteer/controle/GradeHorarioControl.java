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

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.EnumDiaSemana;
import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;
import br.com.ambientinformatica.ivolunteer.entidade.GradeHorario;
import br.com.ambientinformatica.ivolunteer.persistencia.FuncionarioDao;
import br.com.ambientinformatica.ivolunteer.persistencia.GradeHorarioDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Controller("GradeHorarioControl")
@Scope("conversation")
public class GradeHorarioControl {

	private GradeHorario gradeHorario = new GradeHorario();
	private Funcionario funcionario = new Funcionario();
	

	@Autowired
	private GradeHorarioDao gradeHorarioDao;
	
	@Autowired
	private FuncionarioDao funcionarioDao;

	private List<GradeHorario> gradeHorarios = new ArrayList<GradeHorario>();

	@PostConstruct
	public void init() {
		listar(null);
	}

	public void confirmar(ActionEvent evt) {
		try {
			gradeHorarioDao.alterar(gradeHorario);
			listar(evt);
			gradeHorario = new GradeHorario();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void carregarFuncionario(SelectEvent evt) {
		try {
			this.funcionario = funcionarioDao.consultar(funcionario.getId());
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public List<Funcionario> consultarFuncionario(String query) {
		return funcionarioDao.listarPorNome(query);
	}

	public void listar(ActionEvent evt) {
		try {
			gradeHorarios = gradeHorarioDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public List<SelectItem> getCompleteEnumDiaSemana() {
		return UtilFaces.getListEnum(EnumDiaSemana.values());
	}


	public GradeHorario getGradeHorario() {
		return gradeHorario;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void setGradeHorario(GradeHorario gradehorario) {
		this.gradeHorario = gradehorario;
	}

	public List<GradeHorario> getGradeHorarios() {
		return gradeHorarios;
	}

}
