package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import br.com.ambientinformatica.ivolunteer.entidade.Curso;
import br.com.ambientinformatica.ivolunteer.entidade.Turma;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface TurmaDao extends Persistencia<Turma> {

	List<Turma> listarPorNome(String query);

	Turma carregarTurma(Turma turma);

	List<Turma> listarTodos();

	Turma getTurma(Integer idTurma);

	List<Turma> listarPorStatus(String status);

	List<Turma> consultaPorNomeStatus(Turma turmaConsulta, String status);

	Turma consultarPorId(Turma turmaConsulta);

	Turma consultarPorIdStatus(Turma turmaConsulta, String statusFiltro);

	List<Turma> buscaTurmasAtivas(Curso curso);
}
