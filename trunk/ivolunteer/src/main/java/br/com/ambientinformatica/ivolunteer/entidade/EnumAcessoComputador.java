package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumAcessoComputador implements IEnum {
	
	LAZER_TRABALHOS_ESCOLARES("Sim, para lazer e trabalhos escolares"), 
	TRABALHOS_PROFISSIONAIS("Sim, para trabalhos profissionais"),
	PARA_OUTROS_FINS("Sim, para outros fins"),
	NAO("Não");
	
	private String descricao;
	
	private EnumAcessoComputador(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
