package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumLocalInternet implements IEnum {
	
	EM_BRANCO(" "),
	EM_CASA("Em casa"),
	NO_TRABALHO("No Trabalho"),
	LAN_HOUSE("Lan House"),
	CELULAR("Celular"),
	OUTRO("Outro");
	
	private String descricao;
	
	private EnumLocalInternet(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
