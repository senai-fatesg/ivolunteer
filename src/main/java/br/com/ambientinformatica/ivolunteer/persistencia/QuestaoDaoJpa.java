package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Avaliacao;
import br.com.ambientinformatica.ivolunteer.entidade.Questao;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("questaoDao")
public class QuestaoDaoJpa extends PersistenciaJpa<Questao> implements
      QuestaoDao {
	private static final long serialVersionUID = 1L;

	@Override
	public List<Questao> llistaQuestoesPorAvaliacao(Avaliacao avaliacao) {
		Query consulta = em.createQuery("select q from Questao q where q.avaliacao = :avaliacao");
		consulta.setParameter("avaliacao", avaliacao);
		return consulta.getResultList();
	}

}
