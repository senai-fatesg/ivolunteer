package br.com.ambientinformatica.ivolunteer.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Telefone;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("telefoneDao")
public class TelefoneDaoJpa extends PersistenciaJpa<Telefone> implements
      TelefoneDao {
	private static final long serialVersionUID = 1L;

	@Override
	public void desativarTelefone(Telefone telefone) {
		Telefone tel = em.find(Telefone.class, telefone.getId());
		tel.desativa();
		em.merge(tel);
	}
}
