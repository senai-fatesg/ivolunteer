package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import br.com.ambientinformatica.ivolunteer.entidade.Curso;
import br.com.ambientinformatica.ivolunteer.entidade.EnumStatus;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface CursoDao extends Persistencia<Curso> {

	public Curso buscaCursoComListaDeTurmasAtivas(Curso curso);

	public List<Curso> listarCursosAtivos() throws PersistenciaException;

	public List<Curso> listarPorNomeStatus(String nomeFiltro, EnumStatus statusFiltro) throws PersistenciaException ;

}
