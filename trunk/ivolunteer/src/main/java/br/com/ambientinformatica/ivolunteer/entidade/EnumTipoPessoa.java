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
public enum EnumTipoPessoa implements IEnum {

	CANDIDATO("Candidato"), ALUNO("Aluno"), 
	RESPONSALVE("Responsalve"), 
	EDUCADOR("Educador");

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;

	private EnumTipoPessoa(String descricao) {
		this.descricao = descricao;
	}

}
