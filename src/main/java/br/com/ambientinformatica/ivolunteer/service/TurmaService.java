package br.com.ambientinformatica.ivolunteer.service;

import java.util.Set;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTurno;

public interface TurmaService {
	Set<EnumTurno> getTurno();
}
