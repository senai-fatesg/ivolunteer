package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Objetiva extends Questao {

	public Objetiva() {
		super();
		setTipoQuestao(EnumQuestao.O);
	}

	@OneToMany
	private List<Alternativa> alternativas = new ArrayList<Alternativa>();

	public List<Alternativa> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(List<Alternativa> alternativas) {
		this.alternativas = alternativas;
	}

	// Método que ira adicionar as alternativas
	public void addAlternativa(Alternativa alternativa) {
		if (!this.alternativas.contains(alternativa)) {
			this.alternativas.add(alternativa);
		}
	}

	// Método que ira remover as alternativas
	public void remAlternativa(Alternativa alternativa) {
		this.alternativas.remove(alternativa);
	}
}
