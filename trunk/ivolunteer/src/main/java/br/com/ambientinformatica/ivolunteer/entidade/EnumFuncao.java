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
public enum EnumFuncao implements IEnum {
 
	DESCRICAO("Descricao");
private String descricao;

	EnumFuncao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}