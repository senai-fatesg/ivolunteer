package br.com.ambientinformatica.ivolunteer.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;

@FacesConverter(value="funcionarioConverter", forClass = Funcionario.class)
public class FuncionarioConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext fc, UIComponent component, String value) {
		if (value != null && !value.isEmpty()) {
			return (Funcionario) component.getAttributes().get(value);
		}
		else
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof Funcionario) {
			Funcionario funcionario = (Funcionario) value;
			if (funcionario != null && funcionario instanceof Funcionario && funcionario.getId() != null) {
				component.getAttributes().put(funcionario.getId().toString(), funcionario);
				return funcionario.getId().toString();
			}
		}
		
		return null;
	}

}
