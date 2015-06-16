package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.EnumPrioridade;
import br.com.ambientinformatica.ivolunteer.entidade.EnumSexo;
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.entidade.SelecaoCandidato;
import br.com.ambientinformatica.ivolunteer.persistencia.PessoaDao;

@Controller("PesquisaSelecaoControl")
@Scope("conversation")
public class PesquisaSelecaoControl {

	private Pessoa pessoa = new Pessoa();

	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	
	private SelecaoCandidato selecaoCandidato = new SelecaoCandidato();	

	@Autowired
	private PessoaDao PessoaDao;

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
	public void confirmar(ActionEvent evt) {
		try {
			PessoaDao.alterar(pessoa);
			listar(evt);
			pessoa = new Pessoa();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void consultar(ActionEvent evt) {
		try {
			PessoaDao.consultar(pessoa.getId());
			pessoa = new Pessoa();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void descricaoDialogo(ActionEvent evt) {
		try {
			PessoaDao.alterar(pessoa);
			pessoas = PessoaDao.listar();
		} catch (Exception erro) {
			UtilFaces.addMensagemFaces(erro);
		}
	}

	public void listar(ActionEvent evt) {
		try {
			pessoas = PessoaDao.pesquisaSelecaoCandidato(selecaoCandidato);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e.getMessage());
		}
	}

	
	public List<SelectItem> getCompleteEnumPrioridade() {
		return UtilFaces.getListEnum(EnumPrioridade.values());
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public SelecaoCandidato getSelecaoCandidato() {
		return selecaoCandidato;
	}

	public void setSelecaoCandidato(SelecaoCandidato selecaoCandidato) {
		this.selecaoCandidato = selecaoCandidato;
	}
}
