package br.com.ambientinformatica.ivolunteer.controle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ambientinformatica.ivolunteer.entidade.Avaliacao;
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.persistencia.PessoaDao;
import br.com.ambientinformatica.jpa.util.FabricaAbstrata;

@FacesConverter("pessoaConverter")
public class PessoaConverter implements Converter {
	
	private PessoaDao pessoaDao = (PessoaDao) FabricaAbstrata
			.criarObjeto("pessoaDao");

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.trim().equals("")) {
			Pessoa pessoa = new Pessoa();
			try {
				int id = Integer.parseInt(value);
				try {
					pessoa = pessoaDao.consultar(id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (NumberFormatException exception) {
				return null;
			}
			return pessoa;
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
			return String.valueOf(((Pessoa) value).getId());
		}
	}

}
