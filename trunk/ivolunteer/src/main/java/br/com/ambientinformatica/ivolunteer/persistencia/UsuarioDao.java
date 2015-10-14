package br.com.ambientinformatica.ivolunteer.persistencia;

import javax.persistence.PersistenceException;

import br.com.ambientinformatica.ivolunteer.entidade.Usuario;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface UsuarioDao extends Persistencia<Usuario>{

	Usuario consultarPorLogin(String login) throws PersistenceException;


}
