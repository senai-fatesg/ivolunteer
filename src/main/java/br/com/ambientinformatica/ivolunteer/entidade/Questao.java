package br.com.ambientinformatica.ivolunteer.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Questao {

	@Id
	@GeneratedValue(generator = "questao_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "questao_seq", sequenceName = "questao_seq", allocationSize = 1, initialValue = 1)
	private Integer id;

	private Integer ordem;

	private String pergunta;

	private EnumQuestao tipoQuestao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ordem;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Questao other = (Questao) obj;
		if (ordem != other.ordem)
			return false;
		return true;
	}

	public EnumQuestao getTipoQuestao() {
		return tipoQuestao;
	}

	public void setTipoQuestao(EnumQuestao tipoQuestao) {
		this.tipoQuestao = tipoQuestao;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

}
