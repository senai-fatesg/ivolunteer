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

@Entity
public class Avaliacao {
	
	@Id
	@GeneratedValue(generator="avaliacao_seq", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="avaliacao_seq", sequenceName="avaliacao_seq", allocationSize=1,initialValue=1)
	private Integer id;

	private String titulo;

	private String descricao;
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@OneToOne
	private Pessoa pessoa;

	@OneToMany
	private List<Questao> questao = new ArrayList<Questao>();

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

	public List<Questao> getQuestao() {
		return questao;
	}

	public void setQuestao(List<Questao> questao) {
		this.questao = questao;
	}
	
	//Métododo que ira adicionar questões
	public void addQuestao(Questao questao){
		if(!this.questao.contains(questao)){
			this.questao.add(questao);
		}
	}
}
