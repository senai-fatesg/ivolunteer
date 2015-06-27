package br.com.ambientinformatica.ivolunteer.util;

import java.util.List;

public class UtilitarioManipuladorDeLista {

	public boolean ObjetoEhConsistente(Object objeto){
		if(objeto != null && objeto != "")
			return true;
		return false;
	}
	
	public boolean ListaEhConsistente(List<Object> listaObjeto){
		if(listaObjeto != null && !listaObjeto.isEmpty() && listaObjeto.size() > 0)
			return true;
		return false;
	}
}
