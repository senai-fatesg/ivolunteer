package br.com.ambientinformatica.ivolunteer.controle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ambientinformatica.ivolunteer.entidade.Funcionario;
import br.com.ambientinformatica.ivolunteer.persistencia.FuncionarioDao;
import br.com.ambientinformatica.jpa.util.FabricaAbstrata;

@FacesConverter("FuncionarioConverter")
public class FuncionarioConverter implements Converter{

	private FuncionarioDao funcionarioDao = (FuncionarioDao)FabricaAbstrata.criarObjeto("funcionarioDao");
	   
	   @Override
	   public String getAsString(FacesContext facesContext, UIComponent component, Object value) {  
	       if (value == null || value.equals("")) {  
	           return "";  
	       } else {  
	           return String.valueOf(((Funcionario) value).getId());  
	       }  
	   }


	   @Override
	   public Object getAsObject(FacesContext context, UIComponent component, String value) {
	      if (value != null && !value.trim().equals("")) {  
	         Funcionario funcionario = new Funcionario();
	         try {  
	            int id = Integer.parseInt(value);  
	            try {
	               funcionario = funcionarioDao.consultar(id);
	            } catch (Exception e) {
	               e.printStackTrace();
	            }
	         } catch(NumberFormatException exception) {  
//	            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Funcionario escolhido não é válido"));
	            return null;
	         }  
	         return funcionario;  
	      }else{
	         return null;
	      }
	   }
}
