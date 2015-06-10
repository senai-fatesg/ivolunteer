package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.entidade.SelecaoCandidato;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("pessoaDao")
public class PessoaDaoJpa extends PersistenciaJpa<Pessoa> implements PessoaDao {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
   @Override
	public List<Pessoa> pesquisaSelecaoCandidato(SelecaoCandidato selecaoCandidato) {
			String sql = "select p from Pessoa p where p.recebeBeneficio = :recebeBeneficio ";
			
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
}
