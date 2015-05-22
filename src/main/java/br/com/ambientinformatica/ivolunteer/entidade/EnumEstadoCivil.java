package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumEstadoCivil implements IEnum{
  SOLTEIRO("Solteiro"),
	 
  CASADO("casado"),
	 
	DIVORCIADO("Divorciado"),
	 
	 VIUVO("Viuvo");
  private String descricao;

	private EnumEstadoCivil(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
 
