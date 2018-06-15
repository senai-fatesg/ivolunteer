package br.com.ambientinformatica.ivolunteer.controle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.EnumCargo;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTurno;
import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;
import br.com.ambientinformatica.ivolunteer.entidade.Turma;
import br.com.ambientinformatica.ivolunteer.persistencia.FuncionarioDao;
import br.com.ambientinformatica.ivolunteer.persistencia.TurmaDao;
import br.com.ambientinformatica.ivolunteer.service.TurmaService;

@Controller("TurmaControl")
@Scope("conversation")
public class TurmaControl implements TurmaService{

	private Turma turma = new Turma();

	@Autowired
	private TurmaDao turmaDao;
	@Autowired
	private FuncionarioDao funcionarioDao;
	
	private EnumTurno turnoselecionado;
	private TurmaService turmaService;
	private List<Turma> turmas = new ArrayList<Turma>();
	private List<String> professor = new ArrayList<String>();

	@PostConstruct
	public void init(){
		listar(null);
		//carregaTurnos();
		carregaProfessor();
	}

	public void confirmar(ActionEvent evt){
		try {
			turmaDao.alterar(turma);
			listar(evt);
			turma = new Turma();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt){
		try {
			turmas = turmaDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public TurmaService getTurmaService() {
		return turmaService;
	}

	public void setTurmaService(TurmaService turmaService) {
		this.turmaService = turmaService;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}
	
	public List<String> getProfessor() {
		return professor;
	}

	public void setProfessor(List<String> professor) {
		this.professor = professor;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public EnumTurno getTurnoselecionado() {
		return turnoselecionado;
	}

	public void setTurnoselecionado(EnumTurno turnoselecionado) {
		this.turnoselecionado = turnoselecionado;
	}

	public void carregaProfessor() {
		List<Funcionario> itens;
		EnumCargo[] enumCargo = EnumCargo.values();
		try {
			itens = funcionarioDao.listar();
			for (Funcionario funcionario : itens){
				if(funcionario.getCargo() == enumCargo[0])
					professor.add(funcionario.getNomePessoa());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> completeEnumTurno(String query){
		List<String> retorno = new ArrayList<String>();
		EnumTurno[] enunsTurno = EnumTurno.values();
		for (int i = 0; i < enunsTurno.length; i++) {
			retorno.add(enunsTurno[i].getDescricao());	
		}

		return retorno;
	}

	@Override
	public Set<EnumTurno> getTurno() {
		return EnumTurno.getListaTurnos();
	}
	
	public static EnumTurno getTurno(int codigo){
		if(codigo == 0) return null;
		EnumTurno[] enumTurno = EnumTurno.values();
		for (EnumTurno tipo : enumTurno){
			if (Integer.parseInt(tipo.getCodigo()) == codigo){
				return tipo;
			}
		}
		return null;
	}
	

	/*
	// Aplica Filtro por identificador
			public void aplicarFiltro(ActionEvent evt) {
				try {
					if (this.turma.getIdentificador().isEmpty()) {
						this.agrupamentoTurmas = this.agrupamentoTurmaDao.listar();
					} else {
						this.agrupamentoTurmas = this.agrupamentoTurmaDao.listarIdentificador(this.agrupamentoTurma);
					}
				} catch (Exception e) {
					UtilFaces.addMensagemFaces(e);
				}

			}
			public void listaAgrupamentosDeTurmas() {
				try {
					this.agrupamentoTurmas = agrupamentoTurmaDao.listar();
				} catch (PersistenciaException e) {
					UtilFaces.addMensagemFaces(e);
				}
			}

			public void carregaAgrupamentoTurma(AgrupamentoTurma agrupamentoTurma){
				this.agrupamentoTurma = agrupamentoTurma;		
			}

			public void excluir(AgrupamentoTurma agrupamentoTurma) {
				try {
					agrupamentoTurmaDao.excluirPorId(agrupamentoTurma.getId());
					UtilFaces.addMensagemFaces("Funcionário excluido com sucesso!");
				} catch (PersistenciaException e) {
					UtilFaces.addMensagemFaces(e);
				}

			}
	 */
}
