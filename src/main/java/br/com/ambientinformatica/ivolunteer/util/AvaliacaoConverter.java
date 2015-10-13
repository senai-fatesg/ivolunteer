package br.com.ambientinformatica.ivolunteer.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ambientinformatica.ivolunteer.entidade.Avaliacao;
import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;
import br.com.ambientinformatica.ivolunteer.persistencia.AvaliacaoDao;
import br.com.ambientinformatica.jpa.util.FabricaAbstrata;

@FacesConverter("avaliacaoConverter")
public class AvaliacaoConverter implements Converter {

	private AvaliacaoDao avaliacaoDao = (AvaliacaoDao) FabricaAbstrata
			.criarObjeto("avaliacaoDao");

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.trim().equals("")) {
			Avaliacao avaliacao = new Avaliacao();
			try {
				int id = Integer.parseInt(value);
				try {
					avaliacao = avaliacaoDao.consultar(id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (NumberFormatException exception) {
				return null;
			}
			return avaliacao;
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
			return String.valueOf(((Avaliacao) value).getId());
		}
	}

}
