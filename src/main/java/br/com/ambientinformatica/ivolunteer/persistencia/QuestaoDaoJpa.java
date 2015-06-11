package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Avaliacao;
import br.com.ambientinformatica.ivolunteer.entidade.Questao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("questaoDao")
public class QuestaoDaoJpa extends PersistenciaJpa<Questao> implements
      QuestaoDao {
	private static final long serialVersionUID = 1L;

	@Override
	public List<Questao> llistaQuestoesPorAvaliacao(Avaliacao avaliacao) {
		Query consulta = em.createQuery("select q from Questao q where q.avaliacao.id = :avaliacao");
		consulta.setParameter("avaliacao", avaliacao.getId());
		return consulta.getResultList();
	}

	@Override
	public void excluirPorAvaliacao(Avaliacao avaliacao) {		
		
		for (Iterator iterator = this.llistaQuestoesPorAvaliacao(avaliacao).iterator(); iterator.hasNext();) {
			try {
				excluirPorId(((Questao) iterator.next()).getId());
			} catch (PersistenciaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} ;			
		}
		
	}

}
