package br.com.ambientinformatica.ivolunteer.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumParticipacaoEconomicaFamilia implements IEnum {
	
	VOCE_NAO_TRABALHA("Você não trabalha e seus gastos são custeados"),
	VOCE_TRABALHA("Você trabalha e é independente financeiramente"),
	VOCE_TRABALHA_NAO_INDEPENDENTE_FINANCEIRO("Você trabalha mas não é independente financeiramente"),
	VOCE_TRABALHA_RESPOSNSAVEL_FAMILIA("Você trabalha e é resposável pelo sustendo da família");
	
	private String descricao;
	
	private EnumParticipacaoEconomicaFamilia(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
