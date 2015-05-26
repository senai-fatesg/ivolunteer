package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Objetiva {

	@Id
	@GeneratedValue(generator = "objetiva_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "objetiva_seq", sequenceName = "objetiva_seq", allocationSize = 1, initialValue = 1)
	private Integer id;
	
	private String descricao;

	@OneToMany
	private List<Alternativa> alternativas = new ArrayList<Alternativa>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Alternativa> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(List<Alternativa> alternativas) {
		this.alternativas = alternativas;
	}

	//Método que ira adicionar as alternativas
	public void addAlternativa(Alternativa alternativa){
		if(!this.alternativas.contains(alternativa)){
			this.alternativas.add(alternativa);
		}
	}
	//Método que ira remover as alternativas
	public void removerAlternativa(Alternativa alternativa){
		this.alternativas.remove(alternativa);
	}
}
