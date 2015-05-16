package br.com.ambientinformatica.ivolunteer.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Frequencia;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("frequenciaDao")
public class FrequenciaDaoJpa extends PersistenciaJpa<Frequencia> implements
		FrequenciaDao {
	private static final long serialVersioonUID = 1L;
}
