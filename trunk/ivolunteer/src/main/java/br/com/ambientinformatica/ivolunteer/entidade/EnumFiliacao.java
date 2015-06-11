package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumFiliacao implements IEnum{

	FILHO("Filho"),
	PAI("Pai"),
	MAE("Mae"), 
	OUTRO("Outro");
	private String descricao;

	private EnumFiliacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
