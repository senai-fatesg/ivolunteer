package br.com.ambientinformatica.ivolunteer.persistencia;


import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Candidato;
import br.com.ambientinformatica.ivolunteer.entidade.Endereco;
import br.com.ambientinformatica.ivolunteer.entidade.Responsavel;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("enderecoDao")
public class EnderecoDaoJpa extends PersistenciaJpa<Endereco> implements EnderecoDao{
	private static final long serialVersionUID = 1L;

	@Override
	public void desativaEndereco(Endereco endereco) {
		Endereco end = em.find(Endereco.class, endereco.getId());
		end.setIsAtivo(false);
		em.merge(end);
	}

	@Override
	public List<Endereco> buscaTodosEnderecosPorCandidato(Candidato candidato) {
		Query query = em.createQuery("SELECT e FROM Endereco e WHERE pessoa_id = :idCandidato");
		query.setParameter("idCandidato", candidato.getId());
		return query.getResultList();
	}

	@Override
	public List<Endereco> buscaTodosEnderecosPorResponsavel(Responsavel responsavel) {
		Query query = em.createQuery("SELECT e FROM Endereco e WHERE pessoa_id = :idResponsavel");
		query.setParameter("idResponsavel", responsavel.getId());
		return query.getResultList();
	}

	
}

