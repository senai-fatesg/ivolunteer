package br.com.ambientinformatica.ivolunteer.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Endereco;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("enderecoDao")
public class EnderecoDaoJpa extends PersistenciaJpa<Endereco> implements EnderecoDao{
	private static final long serialVersionUID = 1L;
}

