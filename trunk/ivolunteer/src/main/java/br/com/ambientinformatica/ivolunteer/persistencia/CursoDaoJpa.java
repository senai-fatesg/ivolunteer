package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

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

}
