package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumPapelUsuario implements IEnum {

	ADMIN("Admin"),
	USUARIO("Usuario"), 
	SECRETARIA("Secretaria"),
	EDUCADOR("Educador");
	
	private String descricao;

	private EnumPapelUsuario(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
