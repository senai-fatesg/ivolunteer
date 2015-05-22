package br.com.ambientinformatica.ivolunteer.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Turma;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("turmaDao")
public class TurmaDaoJpa extends PersistenciaJpa<Turma> implements TurmaDao {
	private static final long serialVersionUID = 1L;
}
