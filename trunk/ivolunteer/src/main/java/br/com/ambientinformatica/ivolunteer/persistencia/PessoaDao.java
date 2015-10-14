package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.entidade.SelecaoCandidato;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface PessoaDao extends Persistencia<Pessoa> {

	public List<Pessoa> listarPorNome(String query);
	public List<Pessoa> listaCandidatoPorNome(String nome);
	public List<Pessoa> pesquisaSelecaoCandidato(SelecaoCandidato selecaoCandidato);
	public Pessoa consultarPessoaCompleta(Pessoa pessoa);
	public List<Pessoa> listaCandidato();
}

