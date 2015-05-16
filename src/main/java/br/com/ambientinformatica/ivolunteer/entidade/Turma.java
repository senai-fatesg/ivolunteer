package br.com.ambientinformatica.ivolunteer.entidade;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Turma {

	@Id
	@GeneratedValue(generator="turma_seq", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="turma_seq", sequenceName="turma_seq", allocationSize=1, initialValue= 1)
	private Integer id;
	
	private int maximoAlunos;
	
	private EnumTurno turno;
	
	private String nome;
	@ManyToOne
	private AgrupamentoTurma agrupamentoTurma;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="funcionario")
	private List<Funcionario> funcionario;
	
	@OneToOne
	private Matricula matricula;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getMaximoAlunos() {
		return maximoAlunos;
	}

	public void setMaximoAlunos(int maximoAlunos) {
		this.maximoAlunos = maximoAlunos;
	}

	public EnumTurno getTurno() {
		return turno;
	}

	public void setTurno(EnumTurno turno) {
		this.turno = turno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public AgrupamentoTurma getAgrupamentoTurma() {
		return agrupamentoTurma;
	}

	public void setAgrupamentoTurma(AgrupamentoTurma agrupamentoTurma) {
		this.agrupamentoTurma = agrupamentoTurma;
	}

	public List<Funcionario> getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(List<Funcionario> funcionario) {
		this.funcionario = funcionario;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
	
	

}
