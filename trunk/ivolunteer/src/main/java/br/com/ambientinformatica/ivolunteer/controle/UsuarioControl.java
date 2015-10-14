package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;






import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.EnumPapelUsuario;
import br.com.ambientinformatica.ivolunteer.entidade.PapelUsuario;
import br.com.ambientinformatica.ivolunteer.entidade.Usuario;
import br.com.ambientinformatica.ivolunteer.persistencia.UsuarioDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Controller("UsuarioControl")
@Scope("conversation")
public class UsuarioControl {
    
    private Usuario usuario = new Usuario();
    private EnumPapelUsuario papel;
    private PapelUsuario papelUsuario;
    
    private List<PapelUsuario> papeis =  new ArrayList<PapelUsuario>();

    @Autowired
    private UsuarioDao usuarioDao;

    @PostConstruct
    public void init(){

    }
    
    public void adicionarPapel(){
        try{
            usuario.addPapel(papel);
        }catch(Exception e){
            UtilFaces.addMensagemFaces(e);
        }
    }

    public void removerPapel(ActionEvent evt){
        try{
            usuario.removerPapel((PapelUsuario) UtilFaces.getValorParametro(evt, "papel"));
        }catch(Exception e){
            UtilFaces.addMensagemFaces(e);
        }
    }
    
    public void confirmar(ActionEvent evt){
        try {
            usuarioDao.alterar(usuario);
            UtilFaces.addMensagemFaces("");
        } catch (PersistenciaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    
    public EnumPapelUsuario getPapel() {
        return papel;
    }

    public void setPapel(EnumPapelUsuario papel) {
        this.papel = papel;
    }

    public void setPapeis(List<PapelUsuario> papeis) {
        this.papeis = papeis;
    }

    public void addPapel() {
        usuario.addPapel(papel);
    }

    public List<SelectItem> getPapeis() {
        return UtilFaces.getListEnum(EnumPapelUsuario.values());
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PapelUsuario getPapelUsuario() {
        return papelUsuario;
    }

    public void setPapelUsuario(PapelUsuario papelUsuario) {
        this.papelUsuario = papelUsuario;
    }

}
