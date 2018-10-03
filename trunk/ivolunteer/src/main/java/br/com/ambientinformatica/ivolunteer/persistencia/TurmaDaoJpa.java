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
		Query query = em.createQuery("select t from Turma t where upper(t.nome) like :nome");
		query.setParameter("nome", "%" + nome.toUpperCase() + "%");
		return (List<Turma>) query.getResultList();
	}

	@Override
	public Turma carregarTurma(Turma turma) {

		try {
			Query query = em.createQuery("select t from Turma t " + " left join fetch t.matricula mat "
					+ " left join fetch mat.aluno alu " + " where t  = :turma");
			query.setParameter("turma", turma);
			return (Turma) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override
	public List<Turma> listarTodos() {
		Query query = em.createQuery("select t from Turma t");
		return query.getResultList();

	}
	
	
	@Override
	public Turma getTurma(Integer idTurma) {

		try {
			Query query = em.createQuery("select t from Turma t " + " left join fetch t.matricula mat "
					+ " left join fetch mat.aluno alu " + " where t  = :turma");
			query.setParameter("turma", idTurma);
			return (Turma) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override
	public List<Turma> listarPorStatus(String status) {
		try {
			boolean st;
			if(status.contains("t")) {
				st = true;
			} else {
				st = false;
			}
			Query query = em.createQuery("SELECT t FROM Turma t WHERE isConcluido = :status");
			query.setParameter("status", st);
			List<Turma> turmas = query.getResultList();
			return turmas;
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Turma> consultaPorNomeStatus(Turma turmaConsulta,String status) {
		/*
		try {
			boolean st;
			if(status.contains("t")) {
				st = true;
			} else {
				st = false;
			}
			Query query = em.createQuery("SELECT t FROM Turma t WHERE UPPER(t.nome) LIKE :nome "
					+ " AND isAtivo = :status");
			query.setParameter("nome", "%" + turmaConsulta.getNome().toUpperCase() + "%");
			query.setParameter("status", st);
			List turmas = query.getResultList();
			return turmas;
		} catch (NoResultException e) {
			return null;
		}
		*/
		return null;
	}

	@Override
	public Turma consultarPorId(Turma turmaConsulta) {
		Turma resultado = em.find(Turma.class, turmaConsulta.getId());
		return resultado;
	}

	@Override
	public Turma consultarPorIdStatus(Turma turmaConsulta, String statusFiltro) {
		try {
			boolean st;
			if(statusFiltro.contains("t")) {
				st = true;
			} else {
				st = false;
			}
			Query query = em.createQuery("SELECT t FROM Turma t WHERE t.id = :id AND t.isConcluido = :status");
			query.setParameter("id", turmaConsulta.getId());
			query.setParameter("status", st);
			return (Turma) query.getSingleResult();
		} catch (NoResultException e) {
			Turma turma = new Turma();
			turma.setId(0);
			return turma;
		}
	}
}
