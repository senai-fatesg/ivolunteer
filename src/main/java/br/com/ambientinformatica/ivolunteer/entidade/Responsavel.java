package br.com.ambientinformatica.ivolunteer.entidade;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Responsavel extends Pessoa implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	public Responsavel() {}
	
	private Boolean isAtivo = true;
	private String indicacao;
	private String informacoesSobreIntituicao;
	private String nomePessoaMoraComCrianca;
	private String necessidadesEspeciais;

	@Enumerated(EnumType.STRING)
	private EnumEscolaridade enumEscolaridade;

	@Enumerated(EnumType.STRING)
	private EnumTipoCasa enumTipoMoradia;

	@Enumerated(EnumType.STRING)
	private EnumFiliacao enumFiliacao;

	

	private BigDecimal valorBeneficio = BigDecimal.ZERO;

	private BigDecimal rendaExtra = BigDecimal.ZERO;
	private BigDecimal renda = BigDecimal.ZERO;
	private BigDecimal totalRenda = BigDecimal.ZERO;
	private Integer numeroFilhosMatriculados;
	private BigDecimal valorAluguel = BigDecimal.ZERO;
	private Integer numeroDePessoasMoradia;

	private Boolean paisVivemJuntos;
	private Boolean requisitouOutraVaga;
	private Boolean recebeBeneficio = false;
	private Boolean requisitouVagaParaOutraCrianca;

	public String getIndicacao() {
		return indicacao;
	}

	public void setIndicacao(String indicacao) {
		this.indicacao = indicacao;
	}

	public Boolean getIsAtivo() {
		return isAtivo;
	}

	public void setIsAtivo(Boolean isAtivo) {
		this.isAtivo = isAtivo;
	}
	
	public void inativaResponsavel() {
		setIsAtivo(false);
	}

	public String getInformacoesSobreIntituicao() {
		return informacoesSobreIntituicao;
	}

	public void setInformacoesSobreIntituicao(String informacoesSobreIntituicao) {
		this.informacoesSobreIntituicao = informacoesSobreIntituicao;
	}

	public String getNomePessoaMoraComCrianca() {
		return nomePessoaMoraComCrianca;
	}

	public void setNomePessoaMoraComCrianca(String nomePessoaMoraComCrianca) {
		this.nomePessoaMoraComCrianca = nomePessoaMoraComCrianca;
	}

	public EnumEscolaridade getEnumEscolaridade() {
		return enumEscolaridade;
	}

	public void setEnumEscolaridade(EnumEscolaridade enumEscolaridade) {
		this.enumEscolaridade = enumEscolaridade;
	}

	public EnumTipoCasa getEnumTipoMoradia() {
		return enumTipoMoradia;
	}

	public void setEnumTipoMoradia(EnumTipoCasa enumTipoMoradia) {
		this.enumTipoMoradia = enumTipoMoradia;
	}

	public EnumFiliacao getEnumFiliacao() {
		return enumFiliacao;
	}

	public void setEnumFiliacao(EnumFiliacao enumFiliacao) {
		this.enumFiliacao = enumFiliacao;
	}

	public BigDecimal getValorBeneficio() {
		return valorBeneficio;
	}

	public void setValorBeneficio(BigDecimal valorBeneficio) {
		this.valorBeneficio = valorBeneficio;
	}

	public BigDecimal getRendaExtra() {
		return rendaExtra;
	}

	public void setRendaExtra(BigDecimal rendaExtra) {
		this.rendaExtra = rendaExtra;
	}

	public BigDecimal getRenda() {
		return renda;
	}

	public void setRenda(BigDecimal renda) {
		this.renda = renda;
	}

	public void setTotalRenda(BigDecimal totalRenda) {
		this.totalRenda = totalRenda;
	}

	public Integer getNumeroFilhosMatriculados() {
		return numeroFilhosMatriculados;
	}

	public void setNumeroFilhosMatriculados(Integer numeroFilhosMatriculados) {
		this.numeroFilhosMatriculados = numeroFilhosMatriculados;
	}

	public BigDecimal getValorAluguel() {
		return valorAluguel;
	}

	public void setValorAluguel(BigDecimal valorAluguel) {
		this.valorAluguel = valorAluguel;
	}

	public Integer getNumeroDePessoasMoradia() {
		return numeroDePessoasMoradia;
	}

	public void setNumeroDePessoasMoradia(Integer numeroDePessoasMoradia) {
		this.numeroDePessoasMoradia = numeroDePessoasMoradia;
	}

	public Boolean getPaisVivemJuntos() {
		return paisVivemJuntos;
	}

	public void setPaisVivemJuntos(Boolean paisVivemJuntos) {
		this.paisVivemJuntos = paisVivemJuntos;
	}

	public Boolean getRequisitouOutraVaga() {
		return requisitouOutraVaga;
	}

	public void setRequisitouOutraVaga(Boolean requisitouOutraVaga) {
		this.requisitouOutraVaga = requisitouOutraVaga;
	}

	public Boolean getRecebeBeneficio() {
		return recebeBeneficio;
	}

	public void setRecebeBeneficio(Boolean recebeBeneficio) {
		this.recebeBeneficio = recebeBeneficio;
	}

	public Boolean getRequisitouVagaParaOutraCrianca() {
		return requisitouVagaParaOutraCrianca;
	}

	public void setRequisitouVagaParaOutraCrianca(
	      Boolean requisitouVagaParaOutraCrianca) {
		this.requisitouVagaParaOutraCrianca = requisitouVagaParaOutraCrianca;
	}

	// calcula a renda do responsavel
	public BigDecimal calcularRenda() {
		return this.totalRenda.add(renda).add(rendaExtra);
	}

	public BigDecimal getTotalRenda() {
		return totalRenda;
	}

	public String getNecessidadesEspeciais() {
		return necessidadesEspeciais;
	}

	public void setNecessidadesEspeciais(String necessidadesEspeciais) {
		this.necessidadesEspeciais = necessidadesEspeciais;
	}

}
