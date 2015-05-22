package br.com.ambientinformatica.ivolunteer.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.RelatorioAprendizagem;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("relatorioAprendizagemDao")
public class RelatorioAprendizagemDaoJpa extends
		PersistenciaJpa<RelatorioAprendizagem> implements
		RelatorioAprendizagemDao {
	private static final long serialVersionUID = 1L;
}
