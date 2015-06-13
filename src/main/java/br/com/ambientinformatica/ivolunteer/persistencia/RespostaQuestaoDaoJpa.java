package br.com.ambientinformatica.ivolunteer.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.RespostaQuestao;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("respostaQuestaoDao")
public class RespostaQuestaoDaoJpa extends PersistenciaJpa<RespostaQuestao>
		implements RespostaQuestaoDao {
	
	private static final long serialVersionUID = 1L;
	

}
