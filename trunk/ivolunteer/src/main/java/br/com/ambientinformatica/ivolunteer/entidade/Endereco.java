package br.com.ambientinformatica.ivolunteer.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/*
 * Autor: Aureliano / Luiz Fernando
 * Atualizado em: 15/05/205 00:00
 * */

@Entity
public class Endereco {

	@Id
   @GeneratedValue(generator="endereco_seq", strategy=GenerationType.SEQUENCE)
   @SequenceGenerator(name="endereco_seq", sequenceName="endereco_seq", allocationSize=1, initialValue=1)
   private Integer id;
	
	private String bairro;
	private String ruaOuAvenida;
	private String cEP;
	private long numero;
	private String quadra;
	private String lote;
	private String complemento;
	
	@OneToOne
	private Cidade cidade;
	
	@ManyToOne
	private Pessoa pessoaRelacionada;

	private CadastroPessoa cadastroPessoa;

	
	//Construtor da classe
	public Endereco(){
		cidade = new Cidade();
	}
	
	//Metodos gets e sets
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getcEP() {
		return cEP;
	}

	public void setcEP(String cEP) {
		this.cEP = cEP;
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

	public CadastroPessoa getCadastroPessoa() {
		return cadastroPessoa;
	}

	public void setCadastroPessoa(CadastroPessoa cadastroPessoa) {
		this.cadastroPessoa = cadastroPessoa;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Pessoa getPessoaRelacionada() {
		return pessoaRelacionada;
	}

	public void setPessoaRelacionada(Pessoa pessoaRelacionada) {
		this.pessoaRelacionada = pessoaRelacionada;
	}
}
