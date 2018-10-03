package br.com.ambientinformatica.ivolunteer.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.ambientinformatica.ivolunteer.entidade.Curso;
import br.com.ambientinformatica.ivolunteer.persistencia.CursoDao;
import br.com.ambientinformatica.ivolunteer.persistencia.CursoDaoJpa;
import br.com.ambientinformatica.jpa.util.FabricaAbstrata;
@Service
@FacesConverter("cursoConverter")
public class CursoConverter implements Converter {
	
	
	@Autowired
	private CursoDao cursoDao;
	
	/*
	private CursoDao cursoDao = (CursoDao) FabricaAbstrata.criarObjeto("cursoDao");
	*/

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		System.out.println("VALOR COMO OBJETO: " + value);
		System.out.println("CURSODAO NULL? " + (this.cursoDao.equals(null)));
		if (value != null && !value.trim().equals("")) {
			Curso curso = new Curso();
			try {
				int id = Integer.parseInt(value);
				curso.setId(id);
				try {
					curso = cursoDao.consultar(curso.getId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (NumberFormatException exception) {
				return null;
			}
			return curso;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		System.out.println("VALOR COMO STRING: " + value);
		if (value == null || value.equals("")) {
			return "";
		} else {
			return String.valueOf(((Curso) value).getId());
		}
	}

}
