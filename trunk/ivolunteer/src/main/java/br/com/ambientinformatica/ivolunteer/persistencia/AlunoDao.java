package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import br.com.ambientinformatica.ivolunteer.entidade.Aluno;
import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface AlunoDao extends Persistencia<Aluno> {

	public List<Aluno> listarPorCertidao(String query);

	Aluno carregarAluno(Aluno aluno);

	public List<Aluno> listarPorNome(String query);
	
	public List<Aluno> listarTodos();

	Aluno getAluno(Integer idAluno);

	public Aluno consultarAlunoComResponsavel(Aluno aluno);
}
