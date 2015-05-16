package br.com.ambientinformatica.ivolunteer.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;
import br.com.ambientinformatica.ivolunteer.entidade.*;

@Repository("telefoneDao")
public class TelefoneDaoJpa extends PersistenciaJpa<Telefone> implements TelefoneDao{
	private static final long serialVersionUID = 1L;
}

