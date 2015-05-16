package br.com.ambientinformatica.ivolunteer.test;

import static org.junit.Assert.fail;

import org.junit.Test;

import br.com.ambientinformatica.ivolunteer.entidade.Frequencia;
import br.com.ambientinformatica.ivolunteer.persistencia.FrequenciaDao;
import br.com.ambientinformatica.ivolunteer.persistencia.FrequenciaDaoJpa;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;


public class FrequenciaTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void test() {
		Frequencia frequencia = new Frequencia();
		frequencia.setPresenca();
		
		FrequenciaDao frequenciaDao = new FrequenciaDaoJpa();
		
		try {
			frequenciaDao.incluir(frequencia);
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		fail("Falha no testes pois era esperada uma exceção.");
	}
	
	
	
	
}
