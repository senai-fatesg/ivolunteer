package br.com.ambientinformatica.ivolunteer.entidade;

import java.math.BigDecimal;

public class SelecaoCandidato {

	private String nomePessoa;
	private EnumPrioridade prioridade;
	private Boolean nome;
	private Boolean rendaFamiliar;
	private Boolean beneficios;
	
	private BigDecimal valorInicial = BigDecimal.ZERO;
	private BigDecimal valorFinal = BigDecimal.ZERO;
	
	public EnumPrioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(EnumPrioridade prioridade) {
		this.prioridade = prioridade;
	}

	public Boolean getRendaFamiliar() {
		return rendaFamiliar;
	}

	public void setRendaFamiliar(Boolean rendaFamiliar) {
		this.rendaFamiliar = rendaFamiliar;
	}

	public Boolean getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(Boolean beneficios) {
		this.beneficios = beneficios;
	}

	public Boolean getNome() {
		return nome;
	}

	public void setNome(Boolean nome) {
		this.nome = nome;
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

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}
}
