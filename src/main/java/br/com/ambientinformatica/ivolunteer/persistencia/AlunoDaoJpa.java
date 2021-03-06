package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Aluno;
import br.com.ambientinformatica.ivolunteer.entidade.Candidato;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;
import br.com.ambientinformatica.util.UtilLog;

@Repository("alunoDao")
public class AlunoDaoJpa extends PersistenciaJpa<Aluno> implements AlunoDao {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> listarPorCertidao(String nome) {

		Query query = em.createQuery("select f from Aluno f where upper(f.ceridaoNascimento) like :nome");
		query.setParameter("nome", "%" + nome.toUpperCase() + "%");

		return (List<Aluno>) query.getResultList();
	}

	@Override
	public Aluno carregarAluno(Aluno aluno) {

		try {
			Query query = em.createQuery("select f from Aluno f " + " left join fetch f.frequencias freq "
					+ " left join fetch f.gradesHorario grade " + " left join fetch f.atividadesDiaria atividade "
					+ " where f  = :aluno");
			query.setParameter("aluno", aluno);
			return (Aluno) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override
	public List<Aluno> listarPorNome(String nome) {
		Query query = em.createQuery("select a from Aluno a  " + " left join fetch a.frequencias freq "

				+ " where upper(a.nomePessoa) like :nome");
		query.setParameter("nome", "%" + nome.toUpperCase() + "%");

		return (List<Aluno>) query.getResultList();
	}
	
	@Override
	public List<Aluno> listarTodos() {

		Query query = em.createQuery("select f from Aluno f");
		return query.getResultList();
	}
	
	@Override
	public Aluno getAluno(Integer idAluno) {

		try {
			Query query = em.createQuery("select f from Aluno f " + " left join fetch f.frequencias freq "
					+ " left join fetch f.gradesHorario grade " + " left join fetch f.atividadesDiaria atividade "
					+ " where f  = :aluno");
			query.setParameter("aluno", idAluno);
			return (Aluno) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override
	public Aluno consultarAlunoComResponsavel(Aluno aluno) {
		Aluno alunoConsulta = consultar(aluno.getId());
		
		try {
			Query carregaResponsaveis = em.createQuery(
					"SELECT a FROM Aluno a LEFT JOIN FETCH a.listaResponsavel resp" + " WHERE a.id = :id");
			carregaResponsaveis.setParameter("id", alunoConsulta.getId());
			alunoConsulta = (Aluno) carregaResponsaveis.getSingleResult();

		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Ocorreu um erro ao consultar candidato.");
			UtilLog.getLog().error(e);
		}
		
		return alunoConsulta;
	}

}
