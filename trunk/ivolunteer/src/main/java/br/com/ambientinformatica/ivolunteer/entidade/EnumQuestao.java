package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumQuestao implements IEnum{
	
	D("Discursiva"), O("Objetiva");
	
	private String descricao;

	
	private EnumQuestao(String descricao) {
		this.descricao = descricao;
	}


	public String getDescricao() {
		return descricao;
	}
	
	
	
	
}