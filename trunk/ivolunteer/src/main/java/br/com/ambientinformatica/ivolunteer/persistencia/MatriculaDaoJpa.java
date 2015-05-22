package br.com.ambientinformatica.ivolunteer.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Matricula;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("matriculaDao")
public class MatriculaDaoJpa extends PersistenciaJpa<Matricula> implements
		MatriculaDao {

	private static final long serialVersionUID = 1L;

}
