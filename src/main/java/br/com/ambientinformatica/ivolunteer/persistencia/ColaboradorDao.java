package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import br.com.ambientinformatica.ivolunteer.entidade.Colaborador;
import br.com.ambientinformatica.ivolunteer.entidade.EnumCargo;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoFuncionario;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface ColaboradorDao extends Persistencia<Colaborador> {
	
	Colaborador carregarFuncionario(Colaborador funcionario);

	public Colaborador carregarFuncionarioComEnderecoTelefone(Colaborador funcionario);

	public List<Colaborador> listarFuncionariosAtivos();

	public List<Colaborador> listarPorNome(String nome);

	public List<Colaborador> listarPorTipo(EnumTipoFuncionario tipo);
	
	public List<Colaborador> listarPorStatus(String status);
	
	public List<Colaborador> listarPorNomeETipo(String nome, EnumTipoFuncionario tipo);

	public List<Colaborador> listarPorNomeEStatus(String nome, String status);
	
	public List<Colaborador> listarPorTipoEStatus(EnumTipoFuncionario tipo, String status);

	public List<Colaborador> buscaEducadorPorNome(String nome);

	List<Colaborador> listarEducadoresAtivos() throws PersistenciaException;
	
}
