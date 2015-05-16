package br.com.ambientinformatica.ivolunteer.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.RelatorioAprendizagem;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("RealatorioAprendizagemDao")
public class RealatorioAprendizagemDaoJpa extends
		PersistenciaJpa<RelatorioAprendizagem> implements
		RealatorioAprendizagemDao {
	private static final long serialVersionUID = 1L;
}
