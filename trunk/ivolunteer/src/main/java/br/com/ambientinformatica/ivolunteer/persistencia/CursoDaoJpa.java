package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Avaliacao;
import br.com.ambientinformatica.ivolunteer.entidade.Curso;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("cursoDao")
public class CursoDaoJpa extends PersistenciaJpa<Curso> implements CursoDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Curso> buscaCursoPorNome(String nome) {
		Query query = em.createQuery("SELECT c FROM Curso c WHERE UPPER(c.nome) LIKE :nome");
		query.setParameter("nome", "%" + nome.toUpperCase() + "%");
		return (List<Curso>)query.getResultList();
	}

	@Override
	public Curso buscaCursoPorId(Curso curso) {
		try {
			Curso resultado = em.find(Curso.class, curso.getId());
			return resultado;
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Curso buscaCursoComListaDeTurmasAtivas(Curso curso) {
		try {
			Curso resultado = em.find(Curso.class, curso.getId());
			Query buscaTurmasAtivas = em.createQuery("SELECT c FROM Curso c LEFT JOIN FETCH c.listaTurma listaTurma "
					+ " WHERE c.id = :id");
			buscaTurmasAtivas.setParameter("id", resultado.getId());
			//buscaTurmasAtivas.setParameter("status", false);
			resultado = (Curso) buscaTurmasAtivas.getSingleResult();
			
			return resultado;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Curso> listarCursosAtivos() {
		try {
			Query query = em.createQuery("SELECT c FROM Curso c WHERE c.isAtivo = :status");
			query.setParameter("status", true);
			return (List<Curso>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Curso> buscaCursoPorStatus(String statusFiltro) {
		try {
			boolean st;
			if(statusFiltro.contains("t")) {
				st = true;
			} else {
				st = false;
			}
			Query query = em.createQuery("SELECT c FROM Curso c WHERE c.isAtivo = :status");
			query.setParameter("status", st);
			List<Curso> cursos= query.getResultList();
			return cursos;
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Curso> buscaCursoPorStatusNome(String statusFiltro, String nomeFiltro) {
		try {
			boolean st;
			if(statusFiltro.contains("t")) {
				st = true;
			} else {
				st = false;
			}
			Query query = em.createQuery("SELECT c FROM Curso c WHERE UPPER(c.nome) LIKE :nome "
					+ " AND c.isAtivo = :status");
			query.setParameter("nome", "%" + nomeFiltro.toUpperCase() + "%");
			query.setParameter("status", st);
			return query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
