package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumTurno implements IEnum {

	Matutino("Matutino"), Vespertino("Verpertino"), Noturno("Noturno");

	private String descricao;

	private EnumTurno(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
