package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.ArrayList;
import java.util.List;

import br.com.ambientinformatica.util.IEnum;

public enum EnumQuestao implements IEnum {

	D("Discursiva"), O("Objetiva");

	private String descricao;

	private EnumQuestao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public List<EnumQuestao> getOpcoesList() {
		EnumQuestao[] vetorElementos = this.values();
		List retorno = new ArrayList<EnumQuestao>();
		for (int i = 0; i < vetorElementos.length; i++) {
			retorno.add(vetorElementos[i]);
		}
		return retorno;
	}

}