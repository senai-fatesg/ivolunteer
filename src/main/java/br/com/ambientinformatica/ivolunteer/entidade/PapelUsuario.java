package br.com.ambientinformatica.ivolunteer.entidade;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class PapelUsuario implements Comparable<PapelUsuario> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "papel_usuario_seq")
	@SequenceGenerator(name = "papel_usuario_seq", sequenceName = "papel_usuario_seq", allocationSize = 1, initialValue = 1)
	private Long id;

	@Enumerated(EnumType.STRING)
	private EnumPapelUsuario papel;

	public EnumPapelUsuario getPapel() {
		return papel;
	}

	public void setPapel(EnumPapelUsuario papel) {
		this.papel = papel;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int compareTo(PapelUsuario o) {
		try {
			return papel.toString().compareTo(o.papel.toString());
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
		      + ((id == null) ? super.hashCode() : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj.getClass().equals(getClass())) {
			return obj.hashCode() == hashCode();
		} else {
			return false;
		}
	}

}
