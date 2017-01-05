package br.com.dbserver.votenorestaurante.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Almoco  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@OneToOne
	private Restaurante restaurante;
	private Calendar diaVisitado;
	
	public Almoco() {	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Restaurante getRestaurante() {
		return restaurante;
	}
	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	public Calendar getDiaVisitado() {
		return diaVisitado;
	}
	public void setDiaVisitado(Calendar diaVisitado) {
		this.diaVisitado = diaVisitado;
	}
	
}
