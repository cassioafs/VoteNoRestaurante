package br.com.dbserver.votenorestaurante;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.dbserver.votenorestaurante.entity.Profissional;
import br.com.dbserver.votenorestaurante.entity.Restaurante;
import br.com.dbserver.votenorestaurante.repository.ProfissionalRepository;
import br.com.dbserver.votenorestaurante.repository.RestauranteRepository;

@SpringBootApplication
public class VoteNoRestauranteApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoteNoRestauranteApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(final ProfissionalRepository  profissionalRepository, final RestauranteRepository restauranteRepository) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... arg0) throws Exception {
				profissionalRepository.save(new Profissional("Cássio"));
				profissionalRepository.save(new Profissional("Luiz"));
				profissionalRepository.save(new Profissional("Murilo"));
				profissionalRepository.save(new Profissional("Michell"));
				profissionalRepository.save(new Profissional("Lima"));
				profissionalRepository.save(new Profissional("Gabriel"));
				
				restauranteRepository.save(new Restaurante("Le Chef"));
				restauranteRepository.save(new Restaurante("Mania de Churrasco"));
				restauranteRepository.save(new Restaurante("Padoka"));
				restauranteRepository.save(new Restaurante("Pimenta Doce"));
				restauranteRepository.save(new Restaurante("Centro Espanhol"));
				
			}
		};
		
			
	}
}
