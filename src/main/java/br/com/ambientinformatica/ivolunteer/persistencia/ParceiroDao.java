package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import br.com.ambientinformatica.ivolunteer.entidade.Parceiro;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface ParceiroDao extends Persistencia<Parceiro> {

	public List<Parceiro> listarPorNomeStatus(String nomeFiltro, String statusFiltro) throws PersistenciaException;

}
