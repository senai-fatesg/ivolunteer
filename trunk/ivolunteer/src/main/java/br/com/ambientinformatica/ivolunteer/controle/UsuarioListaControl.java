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
import br.com.ambientinformatica.util.UtilLog;

@Controller("UsuarioListaControl")
@Scope("conversation")
public class UsuarioListaControl {

	private Usuario usuario =  new Usuario();

	private List<Usuario> usuarios = new ArrayList<Usuario>();

	private String login;
	private String nomePessoa;

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
			UtilFaces.addMensagemFaces("Erro ao resetar a senha");
		}
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