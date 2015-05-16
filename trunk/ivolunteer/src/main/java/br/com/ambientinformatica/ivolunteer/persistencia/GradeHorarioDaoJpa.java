package br.com.ambientinformatica.ivolunteer.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.GradeHorario;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

	@Repository("GradeHorarioDao")
	public class GradeHorarioDaoJpa extends PersistenciaJpa<GradeHorario> implements GradeHorarioDao{

	   private static final long serialVersionUID = 1L;

}
