package br.com.ambientinformatica.ivolunteer.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ambientinformatica.ivolunteer.entidade.Responsavel;
import br.com.ambientinformatica.ivolunteer.persistencia.ResponsavelDao;
import br.com.ambientinformatica.jpa.util.FabricaAbstrata;

@FacesConverter("responsavelConverter")
public class ResponsavelConverter implements Converter {

	private ResponsavelDao responsavelDao = (ResponsavelDao) FabricaAbstrata
			.criarObjeto("responsavelDao");

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.trim().equals("")) {
			Responsavel responsavel = new Responsavel();
			try {
				int id = Integer.parseInt(value);
				try {
					responsavel = responsavelDao.consultar(id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (NumberFormatException exception) {
				return null;
			}
			return responsavel;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null || value.equals("")) {
			return "";
		} else {
			return String.valueOf(((Responsavel) value).getId());
		}
	}
}
