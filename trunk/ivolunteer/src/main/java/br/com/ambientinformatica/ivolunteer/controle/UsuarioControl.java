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
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
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
    
    private String nome;
    
    private String cpf;
    
    private String login;

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private PessoaDao pessoaDao;

    private boolean usuarioAtivo = true;

    public void adicionarPapel(){
        if(usuario.getLogin() != null)
        	//busca usuario existente para edição 
        	usuario = usuarioDao.consultarPorLogin(usuario.getLogin());
        else{
        	//novo usuario adicionado
        	Pessoa p = new Pessoa();
        	p.setCpf(this.cpf);
        	p.setNomePessoa(this.nome);
        	usuario.setPessoa(p);
        	usuario.setLogin(this.login);
        }
        boolean possuiPermissao = false;
        if(usuario != null){        
        	for(EnumPapelUsuario item : this.getPapeisAdicionados()){
	        	if(item.getDescricao().equalsIgnoreCase(papel.getDescricao())){
	        		UtilFaces.addMensagemFaces("O usuário já possui a permissão selecionada.");
	        		possuiPermissao = true;
	        		break;
	        	}
        	}
        	if(!possuiPermissao || this.getPapeisAdicionados().isEmpty()){
        		papeisAdicionados.add(papel);
        	}
        }
    }

    public void removerPapel(EnumPapelUsuario papel){
    	papeisAdicionados.remove(papel);
    	usuario.removeAllPapel();
    }

    public void confirmar(ActionEvent evt){
        try {
	        if(usuario == null || usuario.getPessoa() == null || usuario.getPessoa().getId() == null){
	        	usuario = new Usuario();
	        	
	            usuario.getPessoa().setEnumTipoPessoa(EnumTipoPessoa.COLABORADOR);
	            usuario.getPessoa().setCpf(cpf);
	            usuario.getPessoa().setNomePessoa(nome);
	            pessoaDao.incluir(usuario.getPessoa());
	            usuario.setSenhaNaoCriptografada(senha);
	            usuario.addPapel(EnumPapelUsuario.USUARIO);
	            usuario.addAllPapel(papeisAdicionados);
	            usuario.setLogin(login);
	            usuario.setAtivo(usuarioAtivo);
	            usuarioDao.incluir(usuario);
	            limparCampos();
	            UtilFaces.addMensagemFaces("Usuário incluido com sucesso.");
	        }else{
	        	usuario.addPapel(EnumPapelUsuario.USUARIO);
	            usuario.addAllPapel(papeisAdicionados);
	            usuario.setAtivo(usuarioAtivo);
	            usuarioDao.alterar(usuario);
	            limparCampos();
	            UtilFaces.addMensagemFaces("Usuário alterado com sucesso.");
	        }
        } catch (PersistenciaException e) {
            limparCampos();
        	UtilFaces.addMensagemFaces("Houve um erro ao adicionar o usuário.");
        }
    }

    public void limparCampos(){
    	usuario = new Usuario();
    	papeisAdicionados = new ArrayList<EnumPapelUsuario>();
        setNome("");
        setSenha("");
        setLogin("");
        setUsuarioAtivo(false);
        this.papel = null;
    }
    
    public void voltar(){
        atualizaLista();
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
        atualizaLista();
    }

    public void atualizaLista() {
        if(usuario != null){
            usuario = usuarioDao.consultarPorLogin(usuario.getLogin());
            papeisAdicionados = new ArrayList<EnumPapelUsuario>();  
            for (PapelUsuario p : usuario.getPapeis()) {
                    papeisAdicionados.add(p.getPapel());
            }
        }
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

    public boolean isUsuarioAtivo() {
        return usuarioAtivo;
    }

    public void setUsuarioAtivo(boolean usuarioAtivo) {
        this.usuarioAtivo = usuarioAtivo;
    }

    public void setPapeisAdicionados(List<EnumPapelUsuario> papeisAdicionados) {
        this.papeisAdicionados = papeisAdicionados;
    }

	public String getNome() {
		return this.getUsuario().getPessoa().getNomePessoa();
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return this.getUsuario().getPessoa().getCpf();
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getLogin() {
		return this.getUsuario().getLogin();
	}

	public void setLogin(String login) {
		this.login = login;
	}
    
}
