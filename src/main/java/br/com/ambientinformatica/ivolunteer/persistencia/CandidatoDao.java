package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import br.com.ambientinformatica.ivolunteer.entidade.Candidato;
import br.com.ambientinformatica.ivolunteer.entidade.SelecaoCandidato;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface CandidatoDao extends Persistencia<Candidato> {
	
	public List<Candidato> listaCandidatosComResponsavel();
	public List<Candidato> listaCandidatoPorNome(String nome);
	public List<Candidato> pesquisaSelecaoCandidato(SelecaoCandidato selecaoCandidato);
	public Candidato consultarCandidatoCompleto(Candidato Candidato);
	public List<Candidato> listaCandidato();
	public List<Candidato> listarCandidatosAtivos();
	public List<Candidato> listarCandidatosAtivosPorNome(String candidatoConsulta);
}
