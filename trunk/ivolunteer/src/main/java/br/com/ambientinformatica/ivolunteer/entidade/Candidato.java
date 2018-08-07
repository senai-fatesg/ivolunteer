package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;

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
	
	@Enumerated(EnumType.STRING)
	private EnumPNE enumPNE;
	
	private String parentescoPNE;
	private String necessidadePNE;
	
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
