package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.EnumStatus;
import br.com.ambientinformatica.ivolunteer.entidade.Parceiro;
import br.com.ambientinformatica.ivolunteer.persistencia.ParceiroDao;

@Controller("ParceiroListControl")
@Scope("conversation")
public class ParceiroListControl {

	@Autowired
	private ParceiroDao parceiroDao;

	private String nomeFiltro;
	private String statusFiltro;
	private Parceiro parceiro;
	private List<Parceiro> parceiros = new ArrayList<>();

	public void aplicarFiltro() {
		try {
			this.parceiros = parceiroDao.listarPorNomeStatus(nomeFiltro, statusFiltro);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void inativar(Parceiro parceiro) {
		parceiro.inativar();
		parceiroDao.alterar(parceiro);
		UtilFaces.addMensagemFaces("Registro inativado!");
	}
	
	public void ativar(Parceiro parceiro) {
		parceiro.ativar();
		parceiroDao.alterar(parceiro);
		UtilFaces.addMensagemFaces("Registro reativado!");
	}

	public void exibeInfo(Parceiro parceiro) {
		this.parceiro = parceiroDao.consultar(parceiro.getId());
	}

	public List<SelectItem> getStatus() {
		return UtilFaces.getListEnum(EnumStatus.values());
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

	public List<Parceiro> getParceiros() {
		return parceiros;
	}

	public Parceiro getParceiro() {
		return parceiro;
	}

	public void setParceiro(Parceiro parceiro) {
		this.parceiro = parceiro;
	}
	
}
