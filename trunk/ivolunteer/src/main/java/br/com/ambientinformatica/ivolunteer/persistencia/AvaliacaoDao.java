package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import br.com.ambientinformatica.ivolunteer.entidade.Avaliacao;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface AvaliacaoDao extends Persistencia<Avaliacao> {
	public List<Avaliacao> listarTitulo(Avaliacao avaliacao);
}
