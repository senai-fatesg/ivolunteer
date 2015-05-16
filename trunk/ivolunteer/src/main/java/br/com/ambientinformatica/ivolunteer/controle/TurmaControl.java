package br.com.ambientinformatica.ivolunteer.controle;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Turma;
import br.com.ambientinformatica.ivolunteer.persistencia.TurmaDao;

@Controller("TurmaControl")
@Scope("conversation")
public class TurmaControl {

private Turma turma = new Turma();
	
	@Autowired
	private TurmaDao turmaDao;
	
	private List<Turma> turmas = new ArrayList<Turma>();
	

   @PostConstruct
   public void init(){
      listar(null);
   }
   
	public void confirmar(ActionEvent evt){
		try {
			turmaDao.alterar(turma);
         listar(evt);
         turma = new Turma();
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt){
		try {
			turmas = turmaDao.listar();
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}
	
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
	public List<Turma> getTurmas() {
		return turmas;
	}
}
