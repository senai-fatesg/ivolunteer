package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import br.com.ambientinformatica.ivolunteer.entidade.Avaliacao;
import br.com.ambientinformatica.ivolunteer.entidade.Turma;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("avaliacaoDao")
public class AvalicaoDaoJpa extends PersistenciaJpa<Avaliacao> implements
		AvaliacaoDao {
	private static final long serialVersionUID = 1L;

	@Override
	public List<Avaliacao> listarAvaliacoesPorTitulo(String titulo) {
		try {
			Query q = em.createQuery("select a from Avaliacao a where UPPER(a.titulo) like :titulo");
			q.setParameter("titulo", "%" + titulo.toUpperCase() + "%");
			return q.getResultList();
		} catch (EntityNotFoundException e) {
			return null;
		}
	}

	@Override
	public List<Avaliacao> listarTitulo(String titulo) {
		try {
			Query q = em.createQuery("select a from Avaliacao a where UPPER(a.titulo) like :titulo AND a.ativo = :true");
			q.setParameter("titulo", "%" + titulo.toUpperCase() + "%");
			q.setParameter("true", true);
			return q.getResultList();
		} catch (EntityNotFoundException e) {
			return null;
		}
	}

	@Override
	public Avaliacao consultarAvalicaoCompleta(Avaliacao avaliacao) {
		Query q = em.createQuery("select a from Avaliacao a "+ "left join fetch a.questoes ques " + "where a = :avaliacao order by ques.ordem");
		q.setParameter("avaliacao", avaliacao);
		return (Avaliacao) q.getSingleResult();
	}

	@Override
	public void removerAvaliacaoCompleta(Avaliacao avaliacao) {
		try {
			excluirPorId(this.consultarAvalicaoCompleta(avaliacao).getId());
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<Avaliacao> listarAvaliacoesAtivas() {
		try {
			Query query = em.createQuery("SELECT a FROM Avaliacao a WHERE a.ativo = :true");
			query.setParameter("true", true);
			return (List<Avaliacao>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Avaliacao> listarAvaliacoesPorStatus(String statusFiltro) {
		try {
			boolean st;
			if(statusFiltro.contains("t")) {
				st = true;
			} else {
				st = false;
			}
			Query query = em.createQuery("SELECT a FROM Avaliacao a WHERE a.ativo = :status");
			query.setParameter("status", st);
			List<Avaliacao> avaliacoes = query.getResultList();
			return avaliacoes;
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Avaliacao> listarAvaliacoesPorTituloStatus(String titulo, String statusFiltro) {
		try {
			boolean st;
			if(statusFiltro.contains("t")) {
				st = true;
			} else {
				st = false;
			}
			Query query = em.createQuery("SELECT a FROM Avaliacao a WHERE UPPER(a.titulo) LIKE :titulo "
					+ " AND a.ativo = :status");
			query.setParameter("titulo", "%" + titulo.toUpperCase() + "%");
			query.setParameter("status", st);
			List<Avaliacao> avaliacoes = query.getResultList();
			return avaliacoes;
		} catch (NoResultException e) {
			return null;
		}
	}

}
