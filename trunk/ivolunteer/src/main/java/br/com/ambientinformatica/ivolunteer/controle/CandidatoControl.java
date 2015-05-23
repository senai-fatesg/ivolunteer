package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.EnumEscolaridade;
import br.com.ambientinformatica.ivolunteer.entidade.EnumEstado;
import br.com.ambientinformatica.ivolunteer.entidade.EnumQuestao;
import br.com.ambientinformatica.ivolunteer.entidade.EnumSexo;
import br.com.ambientinformatica.ivolunteer.entidade.Pessoa;
import br.com.ambientinformatica.ivolunteer.persistencia.PessoaDao;

@Controller("CandidatoControl")
@Scope("conversation")
public class CandidatoControl {

	private Pessoa pessoa = new Pessoa();
	
	@Autowired
	private PessoaDao PessoaDao;
	
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	

   @PostConstruct
   public void init(){
      listar(null);
   }
   
	public void confirmar(ActionEvent evt){
		try {
			PessoaDao.alterar(pessoa);
         listar(evt);
         pessoa = new Pessoa();
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt){
		try {
			pessoas = PessoaDao.listar();
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e.getMessage());
		}
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
	
	private EnumEstado estadoSelecionado;
	
	public EnumEstado getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(EnumEstado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

	public List<String> completeEnumEstado(String query){
		List<String> retorno = new ArrayList<String>();
		EnumEstado[] enunsEstado = EnumEstado.values();
		for (int i = 0; i < enunsEstado.length; i++) {
			retorno.add(enunsEstado[i].getDescricao());			
		}
		return retorno;
	}
	
	
	private EnumSexo sexoSelecionado;
	
	public EnumSexo getSexoSelecionado() {
		return sexoSelecionado;
	}

	public void setSexoSelecionado(EnumSexo sexoSelecionado) {
		this.sexoSelecionado = sexoSelecionado;
	}

	public List<String> completeEnumSexo(String query){
		List<String> retorno = new ArrayList<String>();
		EnumSexo[] enunsSexo = EnumSexo.values();
		for (int i = 0; i < enunsSexo.length; i++) {
			retorno.add(enunsSexo[i].getDescricao());			
		}
		return retorno;
	}
	
	
	private EnumEscolaridade escolaridadeSelecionado;
	
	public EnumEscolaridade getEscolaridadeSelecionado() {
		return escolaridadeSelecionado;
	}

	public void setEscolaridadeSelecionado(EnumEscolaridade escolaridadeSelecionado) {
		this.escolaridadeSelecionado = escolaridadeSelecionado;
	}

	public List<String> completeEnumEscolaridade(String query){
		List<String> retorno = new ArrayList<String>();
		EnumEscolaridade[] enunsEscolaridade = EnumEscolaridade.values();
		for (int i = 0; i < enunsEscolaridade.length; i++) {
			retorno.add(enunsEscolaridade[i].getDescricao());			
		}
		return retorno;
	}
	
}
