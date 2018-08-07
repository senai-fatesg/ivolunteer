package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import javax.persistence.NoResultException;
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
		
		Query carregaTodosEnderecos = em.createQuery("SELECT c FROM Candidato c LEFT JOIN FETCH c.listaEndereco ends"
				+ " WHERE c.id = :id");
		carregaTodosEnderecos.setParameter("id", cand.getId());
		//carregaTodosEnderecos.setParameter("true", true);
		try {
			cand = (Candidato) carregaTodosEnderecos.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("SEM LISTA ENDEREÇOS!");
		}
		
		
		Query carregaResponsaveis = em.createQuery("SELECT c FROM Candidato c LEFT JOIN FETCH c.listaResponsavel resp"
				+ " WHERE c.id = :id");
		carregaResponsaveis.setParameter("id", cand.getId());
		cand = (Candidato) carregaResponsaveis.getSingleResult();
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
		Query buscaCandidatoPorNome = em.createQuery("SELECT c FROM Candidato c WHERE UPPER(c.nomePessoa) LIKE :nomeCandidato "
				+ " AND c.isAtivo = :true");
		buscaCandidatoPorNome.setParameter("nomeCandidato", "%" + candidatoConsulta.toUpperCase() + "%");
		buscaCandidatoPorNome.setParameter("true", true);
		return (List<Candidato>) buscaCandidatoPorNome.getResultList();
	}

}
