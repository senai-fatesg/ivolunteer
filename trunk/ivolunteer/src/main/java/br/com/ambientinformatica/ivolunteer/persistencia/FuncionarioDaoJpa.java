package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("funcionarioDao")
public class FuncionarioDaoJpa extends PersistenciaJpa<Funcionario> implements FuncionarioDao {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionario> listarPorNomeAtivo(String nome) {

		Query query = em.createQuery(
				"SELECT f FROM Funcionario f WHERE UPPER(f.nomePessoa) like :nome " + "AND f.isAtivo = :true");
		query.setParameter("nome", "%" + nome.toUpperCase() + "%");
		query.setParameter("true", true);
		return (List<Funcionario>) query.getResultList();
	}

	@Override
	public Funcionario carregarFuncionario(Funcionario funcionario) {

		try {
			Query query = em.createQuery("select f from Funcionario f " + " left join fetch f.frequencias freq "
					+ " left join fetch f.gradesHorario grade " + " left join fetch f.atividadesDiaria atividade "
					+ " where f  = :funcionario");
			query.setParameter("funcionario", funcionario);
			return (Funcionario) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	public Funcionario carregarFuncionarioComEnderecoTelefone(Funcionario funcionario) {

		try {
			Query query = em.createQuery("select f from Funcionario f " + " left join fetch f.listaEndereco listEnd "
					+ " where f  = :funcionario");
			query.setParameter("funcionario", funcionario);
			funcionario = (Funcionario) query.getSingleResult();
			
			Query carregaTelefones = em.createQuery("select f from Funcionario f left join fetch f.listaTelefone listaTelefone "
					+ " where f = :funcionario");
			carregaTelefones.setParameter("funcionario", funcionario);
			funcionario =  (Funcionario) carregaTelefones.getSingleResult();
			
			Query telefonesEmpresa = em.createQuery("select f from Funcionario f left join fetch f.telefonesEmpresa telefonesEmpresa "
					+ " where f = :funcionario");
			telefonesEmpresa.setParameter("funcionario", funcionario);
			funcionario = (Funcionario) telefonesEmpresa.getSingleResult();
			
			return (Funcionario) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override
	public List<Funcionario> listarFuncionariosAtivos() {
		try {
			Query query = em.createQuery("SELECT f FROM Funcionario f WHERE f.isAtivo = :true");
			query.setParameter("true", true);
			return (List<Funcionario>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
