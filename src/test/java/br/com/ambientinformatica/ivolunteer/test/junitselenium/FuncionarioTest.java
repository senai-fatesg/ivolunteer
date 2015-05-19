package br.com.ambientinformatica.ivolunteer.test.junitselenium;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.ambientinformatica.ivolunteer.entidade.AtividadeDiaria;
import br.com.ambientinformatica.ivolunteer.entidade.Endereco;
import br.com.ambientinformatica.ivolunteer.entidade.EnumDiaSemana;
import br.com.ambientinformatica.ivolunteer.entidade.Frequencia;
import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;
import br.com.ambientinformatica.ivolunteer.entidade.GradeHorario;
import br.com.ambientinformatica.ivolunteer.persistencia.FuncionarioDao;
import br.com.ambientinformatica.ivolunteer.persistencia.FuncionarioDaoJpa;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

public class FuncionarioTest {

//	@Test(expected = IllegalArgumentException.class)
//	public void test() {
//		
//		Funcionario funcionario = new Funcionario();
//
//		Endereco endereco = new Endereco();
//		endereco.setBairro("www");
//		List<Endereco> enderecos = new ArrayList<Endereco>();
//		enderecos.add(endereco);
//
//		Frequencia frequencia = new Frequencia();
//		frequencia.setPresenca();
//		List<Frequencia> frequencias = new ArrayList<Frequencia>();
//		frequencias.add(frequencia);
//
//		GradeHorario gradehorario = new GradeHorario();
//		EnumDiaSemana diaSemana = EnumDiaSemana.SEGUNDA;
//		gradehorario.setDiaSemana(diaSemana);
//		List<GradeHorario> gradeHorarios = new ArrayList<GradeHorario>();
//		gradeHorarios.add(gradehorario);
//
//		AtividadeDiaria atividadeDiaria = new AtividadeDiaria();
//		atividadeDiaria.setAtividade("limpeza");
//		List<AtividadeDiaria> atividadeDiarias = new ArrayList<AtividadeDiaria>();
//		atividadeDiarias.add(atividadeDiaria);
//
//		funcionario.setEndereco(enderecos);
//		funcionario.setNomePessoa("Vilmar");
//
//		funcionario.setFrequencia(frequencias);
//		funcionario.setGradeHorario(gradeHorarios);
//		funcionario.setAtividadeDiaria(atividadeDiarias);
//
//		FuncionarioDao funcionarioDao = new FuncionarioDaoJpa();
//
//		try {
//			funcionarioDao.incluir(funcionario);
//		} catch (PersistenciaException e) {
//			e.printStackTrace();
//		}
//		//fail("Falha no teste pois era esperado uma exceção");
//	}

}
