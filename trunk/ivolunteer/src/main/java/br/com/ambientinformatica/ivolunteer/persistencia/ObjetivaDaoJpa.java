package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Avaliacao;
import br.com.ambientinformatica.ivolunteer.entidade.Objetiva;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("objetivaDao")
public class ObjetivaDaoJpa extends PersistenciaJpa<Objetiva> implements ObjetivaDao{
	
	private static final long serialVersionUID = 1L;

	@Override
	public List<Objetiva> obterObjetivas(Avaliacao avaliacao) {
		Query consulta = em.createQuery("select o from Objetiva o where o.avaliacao = :avaliacao");		
		consulta.setParameter("avaliacao", avaliacao);
		return consulta.getResultList();
	}
}
