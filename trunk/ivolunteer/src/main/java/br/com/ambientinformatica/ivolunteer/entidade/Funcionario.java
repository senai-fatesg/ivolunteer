package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Funcionario extends Pessoa implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

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

	private String exameAdmissional;

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

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "funcionario_id")
	private List<Frequencia> frequencias = new ArrayList<Frequencia>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "funcionario_id")
	private List<GradeHorario> gradesHorario = new ArrayList<GradeHorario>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "funcionario_id")
	private List<AtividadeDiaria> atividadesDiaria = new ArrayList<AtividadeDiaria>();

	public void addAtividadeDiaria(AtividadeDiaria atividadeDiaria) {
		if (!atividadesDiaria.contains(atividadeDiaria)) {
			atividadesDiaria.add(atividadeDiaria);
		}
	}

	public void addGradeHorario(GradeHorario gradeHorario) {
		if (!gradesHorario.contains(gradeHorario)) {
			gradesHorario.add(gradeHorario);
		}
	}
	
	public void removerGradeHorario(GradeHorario gradeHorario) {
		if (gradesHorario.contains(gradeHorario)) {
			this.gradesHorario.remove(gradeHorario);
		}
	}

	public void addFrequencia(Frequencia frequencia) {
		if (!frequencias.contains(frequencia)) {
			frequencias.add(frequencia);
		}
	}

	public void alterarFrequencia(Frequencia frequencia) {
		if (frequencias.contains(frequencia)) {
			frequencias.set(frequencias.indexOf(frequencia), frequencia);
		}
	}
	
	public void removerAtividade(AtividadeDiaria atividadeDiaria) {
		if (atividadesDiaria.contains(atividadeDiaria)) {
			this.atividadesDiaria.remove(atividadeDiaria);
		}
	}

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

	public String getExameAdmissional() {
		return exameAdmissional;
	}

	public void setExameAdmissional(String exameAdmissional) {
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

	public List<Frequencia> getFrequencias() {
		return frequencias;
	}

	public List<GradeHorario> getGradesHorario() {
		return gradesHorario;
	}

	public List<AtividadeDiaria> getAtividadesDiaria() {
		return atividadesDiaria;
	}

}
