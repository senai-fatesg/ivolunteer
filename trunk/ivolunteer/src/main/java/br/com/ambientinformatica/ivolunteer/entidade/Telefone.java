package br.com.ambientinformatica.ivolunteer.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/*
 * Autor: Aureliano / Luiz Fernando
 * Atualizado em: 14/05/205 23:39
 * */

@Entity
public class Telefone {

	@Id
   @GeneratedValue(generator="telefone_seq", strategy=GenerationType.SEQUENCE)
   @SequenceGenerator(name="telefone_seq", sequenceName="telefone_seq", allocationSize=1, initialValue=1)
   private Integer id;
	
	private EnumTipoTelefone tipoTelefone;
	private String numeroTelefone;
	
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public EnumTipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}
	
	public void setTipoTelefone(EnumTipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}
	
	public String getNumeroTelefone() {
		return numeroTelefone;
	}
	
	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}
}
