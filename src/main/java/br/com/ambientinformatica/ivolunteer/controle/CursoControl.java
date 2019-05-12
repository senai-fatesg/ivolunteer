package br.com.ambientinformatica.ivolunteer.controle;

import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.Curso;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoCurso;
import br.com.ambientinformatica.ivolunteer.entidade.Parceiro;
import br.com.ambientinformatica.ivolunteer.persistencia.CursoDao;
import br.com.ambientinformatica.ivolunteer.persistencia.ParceiroDao;

@Controller("CursoControl")
@Scope("conversation")
public class CursoControl {

	@Autowired
	private CursoDao cursoDao;
	
	@Autowired
	private ParceiroDao parceiroDao;
	
	private Curso curso = new Curso();
	
	public void salvar() {
		try {
			cursoDao.alterar(this.curso);
			this.curso = new Curso();
			UtilFaces.addMensagemFaces("Curso atualizado com sucesso!");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public List<Parceiro> getParceiros() {
		return parceiroDao.listar();
	}

	public List<SelectItem> getCompleteEnumTipoCurso() {
		return UtilFaces.getListEnum(EnumTipoCurso.values());
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}
