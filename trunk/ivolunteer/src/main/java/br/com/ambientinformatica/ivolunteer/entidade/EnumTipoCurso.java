package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumTipoCurso implements IEnum {
	DURACAO_DETERMINADA("DETERMINADA"),
	DURACAO_INTEDERMINADA("INDETERMINADA");
	
	private String descricao;
	
	private EnumTipoCurso(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String getDescricao() {
		return this.descricao;
	}

}
