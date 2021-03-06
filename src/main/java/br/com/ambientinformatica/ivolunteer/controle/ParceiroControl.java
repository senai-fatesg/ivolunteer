package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.EnumEstado;
import br.com.ambientinformatica.ivolunteer.entidade.Parceiro;
import br.com.ambientinformatica.ivolunteer.persistencia.ParceiroDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Controller("ParceiroControl")
@Scope("conversation")
public class ParceiroControl {

	private String nomeFiltro;
	private String statusFiltro;
	private Parceiro parceiro = new Parceiro();
	private List<Parceiro> listaParceiros = new ArrayList<Parceiro>();

	@Autowired
	private ParceiroDao parceiroDao;

	public void salvar() {
		try {
			parceiroDao.alterar(this.parceiro);
			this.parceiro = new Parceiro();
			UtilFaces.addMensagemFaces("Parceiro salvo com sucesso!");
		} catch (PersistenciaException e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public List<SelectItem> getCompleteEnumEstado() {
		return UtilFaces.getListEnum(EnumEstado.values());
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

	public Parceiro getParceiro() {
		return parceiro;
	}

	public void setParceiro(Parceiro parceiro) {
		this.parceiro = parceiroDao.consultar(parceiro.getId());
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
