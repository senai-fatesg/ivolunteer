package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoPessoa;
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.entidade.SelecaoCandidato;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("pessoaDao")
public class PessoaDaoJpa extends PersistenciaJpa<Pessoa> implements PessoaDao {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
   @Override
	public List<Pessoa> pesquisaSelecaoCandidato(SelecaoCandidato selecaoCandidato) {
			String sql = "select p from Pessoa p where p.recebeBeneficio = :recebeBeneficio";
			
			if(selecaoCandidato.getNomePessoa() != null && !selecaoCandidato.getNomePessoa().isEmpty()){
				sql += " and upper(p.nomePessoa) like :nomePessoa";
			}
			if(selecaoCandidato.getValorInicial() != null && selecaoCandidato.getValorInicial().intValue() > 10.01 && selecaoCandidato.getValorFinal() != null && selecaoCandidato.getValorFinal().intValue() > 10.01){
				sql += " or (p.totalRenda >= :valorInicial and p.totalRenda <= :valorFinal)";
			}

			Query query = em.createQuery(sql);
			
			query.setParameter("recebeBeneficio", selecaoCandidato.isBeneficios());
			
			if(selecaoCandidato.getNomePessoa() != null && !selecaoCandidato.getNomePessoa().isEmpty()){
				query.setParameter("nomePessoa", "%" + selecaoCandidato.getNomePessoa().toUpperCase() + "%");	
			}
			if(selecaoCandidato.getValorInicial() != null && selecaoCandidato.getValorInicial().intValue() > 10.01 && selecaoCandidato.getValorFinal() != null && selecaoCandidato.getValorFinal().intValue() > 10.01){
				query.setParameter("valorInicial", selecaoCandidato.getValorInicial());
				query.setParameter("valorFinal", selecaoCandidato.getValorFinal());
			}
			
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pessoa> listarPorNome(String nome) {

		Query query = em
				.createQuery("select p from Pessoa p where upper(p.nomePessoa) like :nome ");
		query.setParameter("nome", "%" + nome.toUpperCase() + "%");
		
		return  (List<Pessoa>) query.getResultList();
	}

	//busca somente a pessoa 'CANDIDATO'
	@Override
   public List<Pessoa> listaCandidatoPorNome(String nome) {
	   
		Query query = em.createQuery("SELECT p FROM Pessoa p WHERE UPPER(p.nomePessoa) LIKE :nome "
				+ " AND p.enumTipoPessoa = :enumTipoPessoa");
		query.setParameter("nome", "%" + nome.toUpperCase() + "%");
		query.setParameter("enumTipoPessoa", EnumTipoPessoa.CANDIDATO);
		
		return (List<Pessoa>) query.getResultList();
   }
	
		//busca todos os 'CANDIDATO'
		@Override
	   public List<Pessoa> listaCandidato() {
		   
			Query query = em.createQuery("SELECT p FROM Pessoa p WHERE p.enumTipoPessoa = :enumTipoPessoa");
			query.setParameter("enumTipoPessoa", EnumTipoPessoa.CANDIDATO);
			
			return (List<Pessoa>) query.getResultList();
	   }
	
	
	@Override
	public Pessoa consultarPessoaCompleta(Pessoa pessoa) {
		Query q = em.createQuery("select p from Pessoa p left join fetch p.listaResponsavel res where p = :pessoa");
		q.setParameter("pessoa", pessoa);
		return (Pessoa) q.getSingleResult();
	}
	
}
