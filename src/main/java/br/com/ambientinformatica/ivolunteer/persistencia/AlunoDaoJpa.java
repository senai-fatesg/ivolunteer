package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Aluno;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("alunoDao")
public class AlunoDaoJpa extends PersistenciaJpa<Aluno> implements AlunoDao {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> listarPorNome(String nome) {

		Query query = em
				.createQuery("select f from Aluno f where upper(f.nomePessoa) like :nome");
		query.setParameter("nome", "%" + nome.toUpperCase() + "%");

		return (List<Aluno>) query.getResultList();
	}

	@Override
	public Aluno carregarAluno(Aluno aluno) {

		try {
			Query query = em.createQuery("select f from Aluno f "
					+ " left join fetch f.frequencias freq "
					+ " left join fetch f.gradesHorario grade "
					+ " left join fetch f.atividadesDiaria atividade "
					+ " where f  = :aluno");
			query.setParameter("aluno", aluno);
			return (Aluno) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

}
