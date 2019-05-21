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
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;

import br.com.ambientinformatica.util.AmbientValidator;

@Entity
public class Parceiro implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "parceiro_seq" , strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "parceiro_seq" , sequenceName = "parceiro_seq" , initialValue = 1 , allocationSize = 1)
	private Integer id;
	
	@NotBlank(message = "Informe o nome", groups = AmbientValidator.class)
	private String nome;
	
	@NotBlank(message = "Informe o cnpj", groups = AmbientValidator.class)
	private String cnpj;
	
	private String email;
	
	private String telefone;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EnumStatus status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date dataCadastro;
	
	@OneToOne(fetch=FetchType.LAZY, cascade= CascadeType.ALL, optional = true)
	private Endereco endereco = new Endereco();
	
	@PrePersist
	private void cargaInicial() {
		this.status = EnumStatus.ATIVO;
		this.dataCadastro = new Date();
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

	public EnumStatus getStatus() {
		return status;
	}
	
	public boolean isAtivo() {
		return status != null && status.equals(EnumStatus.ATIVO) ? true : false;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public void inativar() {
		this.status = EnumStatus.INATIVO;
	}
	
	public void ativar() {
		this.status = EnumStatus.ATIVO;
	}
	
	public void addEndereco(Endereco end) {
		this.setEndereco(end);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		Parceiro other = (Parceiro) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
}
