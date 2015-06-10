package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import br.com.ambientinformatica.ivolunteer.entidade.AgrupamentoTurma;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface AgrupamentoTurmaDao extends Persistencia<AgrupamentoTurma> {

	public List<AgrupamentoTurma> listarIdentificador(AgrupamentoTurma agrupamentoTurma);
	
}
