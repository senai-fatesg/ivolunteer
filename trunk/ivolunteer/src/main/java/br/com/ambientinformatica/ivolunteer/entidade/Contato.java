package br.com.ambientinformatica.ivolunteer.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Contato {

	@Id
	@GeneratedValue(generator = "contato_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "contato_seq1", sequenceName = "contato_seq", allocationSize = 1, initialValue = 1)
	private Integer id;

	private String nome;

	private String telefone;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getId() {
		return id;
	}

}
