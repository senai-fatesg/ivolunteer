package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.entidade.SelecaoCandidato;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface PessoaDao extends Persistencia<Pessoa> {

	List<Pessoa> pesquisaSelecaoCandidato(SelecaoCandidato selecaoCandidato);
}

