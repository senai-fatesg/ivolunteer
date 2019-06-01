package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumSituacao implements IEnum {
	AGUARDANDO_INICIO("Aguardando início"),
	EM_ANDAMENTO("Em andamento"),
	CONCLUIDO("Concluido");

	private String descricao;

	private EnumSituacao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String getDescricao() {
		return this.descricao;
	}

}