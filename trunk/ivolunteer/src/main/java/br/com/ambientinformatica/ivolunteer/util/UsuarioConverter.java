package br.com.ambientinformatica.ivolunteer.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Usuario;
import br.com.ambientinformatica.ivolunteer.persistencia.UsuarioDao;
import br.com.ambientinformatica.jpa.util.FabricaAbstrata;
import br.com.ambientinformatica.util.UtilLog;

@FacesConverter("usuarioConverter")
public class UsuarioConverter implements Converter {  

   private UsuarioDao usuarioDao = (UsuarioDao) FabricaAbstrata.criarObjeto("usuarioDao");
   
   @Override
   public String getAsString(FacesContext facesContext, UIComponent component, Object value) {  
       if (value == null || value.equals("")) {  
           return "";  
       } else {  
           return String.valueOf(((Usuario) value).getId());  
       }  
   }


   @Override
   public Object getAsObject(FacesContext context, UIComponent component, String value) {
      if (value != null && !value.trim().equals("")) {  
         Usuario usuario = new Usuario();
         try {  
            long id = Long.parseLong(value);  

            try {
               usuario = usuarioDao.consultar(id);
            } catch (Exception e) {
               UtilFaces.addMensagemFaces("Houve um erro ao Converter o Usuario");
               UtilLog.getLog().error(e);
            }
         } catch(NumberFormatException exception) {  
//            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Usuario escolhido não é válido"));
            return null;
         }  
         return usuario;  
      }else{
         return null;
      }
   }
}  

