package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Avaliacao {
	@Id
	@GeneratedValue(generator = "avaliacao_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "avaliacao_seq", sequenceName = "avaliacao_seq", allocationSize = 1, initialValue = 1)
	private Integer id;

	private String titulo;

	private String descricao;
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@OneToOne
	private Pessoa pessoa;

	@Transient
	private List<Questao> questoes = new ArrayList<Questao>();

	@OneToMany
	private List<Objetiva> objetivas = new ArrayList<Objetiva>();

	@OneToMany
	private List<Discursiva> discursivas = new ArrayList<Discursiva>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	// Métododo que ira adicionar questões
	public void addQuestao(Object questao) {
		Questao questaoProcessar = (Questao) questao;
		if (!this.questoes.contains(questaoProcessar)) {
			this.questoes.add(questaoProcessar);
			if (questao instanceof Discursiva) {
				this.discursivas.add((Discursiva) questao);
			} else {
				this.objetivas.add((Objetiva) questao);
			}
		}
	}

	// Método que ira remover questão
	public void remQuestao(Object questao) {
		Questao questaoProcessar = (Questao) questao;
		this.discursivas.remove(questaoProcessar);
		this.objetivas.remove(questaoProcessar);
		this.questoes.remove(questaoProcessar);	

	}

	public List<Objetiva> getObjetivas() {
		return objetivas;
	}

	public void setObjetivas(List<Objetiva> objetivas) {
		this.objetivas = objetivas;
	}

	public List<Discursiva> getDiscursivas() {
		return discursivas;
	}

	public void setDiscursivas(List<Discursiva> discursivas) {
		this.discursivas = discursivas;
	}

}
