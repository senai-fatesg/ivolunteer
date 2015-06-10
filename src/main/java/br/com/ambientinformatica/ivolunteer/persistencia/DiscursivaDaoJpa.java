package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Avaliacao;
import br.com.ambientinformatica.ivolunteer.entidade.Discursiva;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("discursivaDao")
public class DiscursivaDaoJpa extends PersistenciaJpa<Discursiva> implements
      DiscursivaDao {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Discursiva> obterDiscursivas(Avaliacao avaliacao) {
		Query consulta = em.createQuery("select d from Discursiva d where d.avaliacao = :avaliacao");
		consulta.setParameter("avaliacao", avaliacao);
		return consulta.getResultList();
	}

}
