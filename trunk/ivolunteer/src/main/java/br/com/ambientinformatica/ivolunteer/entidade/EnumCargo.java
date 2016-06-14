package br.com.ambientinformatica.ivolunteer.entidade;
import br.com.ambientinformatica.util.IEnum;

public enum EnumCargo implements IEnum{
	EDUCADOR("Educador"),
	SECRETARIA("Secretária"),
	COORDENADOR("Coordenador"),
	DIRETOR("Diretor");

	@Override
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	private String descricao;

	private EnumCargo(String descricao) {
		this.descricao = descricao;
	}

}
