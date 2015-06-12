package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AgrupamentoTurma {

	@Id
	@GeneratedValue(generator = "agrupamento_turma_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "agrupamento_turma_seq", sequenceName = "agrupamento_turma_seq", allocationSize = 1, initialValue = 1)
	private Integer id;

	private String identificador;
	@Temporal(TemporalType.DATE)
	private Date idadeInicial;

	@Temporal(TemporalType.DATE)
	private Date idadeFinal;

	@OneToMany
	private List<Turma> turmas;
	private Integer numeroCriancas;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Date getIdadeInicial() {
		return idadeInicial;
	}

	public void setIdadeInicial(Date idadeInicial) {
		this.idadeInicial = idadeInicial;
	}

	public Date getIdadeFinal() {
		return idadeFinal;
	}

	public void setIdadeFinal(Date idadeFinal) {
		this.idadeFinal = idadeFinal;
	}

	public Integer getNumeroCriancas() {
		return numeroCriancas;
	}

	public void setNumeroCriancas(Integer numeroCriancas) {
		this.numeroCriancas = numeroCriancas;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

}
