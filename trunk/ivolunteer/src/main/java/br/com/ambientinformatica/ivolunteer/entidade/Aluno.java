package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Aluno {
	
	@Id
	@GeneratedValue(generator = "aluno_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "aluno_seq", sequenceName = "aluno_seq", allocationSize = 1, initialValue = 1)
	private Integer id;

	private Date dataSaida;

	private Date dataExame;

	private Frequencia frequencia;

	private RelatorioApredizagem relatorioApredizagem;

	private Matricula matricula;

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Date getDataExame() {
		return dataExame;
	}

	public void setDataExame(Date dataExame) {
		this.dataExame = dataExame;
	}

	public Frequencia getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(Frequencia frequencia) {
		this.frequencia = frequencia;
	}

	public RelatorioApredizagem getRelatorioApredizagem() {
		return relatorioApredizagem;
	}

	public void setRelatorioApredizagem(RelatorioApredizagem relatorioApredizagem) {
		this.relatorioApredizagem = relatorioApredizagem;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
	
}
