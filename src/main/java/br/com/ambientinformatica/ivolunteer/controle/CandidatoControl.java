package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
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
}
