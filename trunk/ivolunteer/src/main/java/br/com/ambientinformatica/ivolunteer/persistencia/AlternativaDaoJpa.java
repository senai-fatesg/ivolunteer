package br.com.ambientinformatica.ivolunteer.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Alternativa;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("alternativaDao")
public class AlternativaDaoJpa extends PersistenciaJpa<Alternativa> implements
      AlternativaDao {

	private static final long serialVersionUID = 1L;

}
