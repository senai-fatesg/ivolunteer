package br.com.ambientinformatica.ivolunteer.controle;

import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Avaliacao;
import br.com.ambientinformatica.ivolunteer.persistencia.AvaliacaoDao;

@Controller("AvaliacaoControl")
@Scope("conversation")
public class AvaliacaoControl {
	
	private Avaliacao avaliacao = new Avaliacao();
	
	@Autowired
	private AvaliacaoDao avaliacaoDao;
	
	public void confirmar(ActionEvent evt){
		try {
			avaliacaoDao.alterar(avaliacao);
         avaliacao = new Avaliacao();
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}
	


}
