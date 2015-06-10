package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import br.com.ambientinformatica.ivolunteer.entidade.Avaliacao;
import br.com.ambientinformatica.ivolunteer.entidade.Discursiva;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface DiscursivaDao extends Persistencia<Discursiva> {
	public List<Discursiva> obterDiscursivas(Avaliacao avaliacao);
}
