package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Usuario;
import br.com.ambientinformatica.ivolunteer.persistencia.UsuarioDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Controller("UsuarioListaControl")
@Scope("conversation")
public class UsuarioListaControl {

    private Usuario usuario =  new Usuario();

    private List<Usuario> usuarios = new ArrayList<Usuario>();

    private String login;
    private Date dataCadastro;

    @Autowired
    private UsuarioDao usuarioDao;

    @PostConstruct
    public void init(){

    }

    public List<Usuario> listar(){
        try {
            usuarios = usuarioDao.listar();
        } catch (PersistenciaException e) {
            UtilFaces.addMensagemFaces(e);
        }
        return usuarios;
    }

    public void buscarUsuario(){
        usuario = usuarioDao.consultarPorLogin(login);
        usuarios.clear();
        usuarios.add(usuario);
        limparCampos();
    }

    public void reiniciarSenha(Usuario usuario){
        usuario.setSenhaNaoCriptografada("123456");
        try {
            usuarioDao.alterar(usuario);
            UtilFaces.addMensagemFaces("Senha reiniciada com sucesso");
        } catch (PersistenciaException e) {
            UtilFaces.addMensagemFaces(e);
        }
    }

    public void limparCampos(){
        setLogin("");
        setDataCadastro(null);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

}