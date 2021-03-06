package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Candidato;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoPessoa;
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.entidade.SelecaoCandidato;
import br.com.ambientinformatica.ivolunteer.entidade.Turma;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.exception.ValidacaoException;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;
import br.com.ambientinformatica.util.UtilLog;

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
		Candidato cand = consultar(candidato.getId());
		try {

			Query carregaTodosEnderecos = em.createQuery(
					"SELECT c FROM Candidato c LEFT JOIN FETCH c.listaEndereco ends" + " WHERE c.id = :id");
			carregaTodosEnderecos.setParameter("id", cand.getId());
			cand = (Candidato) carregaTodosEnderecos.getSingleResult();

			Query carregaResponsaveis = em.createQuery(
					"SELECT c FROM Candidato c LEFT JOIN FETCH c.listaResponsavel resp" + " WHERE c.id = :id");
			carregaResponsaveis.setParameter("id", cand.getId());
			cand = (Candidato) carregaResponsaveis.getSingleResult();

		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Ocorreu um erro ao consultar candidato.");
			UtilLog.getLog().error(e);
		}

		return cand;
	}

	@Override
	public List<Candidato> listaCandidato() {
		Query query = em.createQuery("SELECT p FROM Pessoa p WHERE p.enumTipoPessoa = :enumTipoPessoa");
		query.setParameter("enumTipoPessoa", EnumTipoPessoa.CANDIDATO);

		return (List<Candidato>) query.getResultList();
	}

	@Override
	public List<Candidato> listarCandidatosAtivos() {
		Query buscaCandidatosAtivos = em.createQuery("SELECT p FROM Candidato p WHERE p.isAtivo = :true");
		buscaCandidatosAtivos.setParameter("true", true);
		return (List<Candidato>) buscaCandidatosAtivos.getResultList();
	}

	@Override
	public List<Candidato> listarCandidatosAtivosPorNome(String candidatoConsulta) {
		Query buscaCandidatoPorNome = em.createQuery(
				"SELECT c FROM Candidato c WHERE UPPER(c.nomePessoa) LIKE :nomeCandidato " + " AND c.isAtivo = :true");
		buscaCandidatoPorNome.setParameter("nomeCandidato", "%" + candidatoConsulta.toUpperCase() + "%");
		buscaCandidatoPorNome.setParameter("true", true);
		return (List<Candidato>) buscaCandidatoPorNome.getResultList();
	}

	@Override
	public List<Candidato> listaCandidatosComResponsavel() {
		Query candidatos = em.createQuery(
				"SELECT c FROM Candidato c LEFT JOIN FETCH c.listaResponsavel listaResp");
		//candidatos.setParameter("true", true);
		
		return (List<Candidato>) candidatos.getResultList();
	}

	@Override
	public List<Candidato> listaPorStatus(String statusFiltro) {
		try {
			boolean st;
			if(statusFiltro.contains("t")) {
				st = true;
			} else {
				st = false;
			}
			Query query = em.createQuery("SELECT c FROM Candidato c LEFT JOIN FETCH c.listaResponsavel listaResp "
					+ " WHERE c.isAtivo = :status");
			query.setParameter("status", st);
			List<Candidato> candidatos = query.getResultList();
			return candidatos;
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Candidato> listarPorNomeStatus(String candidatoConsulta, String statusFiltro) {
		try {
			boolean st;
			if(statusFiltro.contains("t")) {
				st = true;
			} else {
				st = false;
			}
			Query query = em.createQuery("SELECT c FROM Candidato c LEFT JOIN FETCH c.listaResponsavel listaResp "
					+ " WHERE UPPER(c.nomePessoa) LIKE :nomeCandidato AND c.isAtivo = :status");
			query.setParameter("nomeCandidato", "%" + candidatoConsulta.toUpperCase() + "%");
			query.setParameter("status", st);
			List<Candidato> candidatos = query.getResultList();
			return candidatos;
		} catch (NoResultException e) {
			return null;
		}
	}

}
