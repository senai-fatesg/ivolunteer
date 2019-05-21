package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Parceiro;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("parceiroDao")
public class ParceiroDaoJpa extends PersistenciaJpa<Parceiro> implements ParceiroDao {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Parceiro> listarPorNomeStatus(String nomeFiltro, String statusFiltro) throws PersistenciaException {
		try {
			String sql = "SELECT p FROM Parceiro p WHERE 1=1 ";
			if (nomeFiltro != null && !nomeFiltro.isEmpty()) {
				sql += "AND UPPER(p.nome) LIKE (:nome)";
			}
			if (statusFiltro != null && !statusFiltro.isEmpty()) {
				sql += "AND p.status = :status";
			}
			TypedQuery<Parceiro> query = em.createQuery(sql, Parceiro.class);
			if (nomeFiltro != null && !nomeFiltro.isEmpty()) {
				query.setParameter("nome", "%" + nomeFiltro.toUpperCase() + "%");
			}
			if (statusFiltro != null && !statusFiltro.isEmpty()) {
				query.setParameter("status", statusFiltro);
			}
			return query.getResultList();
		} catch (PersistenciaException e) {
			throw new PersistenciaException("Erro ao listar parceiros");
		}
	}

}
