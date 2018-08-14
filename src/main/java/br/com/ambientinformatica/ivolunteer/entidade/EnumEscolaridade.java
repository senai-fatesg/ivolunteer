package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumEscolaridade implements IEnum{

  FUNDAMENTAL("Fundamental"),
  FUNDAMENTAL_INCOMPLETO("Fundamental incompleto"),
  MEDIO("Medio"),
  MEDIO_INCOMPLETO("Medio incompleto"),
  SUPERIOR("Superior"),
  SUPERIOR_INCOMPLETO("Superior incompleto");
  
	private String descricao;

	private EnumEscolaridade(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
