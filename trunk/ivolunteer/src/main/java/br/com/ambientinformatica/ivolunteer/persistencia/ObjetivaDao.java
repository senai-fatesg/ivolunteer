package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import br.com.ambientinformatica.ivolunteer.entidade.Avaliacao;
import br.com.ambientinformatica.ivolunteer.entidade.Objetiva;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface ObjetivaDao extends Persistencia<Objetiva> {
	public List<Objetiva> obterObjetivas(Avaliacao avaliacao);
}
