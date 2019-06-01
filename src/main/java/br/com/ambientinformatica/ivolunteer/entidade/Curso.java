package br.com.ambientinformatica.ivolunteer.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.ambientinformatica.util.AmbientValidator;

@Entity
public class Curso implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "curso_seq" , strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "curso_seq" , sequenceName = "curso_seq" , allocationSize = 1 , initialValue = 1)
	private Integer id;
	
	@Column(length = 200)
	@NotEmpty(message = "Informe o nome do curso", groups = AmbientValidator.class)
	private String nome;
	
	@NotEmpty(message = "Informe a carga horária do curso", groups = AmbientValidator.class)
	private String cargaHoraria;
	
	@Column(length = 500)
	@NotEmpty(message = "Informe o conteúdo programático do curso", groups = AmbientValidator.class)
	private String conteudoProgramatico;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EnumStatus status;

	@ManyToOne(fetch=FetchType.LAZY , cascade = CascadeType.ALL)
	private Parceiro parceiro;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EnumTipoCurso duracao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date dataCadastro;
	
	@PrePersist
	private void cargaInicial() {
		this.status = EnumStatus.ATIVO;
		this.dataCadastro = new Date();
	}
	
	public Parceiro getParceiro() {
		return parceiro;
	}

	public void setParceiro(Parceiro parceiro) {
		this.parceiro = parceiro;
	}

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

	public EnumStatus getStatus() {
		return status;
	}

	public void inativar() {
		this.status = EnumStatus.INATIVO;
	}
	
	public void ativar() {
		this.status = EnumStatus.ATIVO;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
}
