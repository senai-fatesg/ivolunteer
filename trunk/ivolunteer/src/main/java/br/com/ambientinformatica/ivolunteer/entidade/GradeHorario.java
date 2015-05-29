package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((diaSemana == null) ? 0 : diaSemana.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GradeHorario other = (GradeHorario) obj;
		if (diaSemana != other.diaSemana)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
