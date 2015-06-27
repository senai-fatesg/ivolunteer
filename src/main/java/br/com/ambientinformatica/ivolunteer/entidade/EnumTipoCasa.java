package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumTipoCasa implements IEnum {

	PROPRIA("Propria"), 
	ALUGADA("Alugada"), 
	CEDIDA("Cedida"),
	OUTRA("Outra");

	private String descricao;

	private EnumTipoCasa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
