package br.com.ambientinformatica.ivolunteer.webservice;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.ambientinformatica.ivolunteer.entidade.Turma;
import br.com.ambientinformatica.ivolunteer.persistencia.TurmaDao;

@Path("/json/turma")
public class TurmWS {
	
	@PostConstruct
	public void init() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Autowired
	public TurmaDao turmaDao;
	
	@GET
	@Path("/turmas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Turma> getAlunosJSON() {

		return turmaDao.listarTodos();
	}
	
	@GET
	@Path("/{idTurma}")
	@Produces(MediaType.APPLICATION_JSON)
	public Turma getAlunoJSON(@PathParam("idTurma") Integer idTurma) {
		return turmaDao.getTurma(idTurma);

	}

}
