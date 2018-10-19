package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Curso;
import br.com.ambientinformatica.ivolunteer.entidade.Parceiro;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("parceiroDao")
public class ParceiroDaoJpa extends PersistenciaJpa<Parceiro> implements ParceiroDao {

	@Override
	public List<Parceiro> buscaParceiroPorNome(String nomeFiltro) {
		try {
			Query query = em.createQuery("SELECT p FROM Parceiro p WHERE UPPER(p.nome) LIKE :nome");
			query.setParameter("nome", "%" + nomeFiltro.toUpperCase() + "%");
			return (List<Parceiro>)query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Parceiro> buscaParceiroPorStatus(String statusFiltro) {
		try {
			boolean st;
			if(statusFiltro.contains("t")) {
				st = true;
			} else {
				st = false;
			}
			Query query = em.createQuery("SELECT p FROM Parceiro p WHERE p.isAtivo = :status");
			query.setParameter("status", st);
			List<Parceiro> parceiros= query.getResultList();
			return parceiros;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Parceiro> buscaParceiroPorStatusNome(String statusFiltro, String nomeFiltro) {
		try {
			boolean st;
			if(statusFiltro.contains("t")) {
				st = true;
			} else {
				st = false;
			}
			Query query = em.createQuery("SELECT p FROM Parceiro p WHERE UPPER(p.nome) LIKE :nome "
					+ " AND c.isAtivo = :status");
			query.setParameter("nome", "%" + nomeFiltro.toUpperCase() + "%");
			query.setParameter("status", st);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Parceiro buscaParceiroComEndereco(Parceiro parceiro) {
		try {
			Query query = em.createQuery("SELECT p FROM Parceiro p LEFT JOIN FETCH p.endereco endereco "
					+ " WHERE p.id = :parceiroId");
			query.setParameter("parceiroId", parceiro.getId());
			return (Parceiro) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
