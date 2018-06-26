package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumResidencia implements IEnum {
	
	PROPRIA("Própria"), 
	ALUGADA("Alugada"),
	OUTROS("Outros");
	
	private String descricao;
	
	private EnumResidencia(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
