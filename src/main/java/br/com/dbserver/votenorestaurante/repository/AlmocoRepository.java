package br.com.dbserver.votenorestaurante.repository;

import java.util.Calendar;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.dbserver.votenorestaurante.entity.Almoco;
import br.com.dbserver.votenorestaurante.entity.Restaurante;

public interface AlmocoRepository extends CrudRepository<Restaurante, Long> {

	@Query("select a from Almoco a where a.diaVisitado between ?1 and ?2 and a.restaurante_id = ?3")
	Almoco findByDatesBetween(Calendar inicio, Calendar fim, Restaurante restaurante);

}
