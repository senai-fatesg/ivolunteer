package br.com.ambientinformatica.ivolunteer.controle;


	import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.GradeHorario;
import br.com.ambientinformatica.ivolunteer.persistencia.GradeHorarioDao;

	@Controller("GradeHorarioControl")
	@Scope("conversation")
		public class GradeHorarioControl {
	
		private GradeHorario gradeHorario = new GradeHorario();
		
		@Autowired
		private GradeHorarioDao gradehorarioDao;
		
		private List<GradeHorario> gradeHorarios = new ArrayList<GradeHorario>();
		

	   @PostConstruct
	   public void init(){
	      listar(null);
	   }
	   
		public void confirmar(ActionEvent evt){
			try {
				gradehorarioDao.alterar(gradeHorario);
	         listar(evt);
	         gradeHorario = new GradeHorario();
			} catch (Exception e) {
			   UtilFaces.addMensagemFaces(e);
			}
		}

		public void listar(ActionEvent evt){
			try {
				gradeHorarios = gradehorarioDao.listar();
			} catch (Exception e) {
			   UtilFaces.addMensagemFaces(e);
			}
		}
		
		public GradeHorario getGradeHorario() {
			return gradeHorario;
		}

		public void seGradeHorario(GradeHorario gradehorario) {
			this.gradeHorario = gradehorario;
		}
		
		public List<GradeHorario> getGradeHorarios() {
			return  gradeHorarios;
		}

	
}
