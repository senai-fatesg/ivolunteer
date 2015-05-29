package br.com.ambientinformatica.ivolunteer.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class AgrupamentoTurma {

	@Id
	@GeneratedValue(generator = "agrupamento_turma_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "agrupamento_turma_seq", sequenceName = "agrupamento_turma_seq", allocationSize = 1, initialValue = 1)
	private Integer id;

	private String identificador;

	private Double idadeInicial;

	private Double idadeFinal;

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

	public Double getIdadeInicial() {
		return idadeInicial;
	}

	public void setIdadeInicial(Double idadeInicial) {
		this.idadeInicial = idadeInicial;
	}

	public Double getIdadeFinal() {
		return idadeFinal;
	}

	public void setIdadeFinal(Double idadeFinal) {
		this.idadeFinal = idadeFinal;
	}

	public Integer getNumeroCriancas() {
		return numeroCriancas;
	}

	public void setNumeroCriancas(Integer numeroCriancas) {
		this.numeroCriancas = numeroCriancas;
	}
}
