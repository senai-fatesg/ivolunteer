package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;
/*
 * Autor: Edjalma
 * Atualizado em: 15/05/205 01:50
 * Alterado por: Aureliano
 * */

public enum EnumSexo implements IEnum {

	M("Masculino"), 
	F("Feminino");

	private String descricao;

	private EnumSexo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
