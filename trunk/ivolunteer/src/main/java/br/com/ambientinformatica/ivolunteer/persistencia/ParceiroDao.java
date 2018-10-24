package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import br.com.ambientinformatica.ivolunteer.entidade.Parceiro;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface ParceiroDao extends Persistencia<Parceiro> {

	public List<Parceiro> buscaParceiroPorNome(String nomeFiltro);

	public List<Parceiro> buscaParceiroPorStatus(String statusFiltro);

	public List<Parceiro> buscaParceiroPorStatusNome(String statusFiltro, String nomeFiltro);

	public Parceiro buscaParceiroComEndereco(Parceiro parceiro);
	
	public Parceiro buscaParceiroPorID(Integer id);

}
