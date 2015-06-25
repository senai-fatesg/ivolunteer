package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Turma;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("turmaDao")
public class TurmaDaoJpa extends PersistenciaJpa<Turma> implements TurmaDao {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Turma> listarPorNome(String nome) {
		Query query = em
				.createQuery("select t from Turma t where upper(t.nome) like :nome");
		query.setParameter("nome", "%" + nome.toUpperCase() + "%");
		return (List<Turma>) query.getResultList();
	}

	@Override
	public Turma carregarTurma(Turma turma) {

		try {
			Query query = em.createQuery("select t from Turma t "
					+ " left join fetch t.matricula mat "
					+ " left join fetch mat.aluno alu " 
					+ " where t  = :turma");
			query.setParameter("turma", turma);
			return (Turma) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

}
