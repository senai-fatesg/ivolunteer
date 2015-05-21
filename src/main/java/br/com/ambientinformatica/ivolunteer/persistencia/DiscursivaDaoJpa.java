package br.com.ambientinformatica.ivolunteer.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Discursiva;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("discursivaDao")
public class DiscursivaDaoJpa extends PersistenciaJpa<Discursiva> implements
      DiscursivaDao {

	private static final long serialVersionUID = 1L;

}
