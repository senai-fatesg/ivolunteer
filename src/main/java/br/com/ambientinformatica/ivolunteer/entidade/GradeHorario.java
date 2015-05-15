package br.com.ambientinformatica.ivolunteer.entidade;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class GradeHorario {

	@Id
	@GeneratedValue(generator = "gradehorario_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gradehorario_seq", sequenceName = "gradehorario_seq", allocationSize = 1, initialValue = 1)
	private Integer id;

	@Enumerated
	private EnumDiaSemana diaSemana;
	
   @Temporal(TemporalType.TIME)
	private Date horarioEntrada;
   
   @Temporal(TemporalType.TIME)
	private Date horarioSaida;

   @ManyToOne
	private Funcionario funcionario;

	public Date getTotal() {
		return null;
	}

	public EnumDiaSemana getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(EnumDiaSemana diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Date getHorarioEntrada() {
		return horarioEntrada;
	}

	public void setHorarioEntrada(Date horarioEntrada) {
		this.horarioEntrada = horarioEntrada;
	}

	public Date getHorarioSaida() {
		return horarioSaida;
	}

	public void setHorarioSaida(Date horarioSaida) {
		this.horarioSaida = horarioSaida;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
