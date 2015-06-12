package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Avaliacao;
import br.com.ambientinformatica.ivolunteer.entidade.Questao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("avaliacaoDao")
public class AvalicaoDaoJpa extends PersistenciaJpa<Avaliacao> implements
		AvaliacaoDao {
	private static final long serialVersionUID = 1L;

	@Override
	public List<Avaliacao> listarTitulo(Avaliacao avaliacao) {
		try {
			Query q = em
					.createQuery("select a from Avaliacao a where a.titulo like :titulo");
			q.setParameter("titulo", "%" + avaliacao.getTitulo() + "%");
			return q.getResultList();
		} catch (EntityNotFoundException e) {
			return null;
		}
	}
	
	@Override
	public Avaliacao consultarAvalicaoCompleta(Avaliacao avaliacao){
		Query q = em.createQuery("select a from Avaliacao a "
				+ "left join fetch a.questoes ques " + "where a = :avaliacao");
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

}
