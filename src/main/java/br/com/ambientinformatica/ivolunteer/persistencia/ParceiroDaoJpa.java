package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Parceiro;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("parceiroDao")
public class ParceiroDaoJpa extends PersistenciaJpa<Parceiro> implements ParceiroDao {

	@Override
	public List<Parceiro> buscaParceiroPorNome(String nomeFiltro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Parceiro> buscaParceiroPorStatus(String statusFiltro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Parceiro> buscaParceiroPorStatusNome(String statusFiltro, String nomeFiltro) {
		// TODO Auto-generated method stub
		return null;
	}

}
