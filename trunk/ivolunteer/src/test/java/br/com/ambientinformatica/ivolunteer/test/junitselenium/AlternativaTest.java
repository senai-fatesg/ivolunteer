package br.com.ambientinformatica.ivolunteer.test.junitselenium;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import br.com.ambientinformatica.ivolunteer.entidade.Alternativa;
import br.com.ambientinformatica.ivolunteer.persistencia.AlternativaDao;
import br.com.ambientinformatica.ivolunteer.persistencia.AlternativaDaoJpa;

public class AlternativaTest {

	@Test
	public void inserirAlternativa() {
		Alternativa alternativa = new Alternativa();
		AlternativaDao alternativaDao =  new AlternativaDaoJpa();
		alternativa.setAlternativa("Sim");
		try {
			alternativaDao.incluir(alternativa);
			assertEquals(alternativaDao.consultar(alternativa.getId()), alternativa.getId());
      } catch (Exception e) {
	      fail("Erro de Execução no Metodo inserir");
      }
	
	}	

}
