package br.com.ambientinformatica.ivolunteer.util;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ambientinformatica.ivolunteer.entidade.EnumPapelUsuario;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoPessoa;
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.entidade.Usuario;
import br.com.ambientinformatica.ivolunteer.persistencia.PessoaDao;
import br.com.ambientinformatica.ivolunteer.persistencia.UsuarioDao;
import br.com.ambientinformatica.util.UtilLog;

@Service("inicializadorSistema")
public class InicializadorSistema {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private PessoaDao pessoaDao;
	
	@PostConstruct
	public void iniciar(){
		inicializarUsuarioAdmin();
	}
	
	private void inicializarUsuarioAdmin(){
		try {
			List<Usuario> usuarios = usuarioDao.listar();
			if(usuarios.isEmpty()){
				Pessoa pessoa = new Pessoa();
				pessoa.setNomePessoa("ADMINISTRADOR");
				pessoa.setEnumTipoPessoa(EnumTipoPessoa.COLABORADOR);
				pessoa.setCpf("11111111111");
				pessoaDao.incluir(pessoa);
				
				Usuario usu = new Usuario();
				usu.setLogin("admin");
				usu.setSenhaNaoCriptografada("123456");
				usu.addPapel(EnumPapelUsuario.ADMIN);
				usu.addPapel(EnumPapelUsuario.USUARIO);
				usu.setAtivo(true);
				usu.setPessoa(pessoa);
				usuarioDao.incluir(usu);
				UtilLog.getLog().info("*** USUÁRIO admin CRIADO com a senha 123456 ***");
			}
		} catch (Exception e) {
			UtilLog.getLog().error(e.getMessage(), e);
		}
	}
	
}
