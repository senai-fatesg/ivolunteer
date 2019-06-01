package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Curso;
import br.com.ambientinformatica.ivolunteer.entidade.EnumStatus;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;
import br.com.ambientinformatica.util.UtilLog;

@Repository("cursoDao")
public class CursoDaoJpa extends PersistenciaJpa<Curso> implements CursoDao {

	private static final long serialVersionUID = 1L;

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
	public List<Curso> listarAtivos() throws PersistenciaException {
		try {
			TypedQuery<Curso> query = em.createQuery("SELECT c FROM Curso c WHERE c.status = 'ATIVO'", Curso.class);
			return (List<Curso>) query.getResultList();
		} catch (Exception e) {
			throw new PersistenciaException("Erro ao listar cursos ativos");
		}
	}

	@Override
	public List<Curso> listarPorNomeStatus(String nomeFiltro, EnumStatus statusFiltro) throws PersistenciaException {
		try {
			String sql = "SELECT c FROM Curso c WHERE 1=1 ";
			if (nomeFiltro != null && !nomeFiltro.isEmpty()) {
				sql += "AND UPPER(c.nome) LIKE :nome";
			}
			if (statusFiltro != null) {
				sql += "AND c.status IN :status";
			}
			
			TypedQuery<Curso> query = em.createQuery(sql, Curso.class);
			if (nomeFiltro != null && !nomeFiltro.isEmpty()) {
				query.setParameter("nome", "%" + nomeFiltro.toUpperCase() + "%");
			}
			if (statusFiltro != null) {
				query.setParameter("status", statusFiltro);
			}
			return query.getResultList();
		} catch (PersistenciaException e) {
			UtilLog.getLog().error(e);
			throw new PersistenceException("Erro ao listar cursos por nome e status");
		}
	}

}
