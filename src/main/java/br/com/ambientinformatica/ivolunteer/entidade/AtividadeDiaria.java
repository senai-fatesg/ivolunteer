package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AtividadeDiaria {

	@Id
	@GeneratedValue(generator = "atividade_diaria_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "atividade_diaria_seq", sequenceName = "atividade_diaria_seq", allocationSize = 1, initialValue = 1)
	private int id;

	@Temporal(TemporalType.TIME)
	private Date horaInicial;

	@Temporal(TemporalType.TIME)
	private Date horaFinal;

	private String atividade;

	private String local;

	private String observacao;

	@ManyToOne
	private Funcionario funcionario;
	
	@ManyToOne
	private Aluno aluno;

	public int getId() {
		return id;
	}

	public Date getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(Date horaInicial) {
		this.horaInicial = horaInicial;
	}

	public Date getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(Date horaFinal) {
		this.horaFinal = horaFinal;
	}

	public String getAtividade() {
		return atividade;
	}

	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	

}
