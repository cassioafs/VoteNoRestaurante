package br.com.dbserver.votenorestaurante.entity;


import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Voto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long id;
	
	@OneToOne
	private Restaurante restaurante;

	@OneToOne
	private Profissional profissional;
	
	private Calendar diaVoto;

	public Voto() {	}
	
	public Restaurante getRestaurante() {
		return restaurante;
	}
	
	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public Calendar getDiaVoto() {
		return Calendar.getInstance();
	}

	public void setDiaVoto(Calendar diaVoto) {
		this.diaVoto = diaVoto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((diaVoto == null) ? 0 : diaVoto.hashCode());
		result = prime * result + ((profissional == null) ? 0 : profissional.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voto other = (Voto) obj;
		if (diaVoto == null) {
			if (other.diaVoto != null)
				return false;
		} else if (!diaVoto.equals(other.diaVoto))
			return false;
		if (profissional == null) {
			if (other.profissional != null)
				return false;
		} else if (!profissional.equals(other.profissional))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
}
