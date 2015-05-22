package br.com.ambientinformatica.ivolunteer.entidade;

public enum EnumFiliacao {

	PAI("Pai"),
	MAE("Mae"), 
	OUTRO("Outro");
	private String descricao;

	private EnumFiliacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
