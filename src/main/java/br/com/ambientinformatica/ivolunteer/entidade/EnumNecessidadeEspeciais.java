package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumNecessidadeEspeciais implements IEnum {

	DESCRICAO("Descricao");

	private String descricao;

	private EnumNecessidadeEspeciais(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
