package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Aluno;
import br.com.ambientinformatica.ivolunteer.persistencia.AlunoDao;

@Controller("AlunoControl")
@Scope("conversation")
public class AlunoControl {

	private Aluno aluno = new Aluno();

	@Autowired
	private AlunoDao alunoDao;

	private List<Aluno> alunos = new ArrayList<Aluno>();

	@PostConstruct
	public void init() {
		listar(null);
	}

	public void confirmar(ActionEvent evt) {
		try {
			alunoDao.alterar(aluno);
			listar(evt);
			aluno = new Aluno();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt) {
		try {
			alunos = alunoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getAlunos() {
		return alunos;	
	}
}
