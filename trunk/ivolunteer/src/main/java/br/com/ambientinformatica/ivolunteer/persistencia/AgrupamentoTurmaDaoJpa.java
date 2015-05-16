package br.com.ambientinformatica.ivolunteer.persistencia;

import org.springframework.stereotype.Repository;
import br.com.ambientinformatica.ivolunteer.entidade.AgrupamentoTurma;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("agrupamentoTurma")
public class AgrupamentoTurmaDaoJpa extends PersistenciaJpa<AgrupamentoTurma>
		implements AgrupamentoTurmaDao {

	private static final long serialVersionUID = 1L;

}
