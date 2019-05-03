package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumStatus implements IEnum{
	ATIVO("Ativo", true),
	INATIVO("Inativo", false);
	
	private String descricao;
	private boolean status;
	
	private EnumStatus (String descricao, boolean status) {
		this.descricao = descricao;
		this.status = status;
	}

	@Override
	public String getDescricao() {
		return this.descricao;
	}

	public boolean isStatus() {
		return status;
	}

}
