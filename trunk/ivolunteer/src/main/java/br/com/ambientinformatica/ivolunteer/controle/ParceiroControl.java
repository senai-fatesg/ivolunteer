package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.AgrupamentoTurma;
import br.com.ambientinformatica.ivolunteer.entidade.Parceiro;
import br.com.ambientinformatica.ivolunteer.entidade.Parceiro;
import br.com.ambientinformatica.ivolunteer.persistencia.AgrupamentoTurmaDao;
import br.com.ambientinformatica.ivolunteer.persistencia.ParceiroDao;
import br.com.ambientinformatica.ivolunteer.persistencia.ParceiroDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Controller("ParceiroControl")
@Scope("conversation")
public class ParceiroControl {
	
	private String nomeFiltro;
	private String statusFiltro;
	private Parceiro exibeParceiroInfo = new Parceiro();
	private Parceiro parceiro = new Parceiro();
	private List<Parceiro> listaParceiros = new ArrayList<Parceiro>();

	@Autowired
	private ParceiroDao parceiroDao;

	@PostConstruct
	public void init() {
		listar();
	}

	public void listar() {
		try {
			this.listaParceiros = parceiroDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void aplicarFiltro() {
		System.out.println("NOME ISEMPTY? " + this.nomeFiltro);
		System.out.println("NOME ISEMPTY? " + this.statusFiltro);
		
		if(!this.nomeFiltro.isEmpty() && this.statusFiltro.isEmpty()) {
			this.listaParceiros = parceiroDao.buscaParceiroPorNome(nomeFiltro);
		} else if(this.nomeFiltro.isEmpty() && !this.statusFiltro.isEmpty()) {
			this.listaParceiros = parceiroDao.buscaParceiroPorStatus(statusFiltro);
		} else if(!this.nomeFiltro.isEmpty() && !this.statusFiltro.isEmpty()) {
			this.listaParceiros = parceiroDao.buscaParceiroPorStatusNome(statusFiltro , nomeFiltro);
		} else {
			listar();
		}
		
	}
	
	public void cadastrarParceiro() {
		if(validaParceiro()) {
			parceiroDao.incluir(this.parceiro);
			UtilFaces.addMensagemFaces("Parceiro cadastrado com sucesso!");
			this.parceiro = new Parceiro();
			listar();
		}
	}
	
	public void editaParceiro(Parceiro parceiro) {
		this.parceiro = parceiroDao.consultar(parceiro.getId());
	}
	
	public void salvarAlteracoesParceiro() {
		if(validaParceiro()) {
			parceiroDao.alterar(this.parceiro);
			UtilFaces.addMensagemFaces("Parceiro atualizado com sucesso!");
			this.parceiro = new Parceiro();
			listar();
		}
	}
	
	public void exibeInfoDoParceiro(Parceiro parceiro) {
		this.exibeParceiroInfo = parceiroDao.consultar(parceiro.getId());
	}
	
	public void validaEmail(FacesContext fc, UIComponent uc, Object ob) {
		String email = (String) ob;
		if (!email.isEmpty() && !email.contains("@")) {
			((UIInput) uc).setValid(false);
			UtilFaces.addMensagemFaces("Email inválido. O email deve conter '@' em seu endereço.");
		}
	}
	
	public boolean validaParceiro() {
		if(this.parceiro.getNome().isEmpty()) {
			UtilFaces.addMensagemFaces("Nome do parceiro é obrigatório!");
			return false;
		} /*else if(this.parceiro.getCnpj().isEmpty()) {
			UtilFaces.addMensagemFaces("CNPJ do parceiro é obrigatório!");
			return false;
		} else if(this.parceiro.getCnpj().isEmpty() && this.parceiro.getNome().isEmpty()) {
			UtilFaces.addMensagemFaces("CNPJ e nome do parceiro são obrigatórios!");
			return false;
		}
		*/
		return true;
	}

	public String getNomeFiltro() {
		return nomeFiltro;
	}

	public void setNomeFiltro(String nomeFiltro) {
		this.nomeFiltro = nomeFiltro;
	}

	public String getStatusFiltro() {
		return statusFiltro;
	}

	public void setStatusFiltro(String statusFiltro) {
		this.statusFiltro = statusFiltro;
	}

	public Parceiro getExibeParceiroInfo() {
		return exibeParceiroInfo;
	}

	public void setExibeParceiroInfo(Parceiro exibeParceiroInfo) {
		this.exibeParceiroInfo = exibeParceiroInfo;
	}

	public Parceiro getParceiro() {
		return parceiro;
	}

	public void setParceiro(Parceiro parceiro) {
		this.parceiro = parceiro;
	}

	public List<Parceiro> getListaParceiros() {
		return listaParceiros;
	}

	public void setListaParceiros(List<Parceiro> listaParceiros) {
		this.listaParceiros = listaParceiros;
	}

	public ParceiroDao getParceiroDao() {
		return parceiroDao;
	}

	public void setParceiroDao(ParceiroDao parceiroDao) {
		this.parceiroDao = parceiroDao;
	}
	
}
