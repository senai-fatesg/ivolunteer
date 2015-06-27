package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumDiaSemana implements IEnum {
	
	SEGUNDA("Segunda"), 
	TERCA("Terca"), 
	QUARTA("Quarta"), 
	QUINTA("Quinta"), 
	SEXTA("Sexta"), 
	SABADO("Sabado"), 
	DOMINGO("Domingo");

	private String descricao;

	private EnumDiaSemana(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
