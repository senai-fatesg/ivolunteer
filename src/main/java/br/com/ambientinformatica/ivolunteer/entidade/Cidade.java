package br.com.ambientinformatica.ivolunteer.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/*
 * Autor: Aureliano / Luiz Fernando
 * Atualizado em: 15/05/205 01:14
 * */

@Entity
public class Cidade {

	@Id
   @GeneratedValue(generator="cidade_seq", strategy=GenerationType.SEQUENCE)
   @SequenceGenerator(name="cidade_seq", sequenceName="cidade_seq", allocationSize=1, initialValue=1)
   private Integer id;

	private EnumEstado estado;
	private String nomeCidade;
	private EnumEstado enumEstado;
	
	//metodos gets e sets
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNomeCidade() {
		return nomeCidade;
	}
	
	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}
	
	public EnumEstado getEnumEstado() {
		return enumEstado;
	}
	
	public void setEnumEstado(EnumEstado enumEstado) {
		this.enumEstado = enumEstado;
	}
}
