package br.com.ambientinformatica.ivolunteer.controle;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTurno;
import br.com.ambientinformatica.ivolunteer.entidade.Turma;
import br.com.ambientinformatica.ivolunteer.persistencia.TurmaDao;

@Controller("TurmaControl")
@Scope("conversation")
public class TurmaControl {

private Turma turma = new Turma();
	
	@Autowired
	private TurmaDao turmaDao;

	private EnumTurno turnoselecionado;
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

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public EnumTurno getTurnoselecionado() {
		return turnoselecionado;
	}

	public void setTurnoselecionado(EnumTurno turnoselecionado) {
		this.turnoselecionado = turnoselecionado;
	}
	
	public List<String> completeEnumTurno(String query){
		List<String> retorno = new ArrayList<String>();
		EnumTurno[] enunsTurno = EnumTurno.values();
		for (int i = 0; i < enunsTurno.length; i++) {
			retorno.add(enunsTurno[i].getDescricao());			
		}
		return retorno;
	}
	
	/*
	// Aplica Filtro por identificador
			public void aplicarFiltro(ActionEvent evt) {
				try {
					if (this.turma.getIdentificador().isEmpty()) {
						this.agrupamentoTurmas = this.agrupamentoTurmaDao.listar();
					} else {
						this.agrupamentoTurmas = this.agrupamentoTurmaDao.listarIdentificador(this.agrupamentoTurma);
					}
				} catch (Exception e) {
					UtilFaces.addMensagemFaces(e);
				}

			}
			public void listaAgrupamentosDeTurmas() {
				try {
					this.agrupamentoTurmas = agrupamentoTurmaDao.listar();
				} catch (PersistenciaException e) {
					UtilFaces.addMensagemFaces(e);
				}
			}
			
			public void carregaAgrupamentoTurma(AgrupamentoTurma agrupamentoTurma){
				this.agrupamentoTurma = agrupamentoTurma;		
			}
		
			public void excluir(AgrupamentoTurma agrupamentoTurma) {
				try {
					agrupamentoTurmaDao.excluirPorId(agrupamentoTurma.getId());
					UtilFaces.addMensagemFaces("Funcionário excluido com sucesso!");
				} catch (PersistenciaException e) {
					UtilFaces.addMensagemFaces(e);
				}

			}
	*/
}
