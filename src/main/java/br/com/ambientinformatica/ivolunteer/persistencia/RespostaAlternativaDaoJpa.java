package br.com.ambientinformatica.ivolunteer.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.RespostaAlternativa;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("respostaAlternativaDao")
public class RespostaAlternativaDaoJpa extends
		PersistenciaJpa<RespostaAlternativa> implements RespostaAlternativaDao {

	private static final long serialVersionUID = 1L;

}
