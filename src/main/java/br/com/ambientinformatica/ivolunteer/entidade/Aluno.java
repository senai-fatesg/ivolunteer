package br.com.ambientinformatica.ivolunteer.entidade;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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

	@OneToOne(fetch=FetchType.EAGER)
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
	
	public String CalcularIdadeReal(String dataNascimento) {
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

		Date dataNascInput = null;
		try {

			dataNascInput = dataFormatada.parse(dataNascimento);

		} catch (Exception e) {
		}

		Calendar dateOfBirth = new GregorianCalendar();

		dateOfBirth.setTime(dataNascInput);

		// Cria um objeto calendar com a data atual

		Calendar today = Calendar.getInstance();

		// Obtendo a idade baseado no ano

		Integer idade = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
		Integer meses = today.get(Calendar.MONTH) - dateOfBirth.get(Calendar.MONTH);
		
		if(meses < 0)
			meses = meses * -1;

		dateOfBirth.add(Calendar.YEAR, idade);
		dateOfBirth.add(Calendar.MONTH, meses);

		if (today.before(dateOfBirth)) {
			idade--;
		}
		return String.format("%d anos e %d meses", idade, meses);
	}

}
