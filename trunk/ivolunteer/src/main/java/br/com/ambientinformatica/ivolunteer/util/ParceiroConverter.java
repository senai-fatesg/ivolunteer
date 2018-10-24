package br.com.ambientinformatica.ivolunteer.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import br.com.ambientinformatica.ivolunteer.entidade.Avaliacao;
import br.com.ambientinformatica.ivolunteer.entidade.Parceiro;
import br.com.ambientinformatica.ivolunteer.persistencia.AvaliacaoDao;
import br.com.ambientinformatica.ivolunteer.persistencia.ParceiroDao;
import br.com.ambientinformatica.ivolunteer.persistencia.ParceiroDaoJpa;
import br.com.ambientinformatica.jpa.util.FabricaAbstrata;
@FacesConverter("parceiroConverter")
public class ParceiroConverter implements Converter {

	@Autowired
	private ParceiroDao parceiroDao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.trim().equals("")) {
			Parceiro parceiro= new Parceiro();
			try {
				Integer id = Integer.parseInt(value);
				try {
					//System.out.println("ParceiroDao é NULO? " + (this.parceiroDao.equals(null)));
					//parceiro = parceiroDao.buscaParceiroPorID(id);
					parceiro.setId(id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (NumberFormatException exception) {
				return null;
			}
			return parceiro;
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
			System.out.println("VALOR: " + value);
			return value.toString();
			//return String.valueOf(((Avaliacao) value).getId());
		}
	}

}
