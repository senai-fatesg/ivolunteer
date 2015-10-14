package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.EnumPapelUsuario;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoPessoa;
import br.com.ambientinformatica.ivolunteer.entidade.PapelUsuario;
import br.com.ambientinformatica.ivolunteer.entidade.Usuario;
import br.com.ambientinformatica.ivolunteer.persistencia.PessoaDao;
import br.com.ambientinformatica.ivolunteer.persistencia.UsuarioDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Controller("UsuarioControl")
@Scope("conversation")
public class UsuarioControl {
    
    private Usuario usuario = new Usuario();
    
    private EnumPapelUsuario papel;
    
    private List<EnumPapelUsuario> papeisAdicionados = new ArrayList<EnumPapelUsuario>();
    
    private String senha = "123456";
    
    @Autowired
    private UsuarioDao usuarioDao;
    
    @Autowired
    private PessoaDao pessoaDao;

    public void adicionarPapel(){
   	 papeisAdicionados.add(papel);
    }

    public void removerPapel(ActionEvent evt){
   	 papeisAdicionados.remove((PapelUsuario) UtilFaces.getValorParametro(evt, "papel"));
    }
    
    public void confirmar(ActionEvent evt){
        try {
      	  if(usuario == null || usuario.getPessoa() == null || usuario.getPessoa().getId() == null){
      		  usuario.getPessoa().setEnumTipoPessoa(EnumTipoPessoa.COLABORADOR);
      		  pessoaDao.incluir(usuario.getPessoa());
      		  usuario.setSenhaNaoCriptografada(senha);
      		  usuario.addAllPapel(papeisAdicionados);
      		  usuarioDao.incluir(usuario);
      		  UtilFaces.addMensagemFaces("Usuário incluido com sucesso.");
      		  usuario = new Usuario();
      		  papeisAdicionados = new ArrayList<EnumPapelUsuario>();
      	  }else{
      		  usuarioDao.alterar(usuario);
      		  UtilFaces.addMensagemFaces("Usuário alterado com sucesso.");
      	  }
      	  
        } catch (PersistenciaException e) {
            UtilFaces.addMensagemFaces("Houve um erro ao adicionar o usuário.");
        }
    }
    
    
    
    public EnumPapelUsuario getPapel() {
        return papel;
    }

    public void setPapel(EnumPapelUsuario papel) {
        this.papel = papel;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<EnumPapelUsuario> getPapeisAdicionados() {
		return papeisAdicionados;
	}

}
