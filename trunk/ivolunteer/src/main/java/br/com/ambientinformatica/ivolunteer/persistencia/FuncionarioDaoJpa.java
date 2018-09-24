package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoFuncionario;
import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;
import br.com.ambientinformatica.ivolunteer.entidade.Turma;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("funcionarioDao")
public class FuncionarioDaoJpa extends PersistenciaJpa<Funcionario> implements FuncionarioDao {
	private static final long serialVersionUID = 1L;

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
			Query query = em.createQuery("select f from Funcionario f " + " left join fetch f.listaEndereco ListEnd "
					+ " where f  = :funcionario");
			query.setParameter("funcionario", funcionario);
			funcionario = (Funcionario) query.setMaxResults(1).getSingleResult();
			
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

	@Override
	public List<Funcionario> listarPorNome(String nome) {
		Query query = em.createQuery(
				"SELECT f FROM Funcionario f WHERE UPPER(f.nomePessoa) like :nome");
		query.setParameter("nome", "%" + nome.toUpperCase() + "%");
		return (List<Funcionario>) query.getResultList();
	}
	
	@Override
	public List<Funcionario> listarPorTipo(EnumTipoFuncionario tipo) {
		Query query = em.createQuery(
				"SELECT f FROM Funcionario f WHERE f.tipoFuncionario = :tipoFunc");
		query.setParameter("tipoFunc", tipo);
		return (List<Funcionario>) query.getResultList();
	}
	
	@Override
	public List<Funcionario> listarPorStatus(String status) {
		try {
			boolean st;
			if(status.contains("t")) {
				st = true;
			} else {
				st = false;
			}
			Query query = em.createQuery("SELECT f FROM Funcionario f WHERE isAtivo = :status");
			query.setParameter("status", st);
			return (List<Funcionario>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Funcionario> listarPorNomeETipo(String nome, EnumTipoFuncionario tipo) {
		Query query = em.createQuery(
				"SELECT f FROM Funcionario f WHERE UPPER(f.nomePessoa) like :nome AND f.tipoFuncionario = :tipo");
		query.setParameter("nome", "%" + nome.toUpperCase() + "%");
		query.setParameter("tipo", tipo);
		return (List<Funcionario>) query.getResultList();
	}

	@Override
	public List<Funcionario> listarPorNomeEStatus(String nome, String status) {
		boolean st;
		if(status.contains("t")) {
			st = true;
		} else {
			st = false;
		}
		Query query = em.createQuery(
				"SELECT f FROM Funcionario f WHERE UPPER(f.nomePessoa) like :nome AND f.isAtivo = :status");
		query.setParameter("nome", "%" + nome.toUpperCase() + "%");
		query.setParameter("status", st);
		return (List<Funcionario>) query.getResultList();
	}

	@Override
	public List<Funcionario> listarPorTipoEStatus(EnumTipoFuncionario tipo, String status) {
		boolean st;
		if(status.contains("t")) {
			st = true;
		} else {
			st = false;
		}
		Query query = em.createQuery(
				"SELECT f FROM Funcionario f WHERE f.tipoFuncionario = :tipo AND f.isAtivo = :status");
		query.setParameter("tipo", tipo);
		query.setParameter("status", st);
		return (List<Funcionario>) query.getResultList();
	}

}
