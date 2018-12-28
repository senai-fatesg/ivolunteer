package br.com.ambientinformatica.ivolunteer.controle;

import java.util.HashMap;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ambientjsf.util.UtilFacesRelatorio;
import br.com.ambientinformatica.ivolunteer.persistencia.TurmaDao;

@Controller("RelatoriosTurmaControl")
@Scope("conversation")
public class RelatoriosTurmaControl {
	
	String tipoRelatorio;

	@Autowired
	private TurmaDao turmaDao;

	public void geraRelatorio() {
		if(this.tipoRelatorio != null && !this.tipoRelatorio.isEmpty()) {
			try {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("ivolunteerLogo", UtilFacesRelatorio.getCaminhoContexto("/imagens/ivolunteer.png"));
				UtilFacesRelatorio.gerarRelatorioFaces("jasper/turmas.jasper", turmaDao.listar(), map);
			} catch (Exception e) {
				e.printStackTrace();
				UtilFaces.addMensagemFaces(e);
			}
		} else {
			UtilFaces.addMensagemFaces("Por favor, selecione um relatório!", FacesMessage.SEVERITY_ERROR);
		}
	}

	public TurmaDao getTurmaDao() {
		return turmaDao;
	}

	public void setTurmaDao(TurmaDao turmaDao) {
		this.turmaDao = turmaDao;
	}



	public String getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}
}
