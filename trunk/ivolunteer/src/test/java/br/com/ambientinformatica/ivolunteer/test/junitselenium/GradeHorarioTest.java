package br.com.ambientinformatica.ivolunteer.test.junitselenium;

import org.junit.Test;

import br.com.ambientinformatica.ivolunteer.entidade.EnumDiaSemana;
import br.com.ambientinformatica.ivolunteer.entidade.GradeHorario;
import br.com.ambientinformatica.ivolunteer.persistencia.GradeHorarioDao;
import br.com.ambientinformatica.ivolunteer.persistencia.GradeHorarioDaoJpa;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

public class GradeHorarioTest {

	@Test(expected = IllegalArgumentException.class)
	public void test() {
		GradeHorario gradehorario = new GradeHorario();
		EnumDiaSemana diaSemana = EnumDiaSemana.SEGUNDA;

		gradehorario.setDiaSemana(diaSemana);
		GradeHorarioDao gradehorarioDao = new GradeHorarioDaoJpa();

		try {
			gradehorarioDao.incluir(gradehorario);
		} catch (PersistenciaException e) {
			e.printStackTrace();

		}
	}
}
