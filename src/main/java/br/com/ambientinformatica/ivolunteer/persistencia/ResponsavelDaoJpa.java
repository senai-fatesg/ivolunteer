package br.com.ambientinformatica.ivolunteer.persistencia;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Responsavel;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("responsavelDao")
public class ResponsavelDaoJpa extends PersistenciaJpa<Responsavel> implements ResponsavelDao {
	
	private static final long serialVersionUID = 1L;

	@Override
	public Responsavel consultaResponsavelCompleto(Responsavel responsavel) {
		Query query = em.createQuery("SELECT r FROM Responsavel r LEFT JOIN FETCH r.listaEndereco listaEndereco"
				+ " WHERE r.id = :idResponsavel");
		query.setParameter("idResponsavel", responsavel.getId());
		return (Responsavel) query.getSingleResult();
	}
}