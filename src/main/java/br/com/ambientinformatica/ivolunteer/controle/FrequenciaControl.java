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

	private String data = new String();

	@Autowired
	private FuncionarioDao funcionarioDao;

	private List<Frequencia> frequencias = new ArrayList<Frequencia>();
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();

	@PostConstruct
	public void init() {
		listar(null);

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

	public List<Frequencia> getFrequencias() {
		return frequencias;
	}

	public void adicionarFrequencia(Frequencia frequencia) {
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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

	// public Turma carregarTurmar(SelectEvent evt) {
	// return turmaDao.carregarTurma(turma);
	// }
	//
	// public List<Turma> consultarTurma(String query) {
	// return turmaDao.listarPorNome(query);
	// }

	public void manterFrequencia() {
		for (Frequencia auxiliar : frequencias) {
			if (auxiliar.getPresente()) {
				funcionario.addFrequencia(auxiliar);
			} else {
				funcionario.alterarFrequencia(auxiliar);
			}
		}
		this.frequencias = new ArrayList<Frequencia>();
		this.funcionarios = new ArrayList<Funcionario>();
		this.funcionario = new Funcionario();
		this.data = new String();
	}

	@SuppressWarnings("deprecation")
	public void carregarFrequenciaMes() {
		funcionarios = new ArrayList<Funcionario>();
		funcionarios.add(funcionario);
		frequencias = new ArrayList<Frequencia>();

		GregorianCalendar calendar = new GregorianCalendar();
		String[] auxiliar = data.split("/");
		calendar.set(Integer.parseInt(auxiliar[1]),
				(Integer.parseInt(auxiliar[0]) - 1), 1);

		int mes = calendar.get(Calendar.MONTH);
		int dia = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int ano = calendar.get(Calendar.YEAR);

		Frequencia[] array = new Frequencia[dia];

		Frequencia frequenciaAuxiliar;
		Date dataAuxiliar;

		// Carrega somente as datas do mes
		for (Frequencia frequenciaMes : funcionario.getFrequencias()) {
			if (mes == frequenciaMes.getData().getMonth()
					&& ano == (frequenciaMes.getData().getYear() + 1900)) {
				array[frequenciaMes.getData().getDate() - 1] = frequenciaMes;
			}
		}

		for (int i = 0; i < dia; i++) {
			if (array[i] == null) {
				frequenciaAuxiliar = new Frequencia();
				dataAuxiliar = new Date();

				dataAuxiliar.setDate(i + 1);
				dataAuxiliar.setMonth(mes);
				dataAuxiliar.setYear(ano - 1900);
				frequenciaAuxiliar.setData(dataAuxiliar);
				frequencias.add(frequenciaAuxiliar);
			} else {
				frequencias.add(array[i]);
			}
		}
	}

}
