package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

/**
 * 
 * @author luiz/Aureliano
 * 
 */

/*
 * Atualizado em: 15/05/205 19:22
 */
public enum EnumEscolaridade implements IEnum{

	
  FUNDAMENTAL("Fundamental"),
  MEDIO("Medio"),
  SUPERIOR("Superior");
  
	private String descricao;

	private EnumEscolaridade(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	

}
