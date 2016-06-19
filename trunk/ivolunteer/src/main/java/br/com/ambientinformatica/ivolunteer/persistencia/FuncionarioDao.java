package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface FuncionarioDao extends Persistencia<Funcionario> {

	public List<Funcionario> listarPorNome(String query);
	
	Funcionario carregarFuncionario(Funcionario funcionario);
	
}
