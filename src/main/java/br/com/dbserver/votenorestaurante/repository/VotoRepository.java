package br.com.dbserver.votenorestaurante.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.dbserver.votenorestaurante.entity.Profissional;
import br.com.dbserver.votenorestaurante.entity.Voto;

public interface VotoRepository extends CrudRepository<Voto, Long> {

	
	Voto findByProfissional(Profissional p);
}
