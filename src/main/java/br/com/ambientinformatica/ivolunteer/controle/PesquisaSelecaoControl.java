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
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.entidade.SelecaoCandidato;
import br.com.ambientinformatica.ivolunteer.persistencia.PessoaDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Controller("PesquisaSelecaoControl")
@Scope("conversation")
public class PesquisaSelecaoControl {

	private Pessoa pessoa = new Pessoa();

	private List<Pessoa> pessoas = new ArrayList<Pessoa>();

	// instancia de candidato carregada para realização de consulta
	private Pessoa candidatoConsulta = new Pessoa();
	// lista utilizada na consulta do candidato
	private List<Pessoa> listaCandidato = new ArrayList<Pessoa>();

	private SelecaoCandidato selecaoCandidato = new SelecaoCandidato();

	@Autowired
	private PessoaDao pessoaDao;

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void confirmar(ActionEvent evt) {
		try {
			pessoaDao.alterar(candidatoConsulta);
			//listar(evt);
			candidatoConsulta = new Pessoa();
			UtilFaces.addMensagemFaces("Informações salvas com sucesso!");
			candidatoConsulta = new Pessoa();
			pessoa = new Pessoa();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Ocorreu uma falha ao tentar gravar as informações de candidato");
		}
	}

	public void consultar(ActionEvent evt) {
		try {
			candidatoConsulta = pessoaDao.consultar(pessoa.getId());
			pessoa = new Pessoa();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Ocorreu uma falha ao tentar consultar o candidato.");
		}
	}

	public void carregaPessoaAlteracao(Pessoa pessoa)
	      throws PersistenciaException {
		this.candidatoConsulta = pessoaDao.consultarPessoaCompleta(pessoa);		
	}

	public void descricaoDialogo(ActionEvent evt) {
		try {
			pessoaDao.alterar(pessoa);
			pessoas = pessoaDao.listar();
		} catch (Exception erro) {
			UtilFaces.addMensagemFaces(erro);
		}
	}

	public List<Pessoa> getListaCandidato() {
		return listaCandidato;
	}

	public void setListaCandidato(List<Pessoa> listaCandidato) {
		this.listaCandidato = listaCandidato;
	}

	public void listar(ActionEvent evt) {
		try {
			pessoas = pessoaDao.pesquisaSelecaoCandidato(selecaoCandidato);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e.getMessage());
		}
	}

	// Aplica Filtro
	public void aplicarFiltro(ActionEvent evt) {
		try {
			// listaCandidato =
			// pessoaDao.pesquisaSelecaoCandidato(selecaoCandidato);

			if (candidatoConsulta.getNomePessoa() == null
			      || candidatoConsulta.getNomePessoa().isEmpty()) {
				listaCandidato = pessoaDao.listaCandidato();
			} else {
				listaCandidato = pessoaDao.listaCandidatoPorNome(this.pessoa
				      .getNomePessoa());
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
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

	public Pessoa getCandidatoConsulta() {
		return candidatoConsulta;
	}

	public void setCandidatoConsulta(Pessoa candidatoConsulta) {
		this.candidatoConsulta = candidatoConsulta;
	}

	public SelecaoCandidato getSelecaoCandidato() {
		return selecaoCandidato;
	}

	public void setSelecaoCandidato(SelecaoCandidato selecaoCandidato) {
		this.selecaoCandidato = selecaoCandidato;
	}
}
