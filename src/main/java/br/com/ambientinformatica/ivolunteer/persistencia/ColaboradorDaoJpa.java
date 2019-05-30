package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Colaborador;
import br.com.ambientinformatica.ivolunteer.entidade.EnumCargo;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoFuncionario;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("colaboradorDao")
public class ColaboradorDaoJpa extends PersistenciaJpa<Colaborador> implements ColaboradorDao {
	private static final long serialVersionUID = 1L;

	@Override
	public Colaborador carregarFuncionario(Colaborador funcionario) {

		try {
			Query query = em.createQuery("select f from Funcionario f " + " left join fetch f.frequencias freq "
					+ " left join fetch f.gradesHorario grade " + " left join fetch f.atividadesDiaria atividade "
					+ " where f  = :funcionario");
			query.setParameter("funcionario", funcionario);
			return (Colaborador) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	public Colaborador carregarFuncionarioComEnderecoTelefone(Colaborador funcionario) {

		try {
			Query query = em.createQuery("select f from Funcionario f " + " left join fetch f.listaEndereco ListEnd "
					+ " where f  = :funcionario");
			query.setParameter("funcionario", funcionario);
			funcionario = (Colaborador) query.setMaxResults(1).getSingleResult();
			
			Query carregaTelefones = em.createQuery("select f from Funcionario f left join fetch f.listaTelefone listaTelefone "
					+ " where f = :funcionario");
			carregaTelefones.setParameter("funcionario", funcionario);
			funcionario =  (Colaborador) carregaTelefones.getSingleResult();
			
			Query telefonesEmpresa = em.createQuery("select f from Funcionario f left join fetch f.telefonesEmpresa telefonesEmpresa "
					+ " where f = :funcionario");
			telefonesEmpresa.setParameter("funcionario", funcionario);
			funcionario = (Colaborador) telefonesEmpresa.getSingleResult();
			
			return (Colaborador) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override
	public List<Colaborador> listarFuncionariosAtivos() {
		try {
			Query query = em.createQuery("SELECT f FROM Funcionario f WHERE f.isAtivo = :true");
			query.setParameter("true", true);
			return (List<Colaborador>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Colaborador> listarPorNome(String nome) {
		Query query = em.createQuery(
				"SELECT f FROM Funcionario f WHERE UPPER(f.nomePessoa) like :nome");
		query.setParameter("nome", "%" + nome.toUpperCase() + "%");
		return (List<Colaborador>) query.getResultList();
	}
	
	@Override
	public List<Colaborador> listarPorTipo(EnumTipoFuncionario tipo) {
		Query query = em.createQuery(
				"SELECT f FROM Funcionario f WHERE f.tipoFuncionario = :tipoFunc");
		query.setParameter("tipoFunc", tipo);
		return (List<Colaborador>) query.getResultList();
	}
	
	@Override
	public List<Colaborador> listarPorStatus(String status) {
		try {
			boolean st;
			if(status.contains("t")) {
				st = true;
			} else {
				st = false;
			}
			Query query = em.createQuery("SELECT f FROM Funcionario f WHERE isAtivo = :status");
			query.setParameter("status", st);
			return (List<Colaborador>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Colaborador> listarPorNomeETipo(String nome, EnumTipoFuncionario tipo) {
		Query query = em.createQuery(
				"SELECT f FROM Funcionario f WHERE UPPER(f.nomePessoa) like :nome AND f.tipoFuncionario = :tipo");
		query.setParameter("nome", "%" + nome.toUpperCase() + "%");
		query.setParameter("tipo", tipo);
		return (List<Colaborador>) query.getResultList();
	}

	@Override
	public List<Colaborador> listarPorNomeEStatus(String nome, String status) {
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
		return (List<Colaborador>) query.getResultList();
	}

	@Override
	public List<Colaborador> listarPorTipoEStatus(EnumTipoFuncionario tipo, String status) {
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
		return (List<Colaborador>) query.getResultList();
	}

	@Override
	public List<Colaborador> buscaEducadorPorNome(String nome) {
		Query query = em.createQuery("SELECT f FROM Funcionario f WHERE f.isAtivo = true AND "
				+ " f.cargo = :tipoFuncionario AND "
				+ " UPPER(f.nomePessoa) LIKE :nome");
		query.setParameter("tipoFuncionario", EnumCargo.EDUCADOR);
		query.setParameter("nome", "%" + nome.toUpperCase() + "%");
		return (List<Colaborador>) query.getResultList();
	}

	@Override
	public List<Colaborador> listarEducadoresAtivos(EnumCargo educador) {
		try {
			Query query = em.createQuery("SELECT f FROM Funcionario f LEFT JOIN FETCH f.listaEndereco listaEnd "
					+ " WHERE f.cargo = :cargo "
					+ " AND f.isAtivo = :status");
			query.setParameter("cargo", educador);
			query.setParameter("status", true);
			return (List<Colaborador>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Colaborador> listarEducadoresAtivos() throws PersistenciaException {
		try {
			TypedQuery<Colaborador> query = em.createQuery("SELECT c FROM Colaborador c where c.status = 'ATIVO' "
					+ "and c.cargo = 'EDUCADOR'", Colaborador.class);
			return query.getResultList();
		} catch (Exception e) {
			throw new PersistenciaException("Erro ao listar professores ativos!");
		}
	}


}
