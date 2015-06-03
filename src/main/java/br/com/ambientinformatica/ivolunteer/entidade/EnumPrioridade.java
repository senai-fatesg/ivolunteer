package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumPrioridade implements IEnum {
 Alta(" Alta "),
 Baixa(" Baixa "),
 Media(" Media ");
 private String descricao;

	private EnumPrioridade(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
