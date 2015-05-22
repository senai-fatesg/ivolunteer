package br.com.ambientinformatica.ivolunteer.entidade;

/**
 * 
 * @author luiz/Aureliano
 * 
 */

/*
 * Atualizado em: 15/05/205 19:22
 */
import br.com.ambientinformatica.util.IEnum;

public enum EnumEstado implements IEnum {
 
	AC(" Acre "), 
	AL(" Alagoas "), 
	AP(" Amapa "), 
	AM(" Amazonas "), 
	BA(" Bahia "), 
	CE(" Ceara "), 
	DF(" Distrito Federal "), 
	ES(" Espirito Santo "), 
	GO(" Goias "), 
	MA(" Maranhao "), 
	MT(" Mato Grosso "), 
	MS(" Mato Grosso do Sul "), 
	MG(" Minas Gerais "), 
	PA(" Para "), 
	PB(" Paraiba "), 
	PE(" Pernambuco "), 
	PR(" Parana "), 
	PI(" Piaui "), 
	RJ(" Rio De Janeiro "), 
	RN(" Rio Grande do Norte "), 
	RS(" Rio Grande do Sul "), 
	RO(" Rondonia "), 
	RR(" Roraima "), 
	SC(" Santa Catarina "), 
	SE(" Sergipe "), 
	SP(" Sao Paulo "), 
	TO(" Tocantins ");

	private String descricao;

	EnumEstado(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	 
}
 
