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
		/*Query carregaCandidatoComEndereco = em.createQuery("SELECT p FROM Pessoa p "
				+ " left join fetch p.listaEndereco listaEnd WHERE p.id = :idCandidado "
				+ " AND listaEnd.isAtivo = :true"
				+ " AND p.enumTipoPessoa = :tipoPessoa");
		carregaCandidatoComEndereco.setParameter("idCandidato", candidato.getId());
		carregaCandidatoComEndereco.setParameter("true", true);
		carregaCandidatoComEndereco.setParameter("tipoPessoa", EnumTipoPessoa.CANDIDATO);
		Candidato cand = (Candidato) carregaCandidatoComEndereco.getSingleResult();
		
		Query carregaCandidatoResponsaveis = em.createQuery("SELECT p FROM Pessoa p"
				+ " left join fetch Responsavel");
		*/
		
		//cand.getListaEndereco();
		//cand.getListaResponsavel();
		
		Candidato cand = consultar(candidato.getId());
		
		Query carregaEnderecosAtivos = em.createQuery("SELECT c FROM Candidato c join fetch c.listaEndereco ends "
				+ " WHERE c.id = :id "
				+ " AND ends.isAtivo = :true ");
		carregaEnderecosAtivos.setParameter("id", cand.getId());
		carregaEnderecosAtivos.setParameter("true", true);
		cand = (Candidato) carregaEnderecosAtivos.getSingleResult();
		
		Query carregaResponsaveis = em.createQuery("SELECT c FROM Candidato c join fetch c.listaResponsavel resp"
				+ " WHERE c.id = :id");
		carregaResponsaveis.setParameter("id", cand.getId());
		cand = (Candidato) carregaResponsaveis.getSingleResult();
		
		/*
		Query query = em.createQuery("SELECT p FROM Pessoa p left join fetch p.listaResponsavel listaResp "
				+ " left join fetch p.listaEndereco listaEnd "
				+ " WHERE p.id = :id and p.enumTipoPessoa = :enumTipoPessoa "
				+ " AND listaEnd.isAtivo = :true ");
		query.setParameter("id", candidato.getId());
		query.setParameter("enumTipoPessoa", EnumTipoPessoa.CANDIDATO);
		query.setParameter("true", true);
		*/
		return cand;
	}

	@Override
	public List<Candidato> listaCandidato() {
		Query query = em.createQuery("SELECT p FROM Pessoa p WHERE p.enumTipoPessoa = :enumTipoPessoa");
		query.setParameter("enumTipoPessoa", EnumTipoPessoa.CANDIDATO);
		
		return (List<Candidato>) query.getResultList();
	}

}
