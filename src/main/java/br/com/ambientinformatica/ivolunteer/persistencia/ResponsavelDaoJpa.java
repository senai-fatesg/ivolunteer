package br.com.ambientinformatica.ivolunteer.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Responsavel;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("responsavelDao")
public class ResponsavelDaoJpa extends PersistenciaJpa<Responsavel> implements ResponsavelDao {
	
	private static final long serialVersionUID = 1L;
}