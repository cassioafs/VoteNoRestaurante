package br.com.dbserver.votenorestaurante;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;

import br.com.dbserver.votenorestaurante.business.Votacao;
import br.com.dbserver.votenorestaurante.entity.Profissional;
import br.com.dbserver.votenorestaurante.entity.Restaurante;
import br.com.dbserver.votenorestaurante.entity.Voto;
import br.com.dbserver.votenorestaurante.excepetion.RestauranteNaoPodeSerVotadoException;
import br.com.dbserver.votenorestaurante.repository.AlmocoRepository;
import br.com.dbserver.votenorestaurante.repository.VotoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class VotarRestauranteTest {

	@Autowired
	private VotoRepository votoRepository;

	@Autowired
	private AlmocoRepository almocoRepository;

	@Autowired
	private WebApplicationContext context;
	
	@Before
	public void setUp() throws Exception {
	    // Initialize mocks created above
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void profissionalDeveEscolherRestaurante() {

		
		Profissional cassio = new Profissional("Cássio");
		Restaurante leChef = new Restaurante("Le Chef");
		Votacao votacao = new Votacao(votoRepository, almocoRepository);
		votacao.votar(leChef, cassio);
		
		assertEquals(leChef.getNome(), votacao.getRestauranteEscolhido().getNome());
		
	}
	
	@Test
	public void doisProfissionaisPodemEscolherRestaurantesDiferentes() {
		
		Profissional murilo = new Profissional("Murilo");
		Profissional cassio = new Profissional("Cássio");
		
		Restaurante leChef = new Restaurante("Le Chef");
		Restaurante maniaDeChurrasco = new Restaurante("Mania de Churrasco");
		
		Votacao votacao = new Votacao();
		Voto votoCassio = votacao.votar(leChef, cassio);
		Voto votoMurilo = votacao.votar(maniaDeChurrasco, murilo);
		
		assertEquals(leChef.getNome(),votoCassio.getRestaurante().getNome());
		assertEquals(maniaDeChurrasco.getNome(),votoMurilo.getRestaurante().getNome());
	}
	
	@Test(expected=RestauranteNaoPodeSerVotadoException.class)
	public void profissionalNaoPodeVotarDuasVezesNoMesmoRestaurante() {
		
		Profissional cassio = new Profissional("Cássio");
		
		Restaurante maniaDeChurrasco = new Restaurante("Mania de Churrasco");
		Votacao votacao = new Votacao();
		votacao.votar(maniaDeChurrasco, cassio);
		votacao.votar(maniaDeChurrasco, cassio);
		
	}
	
	@Test(expected=RestauranteNaoPodeSerVotadoException.class)
	public void profissionalNaoPodeVotarDuasVezesEmRestauranteDiferente() {
		
		Profissional cassio = new Profissional("Cássio");
		
		Restaurante maniaDeChurrasco = new Restaurante("Mania de Churrasco");
		Restaurante letsWok = new Restaurante("Let's Wok");
		
		Votacao votacao = new Votacao();
		votacao.votar(maniaDeChurrasco, cassio);
		votacao.votar(letsWok, cassio);

	}
	
	@Test
	public void deveSomarQuantidadeDeVotos(){
	
		Profissional cassio = new Profissional("Cássio");
		Profissional luiz = new Profissional("Luiz");
		Profissional murilo = new Profissional("Murilo");
		
		Restaurante maniaDeChurrasco = new Restaurante("Mania de Churrasco");
		Restaurante leChef = new Restaurante("Le Chef");
		
		Votacao votacao = new Votacao();
		votacao.votar(maniaDeChurrasco, cassio);
		votacao.votar(maniaDeChurrasco, murilo);
		votacao.votar(leChef, luiz);
		
		assertEquals(3, votacao.getQuantidadeVotos());
		
	}
	
	@Test
	public void deveEscolherRestauranteVencedor(){
		
		Profissional cassio = new Profissional("Cássio");
		Profissional luiz = new Profissional("Luiz");
		Profissional murilo = new Profissional("Murilo");
		
		Restaurante maniaDeChurrasco = new Restaurante("Mania de Churrasco");
		Restaurante leChef = new Restaurante("Le Chef");
		
		Votacao votacao = new Votacao();
		votacao.votar(maniaDeChurrasco, cassio);
		votacao.votar(leChef, luiz);
		votacao.votar(maniaDeChurrasco, murilo);
		
		assertEquals(maniaDeChurrasco.getNome(), votacao.getRestauranteVencedor().getNome());
		
	}
	
	@Test
	public void naoPodeComputarVotosSemVotacao(){
		
		Votacao votacao = new Votacao();
		
		assertEquals(0,votacao.getVotos().size());
		
	}
	
}
