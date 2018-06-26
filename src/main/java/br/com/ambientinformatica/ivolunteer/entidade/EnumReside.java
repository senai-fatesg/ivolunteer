package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumReside implements IEnum {
	
	COM_OS_PAIS("Com os pais"), 
	COM_OS_PARENTES("Com os parentes"),
	COM_AMIGOS("Com amigos"),
	COM_CONJUGUE("Com cônjugue(marido/esposa)"),
	SOZINHO("Sozinho(a)");
	
	private String descricao;
	
	private EnumReside(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
