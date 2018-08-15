package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumStatusDesistiu implements IEnum {
	EM_BRANCO(" "),
	TRANCADO("Trancado"),
	ABANDONO("Abandono");
	
	private String descricao;

	private EnumStatusDesistiu(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
