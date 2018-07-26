package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import br.com.ambientinformatica.ivolunteer.entidade.Candidato;
import br.com.ambientinformatica.ivolunteer.entidade.Endereco;
import br.com.ambientinformatica.ivolunteer.entidade.Responsavel;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface EnderecoDao extends Persistencia<Endereco>{

	public void desativaEndereco(Endereco endereco);
	public List<Endereco> buscaTodosEnderecosPorCandidato(Candidato candidato);
	public List<Endereco> buscaTodosEnderecosPorResponsavel(Responsavel responsavel);
}



