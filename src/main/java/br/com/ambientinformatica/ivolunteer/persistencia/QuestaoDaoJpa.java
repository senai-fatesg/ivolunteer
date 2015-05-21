package br.com.ambientinformatica.ivolunteer.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Questao;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("questaoDao")
public class QuestaoDaoJpa extends PersistenciaJpa<Questao> implements
      QuestaoDao {
	private static final long serialVersionUID = 1L;

}
