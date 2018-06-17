package br.com.ambientinformatica.ivolunteer.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.ambientinformatica.ivolunteer.entidade.Aluno;
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.persistencia.AlunoDao;

@Path("/json/aluno")
public class AlunoWS {
	
	@PostConstruct
	public void init() {
	    SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Autowired
	public AlunoDao alunoDao;
	
	private static List<Pessoa> contacts = new ArrayList<Pessoa>();
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Aluno getAlunoJSON() {
		
		Aluno aluno = new Aluno();
		aluno.setNomePessoa("Francisco");

		return aluno;

	}
	
	@GET
	@Path("/todos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Aluno> getAlunosJSON(){
		
		return alunoDao.listarTodos();
	}

}
