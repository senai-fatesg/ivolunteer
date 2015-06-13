package br.com.ambientinformatica.ivolunteer.entidade;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class RespostaDiscursiva {
	
	@Id
	@GeneratedValue(generator="seq_discursivaresposta", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="seq_discursivaresposta", sequenceName="seq_discursivaresposta", initialValue=1, allocationSize=1)
	private Integer id;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Discursiva discursiva;
	
	
	private String resposta;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Discursiva getDiscursiva() {
		return discursiva;
	}


	public void setDiscursiva(Discursiva discursiva) {
		this.discursiva = discursiva;
	}


	public String getResposta() {
		return resposta;
	}


	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	
	
	
	
}
