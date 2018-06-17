package br.com.ambientinformatica.ivolunteer.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.ambientinformatica.ivolunteer.entidade.Aluno;
import br.com.ambientinformatica.ivolunteer.persistencia.AlunoDao;

@Path("/json/aluno")
public class AlunoWS {
	
	@Autowired
	public AlunoDao alunoDao;
	
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
