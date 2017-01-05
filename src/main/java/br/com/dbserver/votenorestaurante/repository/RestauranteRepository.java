package br.com.dbserver.votenorestaurante.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.dbserver.votenorestaurante.entity.Restaurante;

public interface RestauranteRepository extends CrudRepository<Restaurante, Long> {
	
	public Restaurante findByNome(String nome);
}
