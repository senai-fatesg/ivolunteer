package br.com.ambientinformatica.ivolunteer.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.ambientinformatica.ivolunteer.entidade.Aluno;

@Path("/json/aluno")
public class AlunoWS {
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Aluno getAlunoJSON() {
		
		Aluno aluno = new Aluno();
		aluno.setNomePessoa("Francisco");

		return aluno;

	}

}
