package br.com.ambientinformatica.ivolunteer.util;

import br.com.ambientinformatica.jpa.exception.ValidacaoException;

public class UtilIvolunteer {

	public static void validaEmail(String email) {
		if (email == null || email.isEmpty()) {
			throw new ValidacaoException("Email inválido!");
		} else if (email.contains("@") && email.contains(".")) {
			throw new ValidacaoException("Email inválido!");
		}
	}
}
