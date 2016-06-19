package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("funcionarioDao")
public class FuncionarioDaoJpa extends PersistenciaJpa<Funcionario> implements FuncionarioDao{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionario> listarPorNome(String nome) {

		Query query = em
				.createQuery("select f from Funcionario f where upper(f.nomePessoa) like :nome");
		query.setParameter("nome", "%" + nome.toUpperCase() + "%");
		
		return  (List<Funcionario>) query.getResultList();
	}

	@Override
	public Funcionario carregarFuncionario(Funcionario funcionario) {
		
		try{
		Query query = em.createQuery("select f from Funcionario f "
				+ " left join fetch f.frequencias freq "
				+ " left join fetch f.gradesHorario grade "
				+ " left join fetch f.atividadesDiaria atividade "
				+ " where f  = :funcionario");
		query.setParameter("funcionario", funcionario);
		return (Funcionario) query.getSingleResult();
		}catch(NoResultException nre){
			return null;
		}
	}
}
