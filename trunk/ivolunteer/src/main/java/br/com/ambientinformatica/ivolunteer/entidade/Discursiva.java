package br.com.ambientinformatica.ivolunteer.entidade;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Discursiva extends Questao {

	public Discursiva() {
		super();
		setTipoQuestao(EnumQuestao.D);
	}

	private Integer numeroLinhas;

	public Integer getNumeroLinhas() {
		return numeroLinhas;
	}

	public void setNumeroLinhas(Integer numeroLinhas) {
		this.numeroLinhas = numeroLinhas;
	}

}
