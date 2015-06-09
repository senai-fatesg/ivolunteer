package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class RelatorioAprendizagem {

	@Id
	@GeneratedValue(generator = "relatorioaprendizagem_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "relatorioaprendizagem_seq", sequenceName = "relatorioaprendizagem_seq", allocationSize = 1, initialValue = 1)
	private Integer id;
	private int valorPeriodo;
	private EnumTipoPeriodo tipoPeriodo;
	@Temporal(TemporalType.DATE)
	private Date ano;
	private String relatorio;

	@OneToOne
	private Turma turma;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getValorPeriodo() {
		return valorPeriodo;
	}

	public void setValorPeriodo(int valorPeriodo) {
		this.valorPeriodo = valorPeriodo;
	}

	public EnumTipoPeriodo getTipoPeriodo() {
		return tipoPeriodo;
	}

	public void setTipoPeriodo(EnumTipoPeriodo tipoPeriodo) {
		this.tipoPeriodo = tipoPeriodo;
	}

	
	
	public Date getAno() {
		return ano;
	}

	public void setAno(Date ano) {
		this.ano = ano;
	}

	public String getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(String relatorio) {
		this.relatorio = relatorio;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

}
