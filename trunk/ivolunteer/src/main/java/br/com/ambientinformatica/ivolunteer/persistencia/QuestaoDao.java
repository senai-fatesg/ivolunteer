package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import br.com.ambientinformatica.ivolunteer.entidade.Avaliacao;
import br.com.ambientinformatica.ivolunteer.entidade.Questao;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface QuestaoDao extends Persistencia<Questao> {
	public List<Questao> llistaQuestoesPorAvaliacao(Avaliacao avaliacao);
}
