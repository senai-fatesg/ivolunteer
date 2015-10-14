package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.ambientinformatica.jpa.util.AlfaNumerico;
import br.com.ambientinformatica.util.AmbientValidator;
import br.com.ambientinformatica.util.Entidade;
import br.com.ambientinformatica.util.UtilHash;
import br.com.ambientinformatica.util.UtilHash.Algoritimo;

@Entity
public class Usuario extends Entidade {

	@Id
	@Column(nullable = false, unique = true)
	@NotNull(message = "Login do usuário é obrigatório", groups = AmbientValidator.class)
	@NotEmpty(message = "Login do usuário é obrigatório", groups = AmbientValidator.class)
	@AlfaNumerico(message = "O login do usuário não pode conter caracteres especiais, acentos ou espaços", semAcentos = true, semEspacos = true, groups = AmbientValidator.class)
	private String login;


	private String senha;

	@OneToOne
	private Pessoa pessoa = new Pessoa();

	@Temporal(TemporalType.DATE)
	private Date dataAlteracaoSenha = new Date();

	@Temporal(TemporalType.DATE)
	private Date dataCriacao = new Date();

	@Temporal(TemporalType.DATE)
	private Date dataUltimoAcesso = new Date();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "usuario_id")
	private Set<PapelUsuario> papeis = new HashSet<PapelUsuario>();

	public void addPapel(EnumPapelUsuario papel) {
	    if (!isContemPapel(papel)) {
	        PapelUsuario pu = new PapelUsuario();
	        pu.setPapel(papel);
	        papeis.add(pu);
	    }
	}
	
	public void addAllPapel(List<EnumPapelUsuario> papeis) {
	    for (EnumPapelUsuario papel : papeis) {
	      addPapel(papel);
      }
	}
	
	public void removePapel(PapelUsuario papel) {
		papeis.remove(papel);
	}
	
	public boolean isContemPapel(EnumPapelUsuario papel) {
		for (PapelUsuario p : papeis) {
			if (p.getPapel() == papel) {
				return true;
			}
		}
		return false;
	}
	
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenhaNaoCriptografada(String senha) {
		this.senha = UtilHash.gerarStringHash(senha, Algoritimo.MD5);
	}

	public Date getDataAlteracaoSenha() {
		return dataAlteracaoSenha;
	}

	public void setDataAlteracaoSenha(Date dataAlteracaoSenha) {
		this.dataAlteracaoSenha = dataAlteracaoSenha;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataUltimoAcesso() {
		return dataUltimoAcesso;
	}

	public void setDataUltimoAcesso(Date dataUltimoAcesso) {
		this.dataUltimoAcesso = dataUltimoAcesso;
	}

	public Set<PapelUsuario> getPapeis() {
		return papeis;
	}

	public Object getId() {
		return login;
	}

	public List<PapelUsuario> getPapeisList() {
		List<PapelUsuario> result = new ArrayList<PapelUsuario>(papeis);
		Collections.sort(result);
		return result;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


}
