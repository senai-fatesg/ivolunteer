package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Usuario;
import br.com.ambientinformatica.ivolunteer.persistencia.UsuarioDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.util.UtilLog;

@Controller("UsuarioListaControl")
@Scope("conversation")
public class UsuarioListaControl {

	private Usuario usuario =  new Usuario();

	private List<Usuario> usuarios = new ArrayList<Usuario>();

	private String login;
	private String nomePessoa;
	private String status;
	private Usuario usuarioAtivo;

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
		usuarios = usuarioDao.consultarPorNome(nomePessoa);
		limparCampos();
	}

	public void reiniciarSenha(Usuario usuario){
		usuario.setSenhaNaoCriptografada("123456");
		try {
			usuarioDao.alterar(usuario);
			UtilFaces.addMensagemFaces("Senha reiniciada com sucesso");
		} catch (PersistenciaException e) {
			UtilLog.getLog().error(e.getMessage(), e);
			UtilFaces.addMensagemFaces("Erro ao reiniciar a senha");
		}
	}

	public void desativarUsuario(ActionEvent evt){
		if(!usuarioAtivo.isAtivo()){
			UtilFaces.addMensagemFaces("Usuário já esta desativado");
		}else{
			usuarioAtivo.setAtivo(false);
			try {
	         usuarioDao.alterar(usuarioAtivo);
	         UtilFaces.addMensagemFaces("Usuário desativado com sucesso");
         } catch (PersistenciaException e) {
         	UtilLog.getLog().error(e.getMessage(), e);
         	UtilFaces.addMensagemFaces("Erro ao desativar o usuário");
         }
		}
			
	}
	
	public void ativarUsuario(ActionEvent evt){
		if(usuarioAtivo.isAtivo()){
			UtilFaces.addMensagemFaces("Usuário já esta ativado");
		}else{
			usuarioAtivo.setAtivo(true);
			try {
	         usuarioDao.alterar(usuarioAtivo);
	         UtilFaces.addMensagemFaces("Usuário liberado para acesso ao sistema");
         } catch (PersistenciaException e) {
         	UtilLog.getLog().error(e.getMessage(), e);
         	UtilFaces.addMensagemFaces("Erro ao ativar o usuário");
         }
		}
			
	}
	
	public String getStatus() {
	    return status;
	}
	
	public void setStatus(String status) {
	    this.status = status;
	}

	public Usuario getUsuarioAtivo() {
		return usuarioAtivo;
	}

	public void setUsuarioAtivo(Usuario usuarioAtivo) {
		this.usuarioAtivo = usuarioAtivo;
	}

	public void limparCampos(){
		setNomePessoa("");
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
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