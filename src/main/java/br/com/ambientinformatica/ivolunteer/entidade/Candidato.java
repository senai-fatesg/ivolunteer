package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CascadeType;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Candidato extends Pessoa implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Boolean isAtivo = true;
	private String formacao;
	private Date ano;
	private Date semestre;
	private String turno;
	private String nomeDoCurso;
	
	private String nomedoPai;
	private String nomedaMae;
	private String nomedoavoPaterno;
	private String nomedaavoPaterno;
	private String nomedoavoMaterno;
	private String nomedaavoMaterno;
	private String observacao;
	
	private String telefoneCelular;
	private String telefoneResidencial;
	private String nomeTelefoneEmergencia;
	private String telefoneEmergencia;
	
	@Enumerated(EnumType.STRING)
	private EnumReside enumReside;
	
	@Enumerated(EnumType.STRING)
	private EnumResidencia enumResidencia;
	
	private String residenciaOutro;
	
	@Enumerated(EnumType.STRING)
	private EnumAcessoComputador enumAcessoComputador;
	
	@Enumerated(EnumType.STRING)
	private EnumTemCelular enumTemCelular;
	
	@Enumerated(EnumType.STRING)
	private EnumTemInternet enumTemInternet;
	
	@Enumerated(EnumType.STRING)
	private EnumLocalInternet enumLocalInternet;
	
	private String localInternetOutro;
	
	@Enumerated(EnumType.STRING)
	private EnumParticipacaoEconomicaFamilia enumParticipacaoEconomicaFamilia;
	
	private String anoEscolaridade;
	
	@Enumerated(EnumType.STRING)
	private EnumPNE enumPNE;
	
	@Enumerated(EnumType.STRING)
	private EnumEscolaridade enumEscolaridade;
	
	@Temporal(TemporalType.DATE)
	private Date anoDeConclusao;
	
	@Temporal(TemporalType.DATE)
	private Date anoDeDesistencia;
	
	private String instituicao;
	
	@Enumerated(EnumType.STRING)
	private EnumStatusDesistiu enumStatusDesistiu;
	@Enumerated(EnumType.STRING)
	private EnumStatusConcluiu enumStatusConcluiu;
	
	private Boolean temPrioridade = false;
	
	private String parentescoPNE;
	private String necessidadePNE;
	
	public String getAnoEscolaridade() {
		return anoEscolaridade;
	}
	public void setAnoEscolaridade(String anoEscolaridade) {
		this.anoEscolaridade = anoEscolaridade;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public EnumStatusDesistiu getEnumStatusDesistiu() {
		return enumStatusDesistiu;
	}
	public void setEnumStatusDesistiu(EnumStatusDesistiu enumStatusDesistiu) {
		this.enumStatusDesistiu = enumStatusDesistiu;
	}
	public EnumStatusConcluiu getEnumStatusConcluiu() {
		return enumStatusConcluiu;
	}
	public void setEnumStatusConcluiu(EnumStatusConcluiu enumStatusConcluiu) {
		this.enumStatusConcluiu = enumStatusConcluiu;
	}
	public String getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}
	public EnumEscolaridade getEnumEscolaridade() {
		return enumEscolaridade;
	}
	public void setEnumEscolaridade(EnumEscolaridade enumEscolaridade) {
		this.enumEscolaridade = enumEscolaridade;
	}
	public Date getAnoDeConclusao() {
		return anoDeConclusao;
	}
	public void setAnoDeConclusao(Date anoDeConclusao) {
		this.anoDeConclusao = anoDeConclusao;
	}
	public Date getAnoDeDesistencia() {
		return anoDeDesistencia;
	}
	public void setAnoDeDesistencia(Date anoDeDesistencia) {
		this.anoDeDesistencia = anoDeDesistencia;
	}
	public Boolean getTemPrioridade() {
		return temPrioridade;
	}
	public void setTemPrioridade(Boolean temPrioridade) {
		this.temPrioridade = temPrioridade;
	}
	public String getTelefoneCelular() {
		return telefoneCelular;
	}
	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}
	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}
	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}
	public String getNomeTelefoneEmergencia() {
		return nomeTelefoneEmergencia;
	}
	public void setNomeTelefoneEmergencia(String nomeTelefoneEmergencia) {
		this.nomeTelefoneEmergencia = nomeTelefoneEmergencia;
	}
	public String getTelefoneEmergencia() {
		return telefoneEmergencia;
	}
	public void setTelefoneEmergencia(String telefoneEmergencia) {
		this.telefoneEmergencia = telefoneEmergencia;
	}
	public Boolean getIsAtivo() {
		return isAtivo;
	}
	public void setIsAtivo(Boolean isAtivo) {
		this.isAtivo = isAtivo;
	}
	public String getFormacao() {
		return formacao;
	}
	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}
	public Date getAno() {
		return ano;
	}
	public void setAno(Date ano) {
		this.ano = ano;
	}
	public Date getSemestre() {
		return semestre;
	}
	public void setSemestre(Date semestre) {
		this.semestre = semestre;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public String getNomeDoCurso() {
		return nomeDoCurso;
	}
	public void setNomeDoCurso(String nomeDoCurso) {
		this.nomeDoCurso = nomeDoCurso;
	}
	public String getNomedoPai() {
		return nomedoPai;
	}
	public void setNomedoPai(String nomedoPai) {
		this.nomedoPai = nomedoPai;
	}
	public String getNomedaMae() {
		return nomedaMae;
	}
	public void setNomedaMae(String nomedaMae) {
		this.nomedaMae = nomedaMae;
	}
	public String getNomedoavoPaterno() {
		return nomedoavoPaterno;
	}
	public void setNomedoavoPaterno(String nomedoavoPaterno) {
		this.nomedoavoPaterno = nomedoavoPaterno;
	}
	public String getNomedaavoPaterno() {
		return nomedaavoPaterno;
	}
	public void setNomedaavoPaterno(String nomedaavoPaterno) {
		this.nomedaavoPaterno = nomedaavoPaterno;
	}
	public String getNomedoavoMaterno() {
		return nomedoavoMaterno;
	}
	public void setNomedoavoMaterno(String nomedoavoMaterno) {
		this.nomedoavoMaterno = nomedoavoMaterno;
	}
	public String getNomedaavoMaterno() {
		return nomedaavoMaterno;
	}
	public void setNomedaavoMaterno(String nomedaavoMaterno) {
		this.nomedaavoMaterno = nomedaavoMaterno;
	}
	public EnumReside getEnumReside() {
		return enumReside;
	}
	public void setEnumReside(EnumReside enumReside) {
		this.enumReside = enumReside;
	}
	public EnumResidencia getEnumResidencia() {
		return enumResidencia;
	}
	public void setEnumResidencia(EnumResidencia enumResidencia) {
		this.enumResidencia = enumResidencia;
	}
	public String getResidenciaOutro() {
		return residenciaOutro;
	}
	public void setResidenciaOutro(String residenciaOutro) {
		this.residenciaOutro = residenciaOutro;
	}
	public EnumAcessoComputador getEnumAcessoComputador() {
		return enumAcessoComputador;
	}
	public void setEnumAcessoComputador(EnumAcessoComputador enumAcessoComputador) {
		this.enumAcessoComputador = enumAcessoComputador;
	}
	public EnumTemCelular getEnumTemCelular() {
		return enumTemCelular;
	}
	public void setEnumTemCelular(EnumTemCelular enumTemCelular) {
		this.enumTemCelular = enumTemCelular;
	}
	public EnumTemInternet getEnumTemInternet() {
		return enumTemInternet;
	}
	public void setEnumTemInternet(EnumTemInternet enumTemInternet) {
		this.enumTemInternet = enumTemInternet;
	}
	public EnumLocalInternet getEnumLocalInternet() {
		return enumLocalInternet;
	}
	public void setEnumLocalInternet(EnumLocalInternet enumLocalInternet) {
		this.enumLocalInternet = enumLocalInternet;
	}
	public String getLocalInternetOutro() {
		return localInternetOutro;
	}
	public void setLocalInternetOutro(String localInternetOutro) {
		this.localInternetOutro = localInternetOutro;
	}
	public EnumParticipacaoEconomicaFamilia getEnumParticipacaoEconomicaFamilia() {
		return enumParticipacaoEconomicaFamilia;
	}
	public void setEnumParticipacaoEconomicaFamilia(EnumParticipacaoEconomicaFamilia enumParticipacaoEconomicaFamilia) {
		this.enumParticipacaoEconomicaFamilia = enumParticipacaoEconomicaFamilia;
	}
	public EnumPNE getEnumPNE() {
		return enumPNE;
	}
	public void setEnumPNE(EnumPNE enumPNE) {
		this.enumPNE = enumPNE;
	}
	public String getParentescoPNE() {
		return parentescoPNE;
	}
	public void setParentescoPNE(String parentescoPNE) {
		this.parentescoPNE = parentescoPNE;
	}
	public String getNecessidadePNE() {
		return necessidadePNE;
	}
	public void setNecessidadePNE(String necessidadePNE) {
		this.necessidadePNE = necessidadePNE;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void desativa() {
		setIsAtivo(false);
	}
	
}
