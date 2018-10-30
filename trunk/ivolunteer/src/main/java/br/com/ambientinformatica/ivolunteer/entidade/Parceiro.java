package br.com.ambientinformatica.ivolunteer.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Parceiro implements Serializable{

	@Id
	@GeneratedValue(generator = "parceiro_seq" , strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "parceiro_seq" , sequenceName = "parceiro_seq" , initialValue = 1 , allocationSize = 1)
	private Integer id;
	
	private String nome;
	
	private String cnpj;
	
	private String email;
	
	private String telefone;
	
	private Boolean isAtivo = true;
	
	@OneToMany(fetch=FetchType.LAZY , cascade = CascadeType.ALL, mappedBy = "parceiro")
	private List<Curso> listaCursos;
	
	@OneToOne(fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	private Endereco endereco = new Endereco();

	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Boolean getIsAtivo() {
		return isAtivo;
	}

	public void setIsAtivo(Boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public void inativaParceiro() {
		this.setIsAtivo(false);
	}
	
	public void addEndereco(Endereco end) {
		this.setEndereco(end);
	}
	
	public void addCurso(Curso curso) {
		this.listaCursos.add(curso);
	}
	
	public void addCursos(List<Curso> cursos) {
		this.listaCursos.addAll(cursos);
	}
}
