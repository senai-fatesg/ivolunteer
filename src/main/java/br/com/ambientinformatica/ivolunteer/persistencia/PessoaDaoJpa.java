package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.entidade.SelecaoCandidato;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("pessoaDao")
public class PessoaDaoJpa extends PersistenciaJpa<Pessoa> implements PessoaDao {

	private static final long serialVersionUID = 1L;

	@Override
   public List<Pessoa> pesquisaSelecaoCandidato(SelecaoCandidato selecaoCandidato) {
	   //TODO implementar a pesquisa
	   return null;
   }

}

