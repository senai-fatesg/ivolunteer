package br.com.ambientinformatica.ivolunteer.entidade;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;


@Entity
public class Endereco implements Serializable{

	@Id
	@GeneratedValue(generator = "endereco_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "endereco_seq", sequenceName = "endereco_seq", allocationSize = 1, initialValue = 1)
	private Integer id;

	private String bairro;

	private String ruaOuAvenida;

	private String cep;

	private long numero;

	private String quadra;

	private String lote;

	private String complemento;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Cidade cidade = new Cidade();
	
	private Boolean isAtivo;
	
	public Boolean getIsAtivo() {
		return isAtivo;
	}

	public void setIsAtivo(Boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRuaOuAvenida() {
		return ruaOuAvenida;
	}

	public void setRuaOuAvenida(String ruaOuAvenida) {
		this.ruaOuAvenida = ruaOuAvenida;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public String getQuadra() {
		return quadra;
	}

	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
}
