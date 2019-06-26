package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Colaborador extends Pessoa implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	private EnumStatus status;

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

	private String emailDoFuncionario;

	private String emailDaEmpresa;

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

	@Enumerated(EnumType.STRING)
	private EnumTipoFuncionario tipoFuncionario;

	@Enumerated
	private EnumFuncao funcao;

	@Enumerated
	private EnumCargo cargo;

	// atributos de funcionario terceirizado
	private String segmento;

	private String cnpj;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "empresa_id")
	private List<Telefone> telefonesEmpresa = new ArrayList<Telefone>();

	private String nomeEmpresa;

	private String site;

	// atributos de voluntário
	@Temporal(TemporalType.DATE)
	private Date dataEntrada;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "funcionario_id")
	private List<Frequencia> frequencias = new ArrayList<Frequencia>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "funcionario_id")
	private List<GradeHorario> gradesHorario = new ArrayList<GradeHorario>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "funcionario_id")
	private List<AtividadeDiaria> atividadesDiaria = new ArrayList<AtividadeDiaria>();

	@PrePersist
	private void cargaInicial() {
		this.status = EnumStatus.ATIVO;
	}

	public List<Telefone> getTelefonesEmpresa() {
		return telefonesEmpresa;
	}

	public void setTelefonesEmpresa(List<Telefone> telefonesEmpresa) {
		this.telefonesEmpresa = telefonesEmpresa;
	}

	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
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

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
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

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getEmailDoFuncionario() {
		return emailDoFuncionario;
	}

	public void setEmailDoFuncionario(String emailDoFuncionario) {
		this.emailDoFuncionario = emailDoFuncionario;
	}

	public String getEmailDaEmpresa() {
		return emailDaEmpresa;
	}

	public void setEmailDaEmpresa(String emailDaEmpresa) {
		this.emailDaEmpresa = emailDaEmpresa;
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

	public EnumTipoFuncionario getTipoFuncionario() {
		return tipoFuncionario;
	}

	public void setTipoFuncionario(EnumTipoFuncionario tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}

	public EnumFuncao getFuncao() {
		return funcao;
	}

	public void setFuncao(EnumFuncao funcao) {
		this.funcao = funcao;
	}

	public EnumCargo getCargo() {
		return cargo;
	}

	public void setCargo(EnumCargo cargo) {
		this.cargo = cargo;
	}

	public List<Frequencia> getFrequencias() {
		return frequencias;
	}

	public void setFrequencias(List<Frequencia> frequencias) {
		this.frequencias = frequencias;
	}

	public List<GradeHorario> getGradesHorario() {
		return gradesHorario;
	}

	public void setGradesHorario(List<GradeHorario> gradesHorario) {
		this.gradesHorario = gradesHorario;
	}

	public List<AtividadeDiaria> getAtividadesDiaria() {
		return atividadesDiaria;
	}

	public void setAtividadesDiaria(List<AtividadeDiaria> atividadesDiaria) {
		this.atividadesDiaria = atividadesDiaria;
	}

	public EnumStatus getStatus() {
		return status;
	}

	public void setStatus(EnumStatus status) {
		this.status = status;
	}

	public void inativar() {
		this.status = EnumStatus.INATIVO;
	}

	public void ativar() {
		this.status = EnumStatus.ATIVO;
	}

	@Override
	public String toString() {
		return "Funcionario [pis=" + pis + ", carteiraDeTrabalho=" + carteiraDeTrabalho + ", serieCarteiraDeTrabalho="
				+ serieCarteiraDeTrabalho + ", reservista=" + reservista + ", emissaoReservista=" + emissaoReservista
				+ ", cNH=" + cNH + ", emissaoCNH=" + emissaoCNH + ", validadeCNH=" + validadeCNH + ", nomePai="
				+ nomePai + ", nomeMae=" + nomeMae + ", emailDaEmpresa=" + emailDaEmpresa + ", titulo=" + titulo
				+ ", zona=" + zona + ", secao=" + secao + ", exameAdmissional=" + exameAdmissional
				+ ", dataExameAdmissional=" + dataExameAdmissional + ", dataAdmissao=" + dataAdmissao
				+ ", inicioExperiencia=" + inicioExperiencia + ", terminoExperiencia=" + terminoExperiencia + ", banco="
				+ banco + ", agencia=" + agencia + ", conta=" + conta + ", dataDemissao=" + dataDemissao
				+ ", valorAcerto=" + valorAcerto + ", observacao=" + observacao + ", historico=" + historico
				+ ", tipoFuncionario=" + tipoFuncionario + ", funcao=" + funcao + ", cargo=" + cargo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Colaborador other = (Colaborador) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}

	public void addTelefoneEmpresa(Telefone telefoneEmpresa) {
		if (!this.telefonesEmpresa.contains(telefoneEmpresa)) {
			this.telefonesEmpresa.add(telefoneEmpresa);
		}
	}

}
