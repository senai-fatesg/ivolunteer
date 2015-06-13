package br.com.ambientinformatica.ivolunteer.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.RespostaObjetiva;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("respostaObjetivaDao")
public class RespostaObjetivaDaoJpa extends PersistenciaJpa<RespostaObjetiva>
		implements RespostaObjetivaDao {

	private static final long serialVersionUID = 1L;

}
