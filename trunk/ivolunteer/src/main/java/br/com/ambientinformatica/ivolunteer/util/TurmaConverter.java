package br.com.ambientinformatica.ivolunteer.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ambientinformatica.ivolunteer.entidade.Turma;
import br.com.ambientinformatica.ivolunteer.persistencia.TurmaDao;
import br.com.ambientinformatica.jpa.util.FabricaAbstrata;

@FacesConverter("TurmaConverter")
public class TurmaConverter implements Converter {
	private TurmaDao turmaDao = (TurmaDao) FabricaAbstrata
			.criarObjeto("turmaDao");

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component,
			Object value) {
		if (value == null || value.equals("")) {
			return "";
		} else {
			return String.valueOf(((Turma) value).getId());
		}
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.trim().equals("")) {
			Turma turma = new Turma();
			try {
				int id = Integer.parseInt(value);
				try {
					turma = turmaDao.consultar(id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (NumberFormatException exception) {
				return null;
			}
			return turma;
		} else {
			return null;
		}
	}
}
