package br.com.ambientinformatica.ivolunteer.test;

import static org.junit.Assert.fail;

import org.junit.Test;

import br.com.ambientinformatica.ivolunteer.entidade.EnumDiaSemana;
import br.com.ambientinformatica.ivolunteer.entidade.Frequencia;
import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;
import br.com.ambientinformatica.ivolunteer.entidade.GradeHorario;
import br.com.ambientinformatica.ivolunteer.persistencia.FrequenciaDao;
import br.com.ambientinformatica.ivolunteer.persistencia.FrequenciaDaoJpa;
import br.com.ambientinformatica.ivolunteer.persistencia.GradeHorarioDao;
import br.com.ambientinformatica.ivolunteer.persistencia.GradeHorarioDaoJpa;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

public class GradeHorarioTest {

	@Test(expected = IllegalArgumentException.class)
	public void test() {
		GradeHorario gradehorario = new GradeHorario();
		EnumDiaSemana diaSemana = EnumDiaSemana.SEGUNDA;
		Funcionario funcionario = new Funcionario();
		funcionario.setAgencia("8405");

		gradehorario.setDiaSemana(diaSemana);
		gradehorario.setFuncionario(funcionario);

		GradeHorarioDao gradehorarioDao = new GradeHorarioDaoJpa();

		try {
			gradehorarioDao.incluir(gradehorario);
		} catch (PersistenciaException e) {
			e.printStackTrace();

		}
	}
}
