package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumTipoTelefone implements IEnum {

	TRABALHO("Trabalho"),
	RESIDENCIAL("Residencial"),
	CELULAR("Celular");

	private String descricao;

	private EnumTipoTelefone(String descricao) {
		this.descricao = descricao;

	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
