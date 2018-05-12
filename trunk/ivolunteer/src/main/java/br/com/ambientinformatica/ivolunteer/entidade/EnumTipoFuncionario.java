package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumTipoFuncionario implements IEnum {
	CONTRATADO("Contratado"),
	VOLUNTARIO("Voluntario"),
	TERCEIRIZADO("Terceirizado");

	private String descricao;
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String getDescricao() {
		return this.descricao;
	}
	
	private EnumTipoFuncionario(String descricao) {
		this.descricao = descricao;
	}

}
