package br.com.ambientinformatica.ivolunteer.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ivolunteer.entidade.Avaliacao;
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.persistencia.AvaliacaoDao;
import br.com.ambientinformatica.ivolunteer.persistencia.PessoaDao;


@Controller("RespondeAvaliacaoControl")
@Scope("conversation")
public class RespondeAvaliacaoControl {
	private Avaliacao avaliacaoSelecionada = new Avaliacao();
	private Pessoa pessoaSelecionada = new Pessoa();
	
	@Autowired
	AvaliacaoDao avaliacaoDao;
	@Autowired
	PessoaDao pessoaDao;
	
	public List<Avaliacao> completaAvaliacoes (String query){
		return this.avaliacaoDao.listarTitulo(query);
	}
	
	public List<Pessoa> completaPessoas (String query){
		return this.pessoaDao.listarPorNome(query); 
	}

	public Avaliacao getAvaliacaoSelecionada() {
		return avaliacaoSelecionada;
	}

	public void setAvaliacaoSelecionada(Avaliacao avaliacaoSelecionada) {
		this.avaliacaoSelecionada = avaliacaoSelecionada;
	}

	public Pessoa getPessoaSelecionada() {
		return pessoaSelecionada;
	}

	public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
		this.pessoaSelecionada = pessoaSelecionada;
	}
	
	
}
