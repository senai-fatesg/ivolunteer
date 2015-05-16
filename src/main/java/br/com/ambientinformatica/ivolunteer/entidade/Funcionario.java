package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Funcionario extends Pessoa {

	private String pis;

	private String carteiraDeTrabalho;

	private String serieCarteiraDeTrabalho;

	private String reservista;

	@Temporal(TemporalType.DATE)
	private Date emissaoReservista;

	private String cNH;

	@Temporal(TemporalType.DATE)
	private Date emissaoCNH;

	@Temporal(TemporalType.DATE)
	private Date validadeCNH;

	private String nomePai;

	private String nomeMae;

	private String email;

	private String titulo;

	private String zona;

	private String secao;

	private boolean exameAdmissional;

	@Temporal(TemporalType.DATE)
	private Date dataExameAdmissional;

	@Temporal(TemporalType.DATE)
	private Date dataAdmissao;

	@Temporal(TemporalType.DATE)
	private Date inicioExperiencia;

	@Temporal(TemporalType.DATE)
	private Date terminoExperiencia;

	private String banco;

	private String agencia;

	private String conta;

	@Temporal(TemporalType.DATE)
	private Date dataDemissao;

	private Double valorAcerto;

	private String observacao;

	private String historico;

	@Enumerated
	private EnumFuncao funcao;

//	@OneToMany
	private List<Dependente> dependente = new ArrayList<Dependente>();

	@OneToMany
	private List<Frequencia> frequencia = new ArrayList<Frequencia>();

	@OneToMany
	private List<GradeHorario> gradeHorario = new ArrayList<GradeHorario>();

	@OneToMany
	private List<AtividadeDiaria> atividadeDiaria = new ArrayList<AtividadeDiaria>();

	public Funcionario() {
		super();

	}

	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}

	public String getCarteiraDeTrabalho() {
		return carteiraDeTrabalho;
	}

	public void setCarteiraDeTrabalho(String carteiraDeTrabalho) {
		this.carteiraDeTrabalho = carteiraDeTrabalho;
	}

	public String getSerieCarteiraDeTrabalho() {
		return serieCarteiraDeTrabalho;
	}

	public void setSerieCarteiraDeTrabalho(String serieCarteiraDeTrabalho) {
		this.serieCarteiraDeTrabalho = serieCarteiraDeTrabalho;
	}

	public String getReservista() {
		return reservista;
	}

	public void setReservista(String reservista) {
		this.reservista = reservista;
	}

	public Date getEmissaoReservista() {
		return emissaoReservista;
	}

	public void setEmissaoReservista(Date emissaoReservista) {
		this.emissaoReservista = emissaoReservista;
	}

	public String getcNH() {
		return cNH;
	}

	public void setcNH(String cNH) {
		this.cNH = cNH;
	}

	public Date getEmissaoCNH() {
		return emissaoCNH;
	}

	public void setEmissaoCNH(Date emissaoCNH) {
		this.emissaoCNH = emissaoCNH;
	}

	public Date getValidadeCNH() {
		return validadeCNH;
	}

	public void setValidadeCNH(Date validadeCNH) {
		this.validadeCNH = validadeCNH;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getSecao() {
		return secao;
	}

	public void setSecao(String secao) {
		this.secao = secao;
	}

	public boolean isExameAdmissional() {
		return exameAdmissional;
	}

	public void setExameAdmissional(boolean exameAdmissional) {
		this.exameAdmissional = exameAdmissional;
	}

	public Date getDataExameAdmissional() {
		return dataExameAdmissional;
	}

	public void setDataExameAdmissional(Date dataExameAdmissional) {
		this.dataExameAdmissional = dataExameAdmissional;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public Date getInicioExperiencia() {
		return inicioExperiencia;
	}

	public void setInicioExperiencia(Date inicioExperiencia) {
		this.inicioExperiencia = inicioExperiencia;
	}

	public Date getTerminoExperiencia() {
		return terminoExperiencia;
	}

	public void setTerminoExperiencia(Date terminoExperiencia) {
		this.terminoExperiencia = terminoExperiencia;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public Date getDataDemissao() {
		return dataDemissao;
	}

	public void setDataDemissao(Date dataDemissao) {
		this.dataDemissao = dataDemissao;
	}

	public Double getValorAcerto() {
		return valorAcerto;
	}

	public void setValorAcerto(Double valorAcerto) {
		this.valorAcerto = valorAcerto;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public EnumFuncao getFuncao() {
		return funcao;
	}

	public void setFuncao(EnumFuncao funcao) {
		this.funcao = funcao;
	}

	public List<Dependente> getDependente() {
		return dependente;
	}

	public void setDependente(List<Dependente> dependente) {
		this.dependente = dependente;
	}

	public List<Frequencia> getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(List<Frequencia> frequencia) {
		this.frequencia = frequencia;
	}

	public List<GradeHorario> getGradeHorario() {
		return gradeHorario;
	}

	public void setGradeHorario(List<GradeHorario> gradeHorario) {
		this.gradeHorario = gradeHorario;
	}

	public List<AtividadeDiaria> getAtividadeDiaria() {
		return atividadeDiaria;
	}

	public void setAtividadeDiaria(List<AtividadeDiaria> atividadeDiaria) {
		this.atividadeDiaria = atividadeDiaria;
	}

}
