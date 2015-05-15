package br.com.ambientinformatica.ivolunteer.entidade;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Matricula {

	@Id
	@GeneratedValue(generator = "matricula_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "matricula_seq", sequenceName = "matricula_seq", allocationSize = 1, initialValue = 1)
	private Integer id;

	private String certidaoNascimentoAntigaLivro;

	private String certidaoNascimentoAntigaFolha;

	private String certidaoNascimentoAntigaCartorio;

	private String certidaoNascimentoNova;

	private String nIS;

	private String idINEP;

	private String bolsaFamilia;

	private Date dataMatricula;

	private Date ano;

	private Aluno aluno;

	private Matricula matricula;

	private Turma turma;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCertidaoNascimentoAntigaLivro() {
		return certidaoNascimentoAntigaLivro;
	}

	public void setCertidaoNascimentoAntigaLivro(
			String certidaoNascimentoAntigaLivro) {
		this.certidaoNascimentoAntigaLivro = certidaoNascimentoAntigaLivro;
	}

	public String getCertidaoNascimentoAntigaFolha() {
		return certidaoNascimentoAntigaFolha;
	}

	public void setCertidaoNascimentoAntigaFolha(
			String certidaoNascimentoAntigaFolha) {
		this.certidaoNascimentoAntigaFolha = certidaoNascimentoAntigaFolha;
	}

	public String getCertidaoNascimentoAntigaCartorio() {
		return certidaoNascimentoAntigaCartorio;
	}

	public void setCertidaoNascimentoAntigaCartorio(
			String certidaoNascimentoAntigaCartorio) {
		this.certidaoNascimentoAntigaCartorio = certidaoNascimentoAntigaCartorio;
	}

	public String getCertidaoNascimentoNova() {
		return certidaoNascimentoNova;
	}

	public void setCertidaoNascimentoNova(String certidaoNascimentoNova) {
		this.certidaoNascimentoNova = certidaoNascimentoNova;
	}

	public String getnIS() {
		return nIS;
	}

	public void setnIS(String nIS) {
		this.nIS = nIS;
	}

	public String getIdINEP() {
		return idINEP;
	}

	public void setIdINEP(String idINEP) {
		this.idINEP = idINEP;
	}

	public String getBolsaFamilia() {
		return bolsaFamilia;
	}

	public void setBolsaFamilia(String bolsaFamilia) {
		this.bolsaFamilia = bolsaFamilia;
	}

	public Date getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public Date getAno() {
		return ano;
	}

	public void setAno(Date ano) {
		this.ano = ano;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

}
