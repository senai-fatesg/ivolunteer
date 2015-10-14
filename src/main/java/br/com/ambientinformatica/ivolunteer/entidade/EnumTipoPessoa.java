package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumTipoPessoa implements IEnum {

	CANDIDATO("Candidato"), 
	ALUNO("Aluno"), 
	RESPONSAVEL("Responsavel"),
	EDUCADOR("Educador"),
	COLABORADOR("Colaborador");

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
