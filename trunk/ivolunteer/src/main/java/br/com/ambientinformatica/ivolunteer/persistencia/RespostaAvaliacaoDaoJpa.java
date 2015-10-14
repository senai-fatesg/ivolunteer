package br.com.ambientinformatica.ivolunteer.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.RespostaAvaliacao;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;
@Repository("respostaAvaliacaoDao")
public class RespostaAvaliacaoDaoJpa extends PersistenciaJpa<RespostaAvaliacao>
		implements RespostaAvaliacaoDao {

	private static final long serialVersionUID = 1L;

}
