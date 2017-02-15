package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.EnumSet;

import br.com.ambientinformatica.util.IEnum;

public enum EnumTurno implements IEnum {

	MATUTINO("1","Matutino"), 
	VESPERTINO("2","Verpertino"), 
	NOTURNO("3","Noturno");

	EnumTurno(String descricao, String codigo) {
		this.descricao = descricao;
		this.codigo = codigo;
	}
	
	private String descricao;
	private String codigo;

	public String getDescricao() {
		return descricao;
	}

	public String getCodigo() {
		return codigo;
	}
	
	public static EnumSet<EnumTurno> getListaTurnos() {
        return EnumSet.of(MATUTINO, VESPERTINO, NOTURNO);
    }
}
