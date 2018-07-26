package br.com.ambientinformatica.ivolunteer.persistencia;

import br.com.ambientinformatica.ivolunteer.entidade.Responsavel;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface ResponsavelDao extends Persistencia<Responsavel>{

	public Responsavel consultaResponsavelCompleto(Responsavel responsavel);

}
