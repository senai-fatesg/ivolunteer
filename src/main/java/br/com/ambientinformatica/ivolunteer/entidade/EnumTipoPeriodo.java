package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumTipoPeriodo implements IEnum {
   
	DESCRICRAO("Descicrao"); 
	
   private String descricao;

   private EnumTipoPeriodo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
