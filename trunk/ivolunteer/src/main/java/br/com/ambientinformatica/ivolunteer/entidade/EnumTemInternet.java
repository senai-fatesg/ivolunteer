package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumTemInternet implements IEnum {
	
	NAO("Não"),
	SIM("Sim");
	
	private String descricao;
	
	private EnumTemInternet(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
