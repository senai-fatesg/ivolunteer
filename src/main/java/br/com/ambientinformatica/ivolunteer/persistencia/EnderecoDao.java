package br.com.ambientinformatica.ivolunteer.persistencia;

import br.com.ambientinformatica.ivolunteer.entidade.Endereco;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface EnderecoDao extends Persistencia<Endereco>{

	public Endereco desativaEndereco(Endereco endereco);
	
}



