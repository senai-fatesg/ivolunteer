package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.EnumSet;

import br.com.ambientinformatica.util.IEnum;

public enum EnumTurno implements IEnum {

	MATUTINO(1, "Matutino"), VESPERTINO(2, "Verpertino"), NOTURNO(3, "Noturno");

	EnumTurno(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	private Integer codigo;
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public static EnumTurno getById(int codigo) {
		for (EnumTurno turno : EnumTurno.values()) {
			if (codigo == turno.getCodigo()) {
				return turno;
			}
		}
		return null;
	}

	public static EnumSet<EnumTurno> getListaTurnos() {
		return EnumSet.of(MATUTINO, VESPERTINO, NOTURNO);
	}
}
