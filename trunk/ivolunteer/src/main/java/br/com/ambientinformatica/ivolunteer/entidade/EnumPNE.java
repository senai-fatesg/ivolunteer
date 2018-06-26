package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumPNE implements IEnum {
	
	SIM("Sim"), 
	NAO("Não");
	
	private String descricao;
	
	private EnumPNE(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
