package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.RespostaAvaliacao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;
@Repository("respostaAvaliacaoDao")
public class RespostaAvaliacaoDaoJpa extends PersistenciaJpa<RespostaAvaliacao>
		implements RespostaAvaliacaoDao {

	private static final long serialVersionUID = 1L;

}
