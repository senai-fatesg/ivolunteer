package br.com.ambientinformatica.ivolunteer.persistencia;



import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Avaliacao;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("avaliacaoDao")
public class AvalicaoDaoJpa extends PersistenciaJpa<Avaliacao> implements
		AvaliacaoDao {
	private static final long serialVersionUID = 1L;
	
	public List<Avaliacao> listarTitulo(Avaliacao avaliacao) {
		try {
			Query  q =  em.createQuery("select a from Avaliacao a where a.titulo like :titulo");
			q.setParameter("titulo", "%"+avaliacao.getTitulo()+"%");
			return q.getResultList();
		} catch (EntityNotFoundException e) {
			return null;
		} 	
	}
}
