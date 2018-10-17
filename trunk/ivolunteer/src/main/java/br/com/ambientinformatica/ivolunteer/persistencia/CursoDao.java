package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import br.com.ambientinformatica.ivolunteer.entidade.Curso;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface CursoDao extends Persistencia<Curso> {

	public List<Curso> buscaCursoPorNome(String nome);

	public Curso buscaCursoPorId(Curso curso);

	public Curso buscaCursoComListaDeTurmasAtivas(Curso curso);

	public List<Curso> listarCursosAtivos();

	public List<Curso> buscaCursoPorStatus(String statusFiltro);

	public List<Curso> buscaCursoPorStatusNome(String statusFiltro, String nomeFiltro);

}
