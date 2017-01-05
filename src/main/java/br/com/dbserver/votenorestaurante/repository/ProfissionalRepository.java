package br.com.dbserver.votenorestaurante.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.dbserver.votenorestaurante.entity.Profissional;

public interface ProfissionalRepository extends CrudRepository<Profissional, Long> {

	public Profissional findByNome(String nome);
}
