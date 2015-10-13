package br.com.ambientinformatica.ivolunteer.controle;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.EnumPapelUsuario;
import br.com.ambientinformatica.ivolunteer.entidade.PapelUsuario;
import br.com.ambientinformatica.ivolunteer.entidade.Usuario;
import br.com.ambientinformatica.ivolunteer.persistencia.UsuarioDao;

@Controller("UsuarioLogadoControl")
@Scope("session")
public class UsuarioLogadoControl implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	@Autowired
	private UsuarioDao usuarioDao;

	@PostConstruct
	public void init() {
		buscarUsuario();
	}

	private void buscarUsuario() {
			try {
				HttpServletRequest req = UtilFaces.getRequest();
				if (req.getUserPrincipal() != null) {
					String login = req.getUserPrincipal().getName();
					usuario = usuarioDao.consultarPorLogin(login);
				}
			} catch (Exception e) {
				UtilFaces.addMensagemFaces(e);
			}
   }

	public boolean isLogado() {
		return getUsuarioLogado() != null;
	}

	public boolean isAdministrador() {
		for (PapelUsuario p : usuario.getPapeisList()) {
			if (p.getPapel() == EnumPapelUsuario.ADMIN) {
				return true;
			}
		}
		return false;
	}
	
	public String getIp(){
		return UtilFaces.getRequest().getHeader("X-FORWARDED-FOR");
	}
	
	public Usuario getUsuario() {
		if (usuario == null) {
			buscarUsuario();
		}
		return usuario;
	}

	public static Usuario getUsuarioLogado() {
		return (Usuario) UtilFaces.getObjetoManagedBean("#{UsuarioLogadoControl.usuario}");
	}

}
