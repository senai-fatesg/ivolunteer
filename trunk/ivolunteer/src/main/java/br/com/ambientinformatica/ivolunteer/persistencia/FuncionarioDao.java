package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoFuncionario;
import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface FuncionarioDao extends Persistencia<Funcionario> {
	
	Funcionario carregarFuncionario(Funcionario funcionario);

	public Funcionario carregarFuncionarioComEnderecoTelefone(Funcionario funcionario);

	public List<Funcionario> listarFuncionariosAtivos();

	public List<Funcionario> listarPorNomeAtivo(String nome);

	public List<Funcionario> listarPorTipoAtivo(EnumTipoFuncionario tipo);
	
	public List<Funcionario> listarPorNomeETipoAtivo(String nome, EnumTipoFuncionario tipo);
	
}
