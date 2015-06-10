package br.com.ambientinformatica.ivolunteer.entidade;

import java.math.BigDecimal;

public class SelecaoCandidato {

	private String nomePessoa;
	private EnumPrioridade prioridade;
	private boolean beneficios = false;
	
	private BigDecimal valorInicial = BigDecimal.ZERO;
	private BigDecimal valorFinal = BigDecimal.ZERO;
	public String getNomePessoa() {
		return nomePessoa;
	}
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}
	public EnumPrioridade getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(EnumPrioridade prioridade) {
		this.prioridade = prioridade;
	}
	public boolean isBeneficios() {
		return beneficios;
	}
	public void setBeneficios(boolean beneficios) {
		this.beneficios = beneficios;
	}
	public BigDecimal getValorInicial() {
		return valorInicial;
	}
	public void setValorInicial(BigDecimal valorInicial) {
		this.valorInicial = valorInicial;
	}
	public BigDecimal getValorFinal() {
		return valorFinal;
	}
	public void setValorFinal(BigDecimal valorFinal) {
		this.valorFinal = valorFinal;
	}
	
}
