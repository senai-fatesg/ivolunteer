package br.com.ambientinformatica.ivolunteer.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.AtividadeDiaria;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("atividadeDiariaDao")
public class AtividadeDiariaDaoJpa extends PersistenciaJpa<AtividadeDiaria>
		implements AtividadeDiariaDao {
	private static final long serialVersioonUID = 1L;

}
