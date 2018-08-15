package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumStatusConcluiu implements IEnum {
	EM_BRANCO(" "),
	CONCLUIU("Concluiu"),
	EM_ANDAMENTO("Em andamento");

	private String descricao;
	
	private EnumStatusConcluiu(String descricao) {
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
