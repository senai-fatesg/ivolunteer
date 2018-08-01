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

	private Boolean isAtivo = true;
	
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
	private EnumTipoFuncionario tipoFuncionario;
	
	@Enumerated
	private EnumFuncao funcao;
	
	@Enumerated
	private EnumCargo cargo;
	
	// atributos de funcionario terceirizado
	private String segmento;
	
	private String cnpj;
	
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

	public Boolean getIsAtivo() {
		return isAtivo;
	}

	public void setIsAtivo(Boolean isAtivo) {
		this.isAtivo = isAtivo;
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

	@Override
	public String toString() {
		return "Funcionario [pis=" + pis + ", carteiraDeTrabalho=" + carteiraDeTrabalho + ", serieCarteiraDeTrabalho="
				+ serieCarteiraDeTrabalho + ", reservista=" + reservista + ", emissaoReservista=" + emissaoReservista
				+ ", cNH=" + cNH + ", emissaoCNH=" + emissaoCNH + ", validadeCNH=" + validadeCNH + ", nomePai="
				+ nomePai + ", nomeMae=" + nomeMae + ", email=" + email + ", titulo=" + titulo + ", zona=" + zona
				+ ", secao=" + secao + ", exameAdmissional=" + exameAdmissional + ", dataExameAdmissional="
				+ dataExameAdmissional + ", dataAdmissao=" + dataAdmissao + ", inicioExperiencia=" + inicioExperiencia
				+ ", terminoExperiencia=" + terminoExperiencia + ", banco=" + banco + ", agencia=" + agencia
				+ ", conta=" + conta + ", dataDemissao=" + dataDemissao + ", valorAcerto=" + valorAcerto
				+ ", observacao=" + observacao + ", historico=" + historico + ", tipoFuncionario=" + tipoFuncionario
				+ ", funcao=" + funcao + ", cargo=" + cargo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((agencia == null) ? 0 : agencia.hashCode());
		result = prime * result + ((atividadesDiaria == null) ? 0 : atividadesDiaria.hashCode());
		result = prime * result + ((banco == null) ? 0 : banco.hashCode());
		result = prime * result + ((cNH == null) ? 0 : cNH.hashCode());
		result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
		result = prime * result + ((carteiraDeTrabalho == null) ? 0 : carteiraDeTrabalho.hashCode());
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		result = prime * result + ((dataAdmissao == null) ? 0 : dataAdmissao.hashCode());
		result = prime * result + ((dataDemissao == null) ? 0 : dataDemissao.hashCode());
		result = prime * result + ((dataExameAdmissional == null) ? 0 : dataExameAdmissional.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((emissaoCNH == null) ? 0 : emissaoCNH.hashCode());
		result = prime * result + ((emissaoReservista == null) ? 0 : emissaoReservista.hashCode());
		result = prime * result + ((exameAdmissional == null) ? 0 : exameAdmissional.hashCode());
		result = prime * result + ((frequencias == null) ? 0 : frequencias.hashCode());
		result = prime * result + ((funcao == null) ? 0 : funcao.hashCode());
		result = prime * result + ((gradesHorario == null) ? 0 : gradesHorario.hashCode());
		result = prime * result + ((historico == null) ? 0 : historico.hashCode());
		result = prime * result + ((inicioExperiencia == null) ? 0 : inicioExperiencia.hashCode());
		result = prime * result + ((nomeMae == null) ? 0 : nomeMae.hashCode());
		result = prime * result + ((nomePai == null) ? 0 : nomePai.hashCode());
		result = prime * result + ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + ((pis == null) ? 0 : pis.hashCode());
		result = prime * result + ((reservista == null) ? 0 : reservista.hashCode());
		result = prime * result + ((secao == null) ? 0 : secao.hashCode());
		result = prime * result + ((serieCarteiraDeTrabalho == null) ? 0 : serieCarteiraDeTrabalho.hashCode());
		result = prime * result + ((terminoExperiencia == null) ? 0 : terminoExperiencia.hashCode());
		result = prime * result + ((tipoFuncionario == null) ? 0 : tipoFuncionario.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((validadeCNH == null) ? 0 : validadeCNH.hashCode());
		result = prime * result + ((valorAcerto == null) ? 0 : valorAcerto.hashCode());
		result = prime * result + ((zona == null) ? 0 : zona.hashCode());
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
		Funcionario other = (Funcionario) obj;
		if (agencia == null) {
			if (other.agencia != null)
				return false;
		} else if (!agencia.equals(other.agencia))
			return false;
		if (atividadesDiaria == null) {
			if (other.atividadesDiaria != null)
				return false;
		} else if (!atividadesDiaria.equals(other.atividadesDiaria))
			return false;
		if (banco == null) {
			if (other.banco != null)
				return false;
		} else if (!banco.equals(other.banco))
			return false;
		if (cNH == null) {
			if (other.cNH != null)
				return false;
		} else if (!cNH.equals(other.cNH))
			return false;
		if (cargo != other.cargo)
			return false;
		if (carteiraDeTrabalho == null) {
			if (other.carteiraDeTrabalho != null)
				return false;
		} else if (!carteiraDeTrabalho.equals(other.carteiraDeTrabalho))
			return false;
		if (conta == null) {
			if (other.conta != null)
				return false;
		} else if (!conta.equals(other.conta))
			return false;
		if (dataAdmissao == null) {
			if (other.dataAdmissao != null)
				return false;
		} else if (!dataAdmissao.equals(other.dataAdmissao))
			return false;
		if (dataDemissao == null) {
			if (other.dataDemissao != null)
				return false;
		} else if (!dataDemissao.equals(other.dataDemissao))
			return false;
		if (dataExameAdmissional == null) {
			if (other.dataExameAdmissional != null)
				return false;
		} else if (!dataExameAdmissional.equals(other.dataExameAdmissional))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (emissaoCNH == null) {
			if (other.emissaoCNH != null)
				return false;
		} else if (!emissaoCNH.equals(other.emissaoCNH))
			return false;
		if (emissaoReservista == null) {
			if (other.emissaoReservista != null)
				return false;
		} else if (!emissaoReservista.equals(other.emissaoReservista))
			return false;
		if (exameAdmissional == null) {
			if (other.exameAdmissional != null)
				return false;
		} else if (!exameAdmissional.equals(other.exameAdmissional))
			return false;
		if (frequencias == null) {
			if (other.frequencias != null)
				return false;
		} else if (!frequencias.equals(other.frequencias))
			return false;
		if (funcao != other.funcao)
			return false;
		if (gradesHorario == null) {
			if (other.gradesHorario != null)
				return false;
		} else if (!gradesHorario.equals(other.gradesHorario))
			return false;
		if (historico == null) {
			if (other.historico != null)
				return false;
		} else if (!historico.equals(other.historico))
			return false;
		if (inicioExperiencia == null) {
			if (other.inicioExperiencia != null)
				return false;
		} else if (!inicioExperiencia.equals(other.inicioExperiencia))
			return false;
		if (nomeMae == null) {
			if (other.nomeMae != null)
				return false;
		} else if (!nomeMae.equals(other.nomeMae))
			return false;
		if (nomePai == null) {
			if (other.nomePai != null)
				return false;
		} else if (!nomePai.equals(other.nomePai))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (pis == null) {
			if (other.pis != null)
				return false;
		} else if (!pis.equals(other.pis))
			return false;
		if (reservista == null) {
			if (other.reservista != null)
				return false;
		} else if (!reservista.equals(other.reservista))
			return false;
		if (secao == null) {
			if (other.secao != null)
				return false;
		} else if (!secao.equals(other.secao))
			return false;
		if (serieCarteiraDeTrabalho == null) {
			if (other.serieCarteiraDeTrabalho != null)
				return false;
		} else if (!serieCarteiraDeTrabalho.equals(other.serieCarteiraDeTrabalho))
			return false;
		if (terminoExperiencia == null) {
			if (other.terminoExperiencia != null)
				return false;
		} else if (!terminoExperiencia.equals(other.terminoExperiencia))
			return false;
		if (tipoFuncionario != other.tipoFuncionario)
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (validadeCNH == null) {
			if (other.validadeCNH != null)
				return false;
		} else if (!validadeCNH.equals(other.validadeCNH))
			return false;
		if (valorAcerto == null) {
			if (other.valorAcerto != null)
				return false;
		} else if (!valorAcerto.equals(other.valorAcerto))
			return false;
		if (zona == null) {
			if (other.zona != null)
				return false;
		} else if (!zona.equals(other.zona))
			return false;
		return true;
	}

	public void desativa() {
		this.setIsAtivo(false);
	}
	
}
