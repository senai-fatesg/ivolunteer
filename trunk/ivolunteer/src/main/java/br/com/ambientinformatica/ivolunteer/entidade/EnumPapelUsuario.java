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

public enum EnumPapelUsuario implements IEnum {

	ADMIN("Admin"),
  USUARIO("Usuario");
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
