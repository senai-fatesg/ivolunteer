package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumTipoEtnia implements IEnum {
	NEGRO("Negra"),
	INDIGENA("Indígena"),
	BRANCA("Branca"),
	PARDA("Parda"),
	MULATA("Mulata");
	
	private String descricao;

	private EnumTipoEtnia(String descricao){
		this.descricao = descricao;
	}
	
	@Override
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
