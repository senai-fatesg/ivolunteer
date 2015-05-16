package br.com.ambientinformatica.ivolunteer.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Objetiva;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("objetivaDao")
public class ObjetivaDaoJpa extends PersistenciaJpa<Objetiva> implements ObjetivaDao{
	
	private static final long serialVersionUID = 1L;

}
