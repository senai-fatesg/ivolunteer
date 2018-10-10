package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import br.com.ambientinformatica.ivolunteer.entidade.EnumCargo;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoFuncionario;
import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;
import br.com.ambientinformatica.ivolunteer.entidade.Turma;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface FuncionarioDao extends Persistencia<Funcionario> {
	
	Funcionario carregarFuncionario(Funcionario funcionario);

	public Funcionario carregarFuncionarioComEnderecoTelefone(Funcionario funcionario);

	public List<Funcionario> listarFuncionariosAtivos();

	public List<Funcionario> listarPorNome(String nome);

	public List<Funcionario> listarPorTipo(EnumTipoFuncionario tipo);
	
	public List<Funcionario> listarPorStatus(String status);
	
	public List<Funcionario> listarPorNomeETipo(String nome, EnumTipoFuncionario tipo);

	public List<Funcionario> listarPorNomeEStatus(String nome, String status);
	
	public List<Funcionario> listarPorTipoEStatus(EnumTipoFuncionario tipo, String status);

	public List<Funcionario> buscaEducadorPorNome(String nome);

	public List<Funcionario> listarEducadoresAtivos(EnumCargo educador);
	
}
