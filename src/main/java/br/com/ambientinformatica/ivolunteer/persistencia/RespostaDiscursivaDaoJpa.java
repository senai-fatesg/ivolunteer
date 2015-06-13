package br.com.ambientinformatica.ivolunteer.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.RespostaDiscursiva;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("respostaDiscursivaDao")
public class RespostaDiscursivaDaoJpa extends
		PersistenciaJpa<RespostaDiscursiva> implements RespostaDiscursivaDao {

	private static final long serialVersionUID = 1L;

}
