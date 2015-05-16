package br.com.ambientinformatica.ivolunteer.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;
import br.com.ambientinformatica.ivolunteer.entidade.Cidade;;

@Repository("cidadeDao")
public class CidadeDaoJpa extends PersistenciaJpa<Cidade> implements CidadeDao{

   private static final long serialVersionUID = 1L;


}
