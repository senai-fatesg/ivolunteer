package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Aluno extends Pessoa{


	@Temporal(TemporalType.DATE)
	private Date dataSaida;

	@Temporal(TemporalType.DATE)
	private Date dataExame;

	@OneToMany
	private List<Frequencia> frequencia;

	@OneToMany
	private List<RelatorioAprendizagem> relatorioApredizagem;

	@OneToOne
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

	public List<Frequencia> getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(List<Frequencia> frequencia) {
		this.frequencia = frequencia;
	}

	public List<RelatorioAprendizagem> getRelatorioApredizagem() {
		return relatorioApredizagem;
	}

	public void setRelatorioApredizagem(List<RelatorioAprendizagem> relatorioApredizagem) {
		this.relatorioApredizagem = relatorioApredizagem;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

}
