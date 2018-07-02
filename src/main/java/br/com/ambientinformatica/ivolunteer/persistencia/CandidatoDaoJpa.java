package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Candidato;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoPessoa;
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.entidade.SelecaoCandidato;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.exception.ValidacaoException;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("candidatoDao")
public class CandidatoDaoJpa extends PersistenciaJpa<Candidato> implements CandidatoDao {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Candidato> listaCandidatoPorNome(String nome) {
		Query query = em.createQuery("SELECT p FROM Pessoa p WHERE UPPER(p.nomePessoa) LIKE :nome "
				+ " AND p.enumTipoPessoa = :enumTipoPessoa");
		query.setParameter("nome", "%" + nome.toUpperCase() + "%");
		query.setParameter("enumTipoPessoa", EnumTipoPessoa.CANDIDATO);
		
		return (List<Candidato>) query.getResultList();
	}

	@Override
	public List<Candidato> pesquisaSelecaoCandidato(SelecaoCandidato selecaoCandidato) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Candidato consultarCandidatoCompleto(Candidato candidato) {
		Query query = em.createQuery("SELECT p FROM Pessoa p left join fetch p.listaResponsavel"
				+ " r WHERE p.id = :id and p.enumTipoPessoa = :enumTipoPessoa");
		query.setParameter("id", candidato.getId());
		query.setParameter("enumTipoPessoa", EnumTipoPessoa.CANDIDATO);
		return (Candidato) query.getSingleResult();
	}

	@Override
	public List<Candidato> listaCandidato() {
		Query query = em.createQuery("SELECT p FROM Pessoa p WHERE p.enumTipoPessoa = :enumTipoPessoa");
		query.setParameter("enumTipoPessoa", EnumTipoPessoa.CANDIDATO);
		
		return (List<Candidato>) query.getResultList();
	}

}
