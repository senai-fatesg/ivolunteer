package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Frequencia;
import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;
import br.com.ambientinformatica.ivolunteer.entidade.Turma;
import br.com.ambientinformatica.ivolunteer.persistencia.FuncionarioDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Controller("FrequenciaControl")
@Scope("conversation")
public class FrequenciaControl {

	private Funcionario funcionario = new Funcionario();
	private Turma turma = new Turma();

	private Frequencia frequencia = new Frequencia();

	private Date data = new Date();

	@Autowired
	private FuncionarioDao funcionarioDao;

	private List<Frequencia> frequencias = new ArrayList<Frequencia>();
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();

	@PostConstruct
	public void init() {
		// listar(null);

	}

	public void confirmar(ActionEvent evt) {
		try {
			funcionarioDao.alterar(funcionario);
			funcionario = new Funcionario();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void consultar(ActionEvent evt) {
		try {
			funcionarioDao.consultar(funcionario.getId());
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void incluir(ActionEvent evt) {
		try {
			funcionarioDao.incluir(funcionario);
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}

	}

	public void excluir(ActionEvent evt) {
		try {
			funcionarioDao.excluirPorId(funcionario);
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}

	}

	public void listar(ActionEvent evt) {
		try {
			funcionarios = funcionarioDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void alterarLista(ActionEvent evt) {
		try {
			funcionarioDao.alterar(funcionarios);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void setFrequencia(Frequencia frequencia) {
		this.frequencia = frequencia;
	}

	public Frequencia getFrequencia() {
		return frequencia;
	}

	public List<Frequencia> getFrequencias() {
		return frequencias;
	}

	public void adicionarFrequencia(ActionEvent evt) {
		try {
			funcionario.addFrequencia(frequencia);
			funcionarioDao.alterar(funcionario);
		} catch (PersistenciaException e) {
			UtilFaces
					.addMensagemFaces("Ocorreu um erro ao adicionar a Frequencia");
		}
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Funcionario carregarFuncionario(SelectEvent evt) {
		return funcionarioDao.carregarFuncionario(funcionario);
	}

	public List<Funcionario> consultarFuncionario(String query) {
		return funcionarioDao.listarPorNome(query);
	}

//	public Turma carregarTurmar(SelectEvent evt) {
//		return turmaDao.carregarTurma(turma);
//	}
//
//	public List<Turma> consultarTurma(String query) {
//		return turmaDao.listarPorNome(query);
//	}

	public void manterFrequencia(ActionEvent evt) {

	}

	@SuppressWarnings("deprecation")
	public void carregarFrequenciaMes() {
		frequencias = new ArrayList<Frequencia>();

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(data.getYear(), (data.getMonth() + 1), data.getDate());

		int mes = calendar.get(Calendar.MONTH);
		int dia = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int ano = calendar.get(Calendar.YEAR);

		Frequencia frequenciaAuxiliar;
		Date dataAuxiliar;

		// Carrega somente as datas do mes
		for (Frequencia frequenciaMes : funcionario.getFrequencias()) {
			if (mes == frequenciaMes.getData().getMonth()) {
				frequencias.add(frequenciaMes);
			}
		}

		for (int i = 1; i <= dia; i++) {
			frequenciaAuxiliar = new Frequencia();
			dataAuxiliar = new Date();

			dataAuxiliar.setDate(i);
			dataAuxiliar.setMonth(mes);
			dataAuxiliar.setYear(ano);
			frequenciaAuxiliar.setData(dataAuxiliar);

			if (!frequencias.contains(frequenciaAuxiliar)) {
				frequencias.add(frequenciaAuxiliar);
			}
		}
	}

}
