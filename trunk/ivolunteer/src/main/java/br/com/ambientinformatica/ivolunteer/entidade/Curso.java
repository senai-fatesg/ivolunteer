package br.com.ambientinformatica.ivolunteer.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;


@Entity
public class Curso implements Serializable {
	
	@Id
	@GeneratedValue(generator = "curso_seq" , strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "curso_seq" , sequenceName = "curso_seq" , allocationSize = 1 , initialValue = 1)
	private Integer id;
	
	private String nome;
	
	private String cargaHoraria;
	
	private String conteudoProgramatico;
	
	private Boolean isAtivo = true;
	
	@Enumerated(EnumType.STRING)
	private EnumTipoCurso duracao;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "curso")
	private List<Turma> listaTurma = new ArrayList<Turma>();
	
	
	public String getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public String getConteudoProgramatico() {
		return conteudoProgramatico;
	}

	public void setConteudoProgramatico(String conteudoProgramatico) {
		this.conteudoProgramatico = conteudoProgramatico;
	}

	public Boolean getIsAtivo() {
		return isAtivo;
	}

	public void setIsAtivo(Boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	public List<Turma> getListaTurma() {
		return listaTurma;
	}

	public void setListaTurma(List<Turma> listaTurma) {
		this.listaTurma = listaTurma;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public EnumTipoCurso getDuracao() {
		return duracao;
	}

	public void setDuracao(EnumTipoCurso duracao) {
		this.duracao = duracao;
	} 

	public void adicionarTurma(Turma turma) {
		this.getListaTurma().add(turma);
	}
	
}
